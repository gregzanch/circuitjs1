/*
    Copyright (C) Paul Falstad and Iain Sharp

    This file is part of CircuitJS1.

    CircuitJS1 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    CircuitJS1 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CircuitJS1.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.lushprojects.circuitjs1.client;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Document;

/**
 * 74HC123 — Dual Retriggerable Monostable Multivibrator with Reset.
 *
 * Matches the CD54/74HC123 datasheet (TI SCHS142F) exactly:
 *
 *   • VCC pin (top, SIDE_N) and GND pin (bottom, SIDE_S) set the supply rails.
 *     All logic thresholds and output levels track the connected supply:
 *       V_threshold  = (V_CC + V_GND) / 2    (50 % of swing)
 *       V_OH  = V_CC,   V_OL = V_GND
 *
 *   • Two independent one-shot sections; per section:
 *       Ā  (active-LOW) — trailing-edge trigger when B is HIGH
 *       B               — leading-edge trigger when Ā is LOW
 *       R̄  (active-LOW) — override reset (level); also rising-edge trigger on 123
 *       Q  output       — HIGH during the pulse
 *       Q̄  output       — complement of Q
 *
 *   • Retriggerable: any valid trigger while a pulse is active reloads the timer.
 *   • Pulse width:  tW = 0.45 · R_X · C_X   (K ≈ 0.45 at V_CC = 5 V)
 *
 * Visual layout:
 *
 *                      ┌────────────────┐
 *                      │   [Vcc top]    │   ← VCC (SIDE_N, pos 2)
 *   1Ā  pos 0  (W) ────┤                ├──── 1Q̄  pos 0  (E)
 *   1B  pos 1  (W) ────┤                ├──── 1Q   pos 1  (E)
 *   1R̄  pos 2  (W) ────┤   74HC123      │
 *                      │                │
 *   2Ā  pos 4  (W) ────┤                ├──── 2Q   pos 4  (E)
 *   2B  pos 5  (W) ────┤                ├──── 2Q̄  pos 5  (E)
 *   2R̄  pos 6  (W) ────┤                │
 *                      │   [GND bot]    │   ← GND (SIDE_S, pos 2)
 *                      └────────────────┘
 *
 * (Position 3 on W/E sides is a deliberate gap separating the two sections.)
 */
class HC123Elm extends ChipElm {

    // ── Pin indices ──────────────────────────────────────────────────────────
    // Section 1
    static final int N_1A  = 0;   // 1Ā  input  (IC pin 1)
    static final int N_1B  = 1;   // 1B  input  (IC pin 2)
    static final int N_1R  = 2;   // 1R̄  input  (IC pin 3)
    static final int N_1QB = 3;   // 1Q̄  output (IC pin 4)
    static final int N_1Q  = 4;   // 1Q  output (IC pin 13)
    // Section 2
    static final int N_2A  = 5;   // 2Ā  input  (IC pin 9)
    static final int N_2B  = 6;   // 2B  input  (IC pin 10)
    static final int N_2R  = 7;   // 2R̄  input  (IC pin 11)
    static final int N_2Q  = 8;   // 2Q  output (IC pin 5)
    static final int N_2QB = 9;   // 2Q̄  output (IC pin 12)
    // Supply
    static final int N_VCC = 10;  // VCC  (IC pin 16) — SIDE_N
    static final int N_GND = 11;  // GND  (IC pin 8)  — SIDE_S

    // ── Per-section state ────────────────────────────────────────────────────
    private boolean[] pulseActive  = new boolean[2];
    private double[]  pulseEndTime = new double[2];
    private boolean[] prevA        = new boolean[2];
    private boolean[] prevB        = new boolean[2];
    private boolean[] prevR        = new boolean[2];

    // ── RC timing parameters ─────────────────────────────────────────────────
    // Pulse width = 0.45 · rx · cx   (K ≈ 0.45 at VCC = 5 V, per datasheet)
    // Defaults: Rx = 10 kΩ, Cx = 10 nF  →  tW ≈ 45 µs
    private double rx1 = 10000;    // Section-1 external resistor  (Ω)
    private double cx1 = 10e-9;    // Section-1 external capacitor (F)
    private double rx2 = 10000;    // Section-2 external resistor  (Ω)
    private double cx2 = 10e-9;    // Section-2 external capacitor (F)

    // ── Constructors ─────────────────────────────────────────────────────────

    public HC123Elm(int xx, int yy) {
        super(xx, yy);
        reset();
    }

