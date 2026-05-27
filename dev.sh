#!/bin/bash
set -o errexit -o nounset # bash script safety

# For GWT download URLs see https://www.gwtproject.org/versions.html
GWT_VERSION="2.8.2"
#GWT_URL="https://github.com/gwtproject/gwt/releases/download/2.10.0/gwt-2.10.0.zip"
#GWT_URL="https://storage.googleapis.com/gwt-releases/gwt-2.9.0.zip"
GWT_URL="https://github.com/gwtproject/gwt/releases/download/2.8.2/gwt-2.8.2.zip" # 2.8.2
#GWT_URL="https://goo.gl/TysXZl" # 2.8.1 (does not run)

if [[ "$(uname -s)" == "Darwin" ]]; then
    SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd -P)"
else
    SCRIPT_DIR="$(dirname "$(readlink -f "${BASH_SOURCE[0]}")")"
fi
SDK_DIR="$SCRIPT_DIR/.."
GWT_DIR="$SDK_DIR/gwt-$GWT_VERSION"

WEB_PORT=${WEB_PORT:-8000}
WEB_BINDADDRESS=${WEB_BINDADDRESS:-127.0.0.1}
CODESERVER_BINDADDRESS=${CODESERVER_BINDADDRESS:-127.0.0.1}

compile() {
    ant build
}

package() {
    compile
    (
        cd "$SCRIPT_DIR/war"
        tar czf "$SCRIPT_DIR/circuitjs1.tar.gz" .
    )
}

setup() {
    # Install Java if no java compiler is present
    if ! which javac > /dev/null 2>&1 || ! which ant > /dev/null 2>&1; then
        set -x
        if [[ "$(uname -s)" == "Darwin" ]]; then
            # macOS: install via Homebrew
            if ! which brew > /dev/null 2>&1; then
                echo "Homebrew is required on macOS. Install it from https://brew.sh and re-run."
                exit 1
            fi
            brew install openjdk ant
            # Ensure the Homebrew-managed JDK is on PATH
            if [[ -d "$(brew --prefix openjdk)/bin" ]]; then
                export PATH="$(brew --prefix openjdk)/bin:$PATH"
            fi
        else
            echo "Installing packages may need your sudo password."
            sudo apt-get update
            sudo apt-get install -y openjdk-8-jdk-headless ant
        fi
        set +x
    fi

    if ! [[ -d "$GWT_DIR" ]]; then
        mkdir -p "$SDK_DIR"
        (
            cd "$SDK_DIR"
            if [[ "$(uname -s)" == "Darwin" ]]; then
                curl -L "$GWT_URL" -o "gwt-$GWT_VERSION.zip"
            else
                wget "$GWT_URL" -O "gwt-$GWT_VERSION.zip"
            fi
            unzip "gwt-$GWT_VERSION.zip"
            rm "gwt-$GWT_VERSION.zip"
        )
    fi

    if [[ -e build.xml ]]; then
        mv build.xml build.xml.backup
    fi
    chmod +x "$GWT_DIR/webAppCreator"
    "$GWT_DIR/webAppCreator" -out ../tempProject com.lushprojects.circuitjs1.circuitjs1
    cp ../tempProject/build.xml ./
    # sed -i requires an empty backup suffix on macOS
    if [[ "$(uname -s)" == "Darwin" ]]; then
        sed -i '' 's/source="1.7"/source="1.8"/g' build.xml
        sed -i '' 's/target="1.7"/target="1.8"/g' build.xml
    else
        sed -i 's/source="1.7"/source="1.8"/g' build.xml
        sed -i 's/target="1.7"/target="1.8"/g' build.xml
    fi
    rm -rf ../tempProject
}

codeserver() {
    mkdir -p war/WEB-INF/classes
    # war/WEB-INF/classes must be on the classpath so GWT generators
    # (e.g. ElementFactoryGenerator) can be loaded as compiled classes.
    java -classpath "src:war/WEB-INF/classes:$GWT_DIR/gwt-codeserver.jar:$GWT_DIR/gwt-dev.jar:$GWT_DIR/gwt-user.jar" \
        com.google.gwt.dev.codeserver.CodeServer \
        -launcherDir war \
        -bindAddress ${CODESERVER_BINDADDRESS} \
        com.lushprojects.circuitjs1.circuitjs1
}

webserver() {
    webroot="$SCRIPT_DIR/war"

    (
        cd $webroot
        python3 -m http.server --bind ${WEB_BINDADDRESS} ${WEB_PORT}
    )
}

start() {
    echo "Compiling Java sources..."
    ant javac
    echo "Starting web server http://${WEB_BINDADDRESS}:${WEB_PORT}"
    trap "pkill -f \"python -m http.server\"" EXIT
    webserver >"webserver.log" 2>&1 &
    sleep 0.5
    codeserver | tee "codeserver.log"
}


for func in $(compgen -A function); do
    if [[ $func == "$1" ]]; then
        shift
        $func "$@"
        exit $?
    fi
done

echo "Unknown command '$1'. Try one of the following:"
compgen -A function
exit 1