    public HC123Elm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
        super(xa, ya, xb, yb, f, st);
        reset();
    }

    // ── Chip identity ─────────────────────────────────────────────────────────

    String getChipName() { return "74HC123"; }

    // Suppress the inherited "High Logic Voltage" property entry — VCC is now
    // a physical pin, so the property is irrelevant.
    @Override boolean isDigitalChip() { return false; }

    // ── Pin setup ─────────────────────────────────────────────────────────────

    void setupPins() {
        sizeX = 4;
        sizeY = 7;    // positions 0-6; gap at 3 divides the two sections
        pins = new Pin[getPostCount()];

        // ── Section 1 ── (W-side positions 0-2, E-side positions 0-1)
        pins[N_1A]  = new Pin(0, SIDE_W, "A");
        pins[N_1A].lineOver  = true;            // Ā

        pins[N_1B]  = new Pin(1, SIDE_W, "B");

        pins[N_1R]  = new Pin(2, SIDE_W, "R");
        pins[N_1R].lineOver  = true;            // R̄

        pins[N_1QB] = new Pin(0, SIDE_E, "Q");
        pins[N_1QB].output   = true;
        pins[N_1QB].lineOver = true;            // Q̄

        pins[N_1Q]  = new Pin(1, SIDE_E, "Q");
        pins[N_1Q].output    = true;

        // ── Section 2 ── (W-side positions 4-6, E-side positions 4-5)
        pins[N_2A]  = new Pin(4, SIDE_W, "A");
        pins[N_2A].lineOver  = true;

        pins[N_2B]  = new Pin(5, SIDE_W, "B");

        pins[N_2R]  = new Pin(6, SIDE_W, "R");
        pins[N_2R].lineOver  = true;

        pins[N_2Q]  = new Pin(4, SIDE_E, "Q");
        pins[N_2Q].output    = true;

        pins[N_2QB] = new Pin(5, SIDE_E, "Q");
        pins[N_2QB].output   = true;
        pins[N_2QB].lineOver = true;

        // ── Supply rails ─────────────────────────────────────────────────────
        // VCC at horizontal-centre of top edge (SIDE_N, pos 2 → x-centre for sizeX=4)
        pins[N_VCC] = new Pin(2, SIDE_N, "Vcc");

        // GND at horizontal-centre of bottom edge (SIDE_S, pos 2)
        pins[N_GND] = new Pin(2, SIDE_S, "GND");
    }

    int getPostCount()          { return 12; }
    int getVoltageSourceCount() { return 4;  }   // 4 output pins

    // ── Reset / initialise ────────────────────────────────────────────────────

    void reset() {
        super.reset();   // sets all pin.value = false, volts[] = 0

        // Idle state: Q = LOW, Q̄ = HIGH for both sections
        pins[N_1QB].value = true;
        pins[N_1Q].value  = false;
        pins[N_2Q].value  = false;
        pins[N_2QB].value = true;

        for (int s = 0; s < 2; s++) {
            pulseActive[s]  = false;
            pulseEndTime[s] = 0;
            prevA[s]        = false;
            prevB[s]        = false;
            prevR[s]        = true;   // R̄ inactive (HIGH) at start-up
        }
    }

    // ── Override stamp() ──────────────────────────────────────────────────────
    // Reference all voltage sources to the chip's own GND pin rather than the
    // simulator's global ground, so outputs respect whatever supply is connected.

    @Override void stamp() {
        for (int i = 0; i != getPostCount(); i++) {
            Pin p = pins[i];
            if (p.output)
                sim.stampVoltageSource(nodes[N_GND], nodes[i], p.voltSource);
        }
    }

    // Keep the VoltageSource ↔ node association consistent with our GND pin.
    @Override void setVoltageSource(int j, VoltageSource vs) {
        for (int i = 0; i != getPostCount(); i++) {
            Pin p = pins[i];
            if (p.output && j-- == 0) {
                p.voltSource = vs;
                vs.setNodes(nodes[N_GND], nodes[i]);
                return;
            }
        }
        CirSim.console("setVoltageSource failed for HC123Elm");
    }

    // ── Override hasGroundConnection() ───────────────────────────────────────
    // The GND pin is effectively the local ground reference for this chip.
    @Override boolean hasGroundConnection(int n1) {
        return pins[n1].output || n1 == N_GND;
    }

    // ── Helper: pulse width for section s ────────────────────────────────────

    private double pulseWidth(int s) {
        return 0.45 * (s == 0 ? rx1 * cx1 : rx2 * cx2);
    }

    // ── Main simulation step ──────────────────────────────────────────────────
    // Overrides ChipElm.doStep() so that:
    //   • logic thresholds track the real supply swing (VCC − GND)
    //   • output levels are referenced to the chip's GND and VCC pins

    @Override void doStep() {
        double vcc   = volts[N_VCC];
        double vgnd  = volts[N_GND];
        double swing = vcc - vgnd;

        // Guard against unconnected / inverted supply — treat chip as powered off
        if (swing < 0.5) {
            for (int i = 0; i != getPostCount(); i++) {
                Pin p = pins[i];
                if (p.output)
                    sim.updateVoltageSource(nodes[N_GND], nodes[i], p.voltSource, 0);
            }
            return;
        }

        // Datasheet thresholds: V_IH ≥ 0.7 × V_CC, V_IL ≤ 0.3 × V_CC
        // Using 50 % (= midpoint) as the digital threshold — common simulation choice.
        double threshold = vgnd + swing * 0.5;

        // Latch input pin logic levels (excluding supply pins)
        for (int i = 0; i != getPostCount(); i++) {
            Pin p = pins[i];
            if (!p.output && i != N_VCC && i != N_GND)
                p.value = volts[i] > threshold;
        }

        execute();   // run 74HC123 one-shot logic

        // Drive output voltage sources relative to GND node
        for (int i = 0; i != getPostCount(); i++) {
            Pin p = pins[i];
            if (p.output)
                sim.updateVoltageSource(nodes[N_GND], nodes[i], p.voltSource,
                    p.value ? swing : 0);
        }
    }

    // ── One-shot logic (both sections) ───────────────────────────────────────

    @Override void execute() {
        for (int s = 0; s < 2; s++) {
            int base = s * 5;

            boolean A = pins[base + 0].value;   // Ā pin voltage
            boolean B = pins[base + 1].value;   // B  pin voltage
            boolean R = pins[base + 2].value;   // R̄  pin voltage

            if (!R) {
                // ── R̄ LOW: override reset — immediately terminate pulse ──────
                pulseActive[s] = false;

            } else {
                // ── R̄ HIGH: evaluate trigger conditions ─────────────────────
                boolean triggered = false;

                // 1. Leading-edge trigger: B rises (LOW→HIGH) while Ā is LOW
                if (!A && B && !prevB[s])
                    triggered = true;

                // 2. Trailing-edge trigger: Ā falls (HIGH→LOW) while B is HIGH
                if (B && !A && prevA[s])
                    triggered = true;

                // 3. Reset-recovery trigger (74HC123 only):
                //    R̄ rises (LOW→HIGH) while Ā is LOW and B is HIGH
                if (!A && B && R && !prevR[s])
                    triggered = true;

                if (triggered) {
                    // (Re)trigger: reload the timer to the full pulse width
                    pulseActive[s]  = true;
                    pulseEndTime[s] = sim.t + pulseWidth(s);
                }

                // Timer expiry
                if (pulseActive[s] && sim.t >= pulseEndTime[s])
                    pulseActive[s] = false;
            }

            // Drive Q / Q̄ outputs
            boolean q = pulseActive[s];
            if (s == 0) {
                writeOutput(N_1Q,   q);
                writeOutput(N_1QB, !q);
            } else {
                writeOutput(N_2Q,   q);
                writeOutput(N_2QB, !q);
            }

            prevA[s] = A;
            prevB[s] = B;
            prevR[s] = R;
        }
    }

    // ── Serialisation ─────────────────────────────────────────────────────────

    int getDumpType() { return 437; }

    @Override void dumpXml(Document doc, Element elem) {
        super.dumpXml(doc, elem);
        XMLSerializer.dumpAttr(elem, "rx1", rx1);
        XMLSerializer.dumpAttr(elem, "cx1", cx1);
        XMLSerializer.dumpAttr(elem, "rx2", rx2);
        XMLSerializer.dumpAttr(elem, "cx2", cx2);
    }

    @Override void undumpXml(XMLDeserializer xml) {
        super.undumpXml(xml);   // calls setupPins() internally
        rx1 = xml.parseDoubleAttr("rx1", rx1);
        cx1 = xml.parseDoubleAttr("cx1", cx1);
        rx2 = xml.parseDoubleAttr("rx2", rx2);
        cx2 = xml.parseDoubleAttr("cx2", cx2);
        reset();
    }

    // ── Properties dialog ─────────────────────────────────────────────────────

    @Override public EditInfo getChipEditInfo(int n) {
        switch (n) {
            case 0: return new EditInfo("Rx — Section 1 External Resistor (Ω)",  rx1, 5000, 1e6);
            case 1: return new EditInfo("Cx — Section 1 External Capacitor (F)", cx1, 0,    1e-3);
            case 2: return new EditInfo("Rx — Section 2 External Resistor (Ω)",  rx2, 5000, 1e6);
            case 3: return new EditInfo("Cx — Section 2 External Capacitor (F)", cx2, 0,    1e-3);
        }
        return super.getChipEditInfo(n);
    }

    @Override public void setChipEditValue(int n, EditInfo ei) {
        switch (n) {
            case 0: rx1 = ei.value; return;
            case 1: cx1 = ei.value; return;
            case 2: rx2 = ei.value; return;
            case 3: cx2 = ei.value; return;
        }
        super.setChipEditValue(n, ei);
    }

    // ── Hover info ────────────────────────────────────────────────────────────

    @Override void getInfo(String arr[]) {
        double vcc  = volts[N_VCC];
        double vgnd = volts[N_GND];
        arr[0] = "74HC123";
        arr[1] = "Vcc = " + getVoltageText(vcc)
               + "  GND = " + getVoltageText(vgnd);
        arr[2] = "Section 1 — tW = " + getUnitText(pulseWidth(0), "s")
               + "  Q=" + (pins[N_1Q].value ? "H" : "L");
        arr[3] = "Section 2 — tW = " + getUnitText(pulseWidth(1), "s")
               + "  Q=" + (pins[N_2Q].value ? "H" : "L");
    }
}
