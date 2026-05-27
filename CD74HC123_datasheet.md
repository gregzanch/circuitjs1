# CD54/74HC123, CD54/74HCT123, CD74HC423, CD74HCT423

## High-Speed CMOS Logic Dual Retriggerable Monostable Multivibrators with Resets

**Texas Instruments** — Data sheet acquired from Harris Semiconductor (SCHS142F)
September 1997 – Revised October 2003

---

## Features

- Overriding Reset Terminates Output Pulse
- Triggering From the Leading or Trailing Edge
- Q and Q̄ Buffered Outputs
- Separate Resets
- Wide Range of Output-Pulse Widths
- Schmitt Trigger on Both Ā and B Inputs
- Fanout (Over Temperature Range)
  - Standard Outputs: 10 LSTTL Loads
  - Bus Driver Outputs: 15 LSTTL Loads
- Wide Operating Temperature Range: −55 °C to 125 °C
- Balanced Propagation Delay and Transition Times
- Significant Power Reduction Compared to LSTTL Logic ICs
- **HC Types**
  - 2 V to 6 V Operation
  - High Noise Immunity: N<sub>IL</sub> = 30%, N<sub>IH</sub> = 30% of V<sub>CC</sub> at V<sub>CC</sub> = 5 V
- **HCT Types**
  - 4.5 V to 5.5 V Operation
  - Direct LSTTL Input Logic Compatibility: V<sub>IL</sub> = 0.8 V (Max), V<sub>IH</sub> = 2 V (Min)
  - CMOS Input Compatibility: I<sub>I</sub> ≤ 1 µA at V<sub>OL</sub>, V<sub>OH</sub>

---

## Description

The 'HC123, 'HCT123, CD74HC423 and CD74HCT423 are dual monostable multivibrators with resets. They are all retriggerable and differ only in that the 123 types can be triggered by a negative-to-positive reset pulse, whereas the 423 types do not have this feature. An external resistor (R<sub>X</sub>) and an external capacitor (C<sub>X</sub>) control the timing and the accuracy for the circuit. Adjustment of R<sub>X</sub> and C<sub>X</sub> provides a wide range of output pulse widths from the Q and Q̄ terminals. Pulse triggering on the Ā and B inputs occurs at a particular voltage level and is not related to the rise and fall times of the trigger pulses.

Once triggered, the output pulse width may be extended by retriggering inputs Ā and B. The output pulse can be terminated by a LOW level on the Reset (R̄) pin. Trailing-edge triggering (Ā) and leading-edge triggering (B) inputs are provided for triggering from either edge of the input pulse. If either Mono is not used, each input on the unused device (Ā, B, and R̄) must be terminated high or low.

The minimum value of external resistance, R<sub>X</sub>, is typically 5 kΩ. The minimum value of external capacitance, C<sub>X</sub>, is 0 pF. The calculation for the pulse width is **t<sub>W</sub> = 0.45 R<sub>X</sub>C<sub>X</sub>** at V<sub>CC</sub> = 5 V.

---

## Ordering Information

| Part Number      | Temp. Range (°C) | Package        |
|------------------|------------------|----------------|
| CD54HC123F3A     | −55 to 125       | 16 Ld CERDIP   |
| CD54HCT123F3A    | −55 to 125       | 16 Ld CERDIP   |
| CD74HC123E       | −55 to 125       | 16 Ld PDIP     |
| CD74HC123M       | −55 to 125       | 16 Ld SOIC     |
| CD74HC123MT      | −55 to 125       | 16 Ld SOIC     |
| CD74HC123M96     | −55 to 125       | 16 Ld SOIC     |
| CD74HC123NSR     | −55 to 125       | 16 Ld SOP      |
| CD74HC123PW      | −55 to 125       | 16 Ld TSSOP    |
| CD74HC123PWR     | −55 to 125       | 16 Ld TSSOP    |
| CD74HC123PWT     | −55 to 125       | 16 Ld TSSOP    |
| CD74HC423E       | −55 to 125       | 16 Ld PDIP     |
| CD74HC423M       | −55 to 125       | 16 Ld SOIC     |
| CD74HC423MT      | −55 to 125       | 16 Ld SOIC     |
| CD74HC423M96     | −55 to 125       | 16 Ld SOIC     |
| CD74HC423NSR     | −55 to 125       | 16 Ld SOP      |
| CD74HCT123E      | −55 to 125       | 16 Ld PDIP     |
| CD74HCT123M      | −55 to 125       | 16 Ld SOIC     |
| CD74HCT123MT     | −55 to 125       | 16 Ld SOIC     |
| CD74HCT123M96    | −55 to 125       | 16 Ld SOIC     |
| CD74HCT423E      | −55 to 125       | 16 Ld PDIP     |
| CD74HCT423MT     | −55 to 125       | 16 Ld SOIC     |
| CD74HCT423M96    | −55 to 125       | 16 Ld SOIC     |

> **NOTE:** When ordering, use the entire part number. The suffixes 96 and R denote tape and reel. The suffix T denotes a small-quantity reel of 250.

> **CAUTION:** These devices are sensitive to electrostatic discharge. Users should follow proper IC Handling Procedures.

---

## Pinout (16-Pin, Top View)

Packages: CD54HC123/CD54HCT123 (CERDIP); CD74HC123 (PDIP, SOIC, SOP, TSSOP); CD74HC423 (PDIP, SOIC, SOP); CD74HCT123/CD74HCT423 (PDIP, SOIC)

| Pin | Signal  | Pin | Signal  |
|-----|---------|-----|---------|
| 1   | 1Ā      | 16  | V<sub>CC</sub> |
| 2   | 1B      | 15  | 1RXCX   |
| 3   | 1R̄      | 14  | 1CX     |
| 4   | 1Q̄      | 13  | 1Q      |
| 5   | 2Q      | 12  | 2Q̄      |
| 6   | 2CX     | 11  | 2R̄      |
| 7   | 2RXCX   | 10  | 2B      |
| 8   | GND     | 9   | 2Ā      |

---

## Functional Diagram

Each package contains two independent monostable multivibrators (MONO 1 and MONO 2). Each section uses an external resistor (R<sub>X</sub>) connected to V<sub>CC</sub> and an external capacitor (C<sub>X</sub>) across the CX / RXCX terminals to set the timing. Each section has inputs Ā (trailing-edge trigger), B (leading-edge trigger), and R̄ (reset), with complementary outputs Q and Q̄.

- **MONO 1:** inputs 1Ā (pin 1), 1B (pin 2), 1R̄ (pin 3); outputs 1Q̄ (pin 4), 1Q (pin 13); timing 1CX (pin 14), 1RXCX (pin 15)
- **MONO 2:** inputs 2Ā (pin 9), 2B (pin 10), 2R̄ (pin 11); outputs 2Q (pin 5), 2Q̄ (pin 12); timing 2CX (pin 6), 2RXCX (pin 7)

### External RC timing connection

For each monostable, the RC network sets the pulse width:

- The external resistor R<sub>X</sub> connects between **V<sub>CC</sub>** and the **RXCX** pin (1RXCX = pin 15, 2RXCX = pin 7).
- The external capacitor C<sub>X</sub> connects between the **CX** pin (1CX = pin 14, 2CX = pin 6) and the **RXCX** pin.
- Thus C<sub>X</sub> sits across CX→RXCX, and R<sub>X</sub> pulls the RXCX node up to V<sub>CC</sub>.

Pulse width: **t<sub>W</sub> = K · R<sub>X</sub> · C<sub>X</sub>**, with K ≈ 0.45 at V<sub>CC</sub> = 5 V. Minimum R<sub>X</sub> ≈ 5 kΩ, minimum C<sub>X</sub> = 0 pF.

---

## Functional Description (Implementation Spec)

This section describes the behavior of **one** monostable (a "one-shot"). The chip has two identical, fully independent copies; replicate this logic per section with the pin mapping above. The intent is to let another implementer reproduce the device in code, HDL, or a discrete-event simulator.

### Signals (per section)

| Name | Direction | Active | Role |
|------|-----------|--------|------|
| `A_n` (Ā) | input | trigger on **falling** edge (HIGH→LOW), gated by `B = HIGH` | trailing-edge trigger |
| `B` | input | trigger on **rising** edge (LOW→HIGH), gated by `A_n = LOW` | leading-edge trigger |
| `R_n` (R̄) | input | active **LOW** (level), plus rising-edge trigger on 123 only | reset / override (and trigger on 123) |
| `Q` | output | — | pulse output (HIGH during pulse) |
| `Q_n` (Q̄) | output | — | complement of `Q` |

All three inputs have **Schmitt-trigger** thresholds on Ā and B, so triggering is level-based and independent of input rise/fall times.

### Core state

The one-shot has one bit of operating state plus a timer:
- `pulse_active` — whether the output pulse is currently being driven.
- `timer` — counts down (or counts up toward `t_W`) the remaining pulse duration. On every (re)trigger the timer is **reloaded** to the full `t_W` (this is what "retriggerable" means).

`t_W = K * R_X * C_X` (K ≈ 0.45 at 5 V). When `timer` expires and no retrigger has occurred, the pulse ends.

### Output convention

- While `pulse_active` is true: `Q = HIGH`, `Q_n = LOW`.
- Otherwise (idle / reset): `Q = LOW`, `Q_n = HIGH`.

### Trigger conditions

A new pulse starts (or an active pulse is retriggered → timer reloaded to `t_W`) when **R̄ is HIGH** AND one of the following edge events occurs:

1. **Leading-edge trigger (B):** `A_n` is LOW and `B` makes a LOW→HIGH transition.
2. **Trailing-edge trigger (Ā):** `B` is HIGH and `A_n` makes a HIGH→LOW transition.
3. **Reset-recovery trigger (123 types ONLY):** `A_n` is LOW and `B` is HIGH and `R̄` makes a LOW→HIGH transition. The **423 types do not have this trigger** — releasing reset never starts a pulse on a 423.

These correspond directly to the truth-table rows marked with a pulse (⊓ on Q):
- `Ā=L, B=↑, R̄=H` → pulse  (leading edge)
- `Ā=↓, B=H, R̄=H` → pulse  (trailing edge)
- `Ā=L, B=H, R̄=↑` → pulse  (123 only)

### Override / inhibit conditions

- **Reset override:** Whenever `R̄ = LOW`, the output pulse is immediately terminated: `pulse_active = false`, `Q = LOW`, `Q_n = HIGH`. Reset is dominant over any trigger. (Truth-table rows `X, X, L → Q=L, Q̄=H`.)
- **Static non-trigger levels:** With `R̄ = HIGH`, holding `Ā = HIGH` (with B don't-care) or holding `B = LOW` (with Ā don't-care) keeps the device idle (`Q = LOW, Q̄ = H`) — these are static levels, not edges, so they do not start a pulse. (Truth-table rows `H, X, H` and `X, L, H`.)

### Retriggering

If a valid trigger event (condition 1, 2, or 3 above) occurs **while a pulse is already active**, the timer is reloaded to the full `t_W` and the pulse continues without going LOW — the output is simply extended. For retriggering to lengthen the pulse, successive active-going trigger edges must be separated by at least the minimum **retrigger time** `t_rT` (≈ 50 ns typ at 5 V with R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 0).

### Timing prerequisites (for a faithful timed model)

- Minimum input pulse width on Ā, B, R̄ (`t_WL`/`t_WH`): ≈ 20 ns at 5 V (HC/HCT).
- Ā/B hold time (`t_H`): ≈ 10 ns at 5 V.
- Reset removal time (`t_REM`): ≈ 10 ns at 5 V.
- Trigger-to-output propagation delay (`t_PLH`/`t_PHL`): tens of ns (see Switching Specifications).
- A trigger arriving before these minimums are met may be ignored or produce an indeterminate output in real hardware.

### Reference pseudocode (single section)

```text
state:
  pulse_active = false
  timer        = 0
  prev_A, prev_B, prev_R = current input levels

on each time step or input event:
  # 1. Reset is dominant and level-sensitive
  if R == LOW:
      pulse_active = false
      Q = LOW; Q_bar = HIGH
      # fall through; no trigger can occur while R is LOW

  else:  # R == HIGH
      triggered = false

      # leading-edge trigger: B rising while A_n low
      if A == LOW and rising_edge(B):  triggered = true
      # trailing-edge trigger: A_n falling while B high
      if B == HIGH and falling_edge(A): triggered = true
      # 123 ONLY: reset-recovery trigger, R rising while A_n low, B high
      if DEVICE_IS_123 and A == LOW and B == HIGH and rising_edge(R):
          triggered = true

      if triggered:
          pulse_active = true
          timer = t_W            # reload to full width (retriggerable)

  # 2. Timer countdown when a pulse is active
  if pulse_active:
      Q = HIGH; Q_bar = LOW
      timer -= dt
      if timer <= 0:
          pulse_active = false
          Q = LOW; Q_bar = HIGH
  else if R == HIGH:
      Q = LOW; Q_bar = HIGH

  prev_A, prev_B, prev_R = A, B, R   # for next-step edge detection
```

`rising_edge(x)` = (prev_x == LOW and x == HIGH); `falling_edge(x)` = (prev_x == HIGH and x == LOW). Set `DEVICE_IS_123 = true` for HC123/HCT123 and `false` for HC423/HCT423.

### Unused sections

If a monostable is unused, tie each of its inputs (Ā, B, R̄) to a fixed HIGH or LOW — they must not float.

---

## Truth Table

### CD74HC/HCT123

| Ā | B | R̄ | Q | Q̄ |
|---|---|---|---|----|
| H | X | H | L | H  |
| X | L | H | L | H  |
| L | ↑ | H | ⊓ | ⊔  |
| ↓ | H | H | ⊓ | ⊔  |
| X | X | L | L | H  |
| L | H | ↑ | ⊓ | ⊔  |

### CD74HC/HCT423

| Ā | B | R̄ | Q | Q̄ |
|---|---|---|---|----|
| H | X | H | L | H  |
| X | L | H | L | H  |
| L | ↑ | H | ⊓ | ⊔  |
| ↓ | H | H | ⊓ | ⊔  |
| X | X | L | L | H  |

> H = High Voltage Level, L = Low Voltage Level, X = Don't Care, ↑ = LOW-to-HIGH transition, ↓ = HIGH-to-LOW transition, ⊓ / ⊔ = one output pulse.

---

## Absolute Maximum Ratings

| Parameter | Symbol | Rating |
|-----------|--------|--------|
| DC Supply Voltage | V<sub>CC</sub> | −0.5 V to 7 V |
| DC Input Diode Current (V<sub>I</sub> < −0.5 V or V<sub>I</sub> > V<sub>CC</sub> + 0.5 V) | I<sub>IK</sub> | ±20 mA |
| DC Output Diode Current (V<sub>O</sub> < −0.5 V or V<sub>O</sub> > V<sub>CC</sub> + 0.5 V) | I<sub>OK</sub> | ±20 mA |
| DC Output Source/Sink Current per Output Pin (−0.5 V < V<sub>O</sub> < V<sub>CC</sub> + 0.5 V) | I<sub>O</sub> | ±25 mA |
| DC V<sub>CC</sub> or Ground Current | I<sub>CC</sub> / I<sub>GND</sub> | ±50 mA |

> **CAUTION:** Stresses above those listed in "Absolute Maximum Ratings" may cause permanent damage to the device. This is a stress-only rating; operation at these or any other conditions above those indicated in the operational sections of the specification is not implied.

---

## Thermal Information

| Parameter | Value |
|-----------|-------|
| Package Thermal Impedance θ<sub>JA</sub> — E (PDIP) | 67 °C/W |
| Package Thermal Impedance θ<sub>JA</sub> — M (SOIC) | 73 °C/W |
| Package Thermal Impedance θ<sub>JA</sub> — NS (SOP) | 64 °C/W |
| Package Thermal Impedance θ<sub>JA</sub> — PW (TSSOP) | 108 °C/W |
| Maximum Junction Temperature | 150 °C |
| Maximum Storage Temperature Range | −65 °C to 150 °C |
| Maximum Lead Temperature (Soldering 10 s, SOIC lead tips only) | 300 °C |

> **NOTE:** Package thermal impedance is calculated in accordance with JESD 51-7.

---

## Operating Conditions

| Parameter | Condition | Value |
|-----------|-----------|-------|
| Temperature Range (T<sub>A</sub>) | — | −55 °C to 125 °C |
| Supply Voltage Range, V<sub>CC</sub> | HC Types | 2 V to 6 V |
| Supply Voltage Range, V<sub>CC</sub> | HCT Types | 4.5 V to 5.5 V |
| DC Input or Output Voltage, V<sub>I</sub>, V<sub>O</sub> | — | 0 V to V<sub>CC</sub> |
| Input Rise and Fall Time | 2 V | 1000 ns (Max) |
| Input Rise and Fall Time | 4.5 V | 500 ns (Max) |
| Input Rise and Fall Time | 6 V | 400 ns (Max) |

---

## DC Electrical Specifications

### HC Types

| Parameter | Symbol | Test Conditions (V<sub>I</sub>, I<sub>O</sub>) | V<sub>CC</sub> (V) | 25 °C Min | 25 °C Typ | 25 °C Max | −40 to 85 °C Min/Max | −55 to 125 °C Min/Max | Units |
|-----------|--------|------------------|---------|----------|----------|----------|--------------------|---------------------|-------|
| High-Level Input Voltage | V<sub>IH</sub> | — | 2 | 1.5 | — | — | 1.5 / — | 1.5 / — | V |
| | | | 4.5 | 3.15 | — | — | 3.15 / — | 3.15 / — | V |
| | | | 6 | 4.2 | — | — | 4.2 / — | 4.2 / — | V |
| Low-Level Input Voltage | V<sub>IL</sub> | — | 2 | — | — | 0.5 | — / 0.5 | — / 0.5 | V |
| | | | 4.5 | — | — | 1.35 | — / 1.35 | — / 1.35 | V |
| | | | 6 | — | — | 1.8 | — / 1.8 | — / 1.8 | V |
| High-Level Output Voltage (CMOS Loads) | V<sub>OH</sub> | V<sub>IH</sub> or V<sub>IL</sub>, −0.02 mA | 2 | 1.9 | — | — | 1.9 / — | 1.9 / — | V |
| | | −0.02 mA | 4.5 | 4.4 | — | — | 4.4 / — | 4.4 / — | V |
| | | −0.02 mA | 6 | 5.9 | — | — | 5.9 / — | 5.9 / — | V |
| High-Level Output Voltage (TTL Loads) | V<sub>OH</sub> | −4 mA | 4.5 | 3.98 | — | — | 3.84 / — | 3.7 / — | V |
| | | −5.2 mA | 6 | 5.48 | — | — | 5.34 / — | 5.2 / — | V |
| Low-Level Output Voltage (CMOS Loads) | V<sub>OL</sub> | V<sub>IH</sub> or V<sub>IL</sub>, 0.02 mA | 2 | — | — | 0.1 | — / 0.1 | — / 0.1 | V |
| | | 0.02 mA | 4.5 | — | — | 0.1 | — / 0.1 | — / 0.1 | V |
| | | 0.02 mA | 6 | — | — | 0.1 | — / 0.1 | — / 0.1 | V |
| Low-Level Output Voltage (TTL Loads) | V<sub>OL</sub> | 4 mA | 4.5 | — | — | 0.26 | — / 0.33 | — / 0.4 | V |
| | | 5.2 mA | 6 | — | — | 0.26 | — / 0.33 | — / 0.4 | V |
| Input Leakage Current | I<sub>I</sub> | V<sub>CC</sub> or GND | 6 | — | — | ±0.1 | — / ±1 | — / ±1 | µA |
| Quiescent Device Current | I<sub>CC</sub> | V<sub>CC</sub> or GND, V<sub>I</sub> = 0 | 6 | — | — | 8 | — / 80 | — / 160 | µA |

### HCT Types

| Parameter | Symbol | Test Conditions (V<sub>I</sub>, I<sub>O</sub>) | V<sub>CC</sub> (V) | 25 °C Min | 25 °C Typ | 25 °C Max | −40 to 85 °C Min/Max | −55 to 125 °C Min/Max | Units |
|-----------|--------|------------------|---------|----------|----------|----------|--------------------|---------------------|-------|
| High-Level Input Voltage | V<sub>IH</sub> | — | 4.5 to 5.5 | 2 | — | — | 2 / — | 2 / — | V |
| Low-Level Input Voltage | V<sub>IL</sub> | — | 4.5 to 5.5 | — | — | 0.8 | — / 0.8 | — / 0.8 | V |
| High-Level Output Voltage (CMOS Loads) | V<sub>OH</sub> | V<sub>IH</sub> or V<sub>IL</sub>, −0.02 mA | 4.5 | 4.4 | — | — | 4.4 / — | 4.4 / — | V |
| High-Level Output Voltage (TTL Loads) | V<sub>OH</sub> | −4 mA | 4.5 | 3.98 | — | — | 3.84 / — | 3.7 / — | V |
| Low-Level Output Voltage (CMOS Loads) | V<sub>OL</sub> | V<sub>IH</sub> or V<sub>IL</sub>, 0.02 mA | 4.5 | — | — | 0.1 | — / 0.1 | — / 0.1 | V |
| Low-Level Output Voltage (TTL Loads) | V<sub>OL</sub> | 4 mA | 4.5 | — | — | 0.26 | — / 0.33 | — / 0.4 | V |
| Input Leakage Current | I<sub>I</sub> | V<sub>CC</sub> and GND | 5.5 | — | — | ±0.1 | — / ±1 | — / ±1 | µA |
| Quiescent Device Current | I<sub>CC</sub> | V<sub>CC</sub> or GND, V<sub>I</sub> = 0 | 5.5 | — | — | 8 | — / 80 | — / 160 | µA |
| Additional Quiescent Device Current per Input Pin (1 Unit Load) | ΔI<sub>CC</sub> (Note 2) | V<sub>CC</sub> − 2.1 | 4.5 to 5.5 | — | 100 | 360 | — / 450 | — / 490 | µA |

> **NOTE 2:** For dual-supply systems, theoretical worst case (V<sub>I</sub> = 2.4 V, V<sub>CC</sub> = 5.5 V) specification is 1.8 mA.

### HCT Input Loading Table

| Input | Unit Loads |
|-------|-----------|
| All   | 0.35      |

> **NOTE:** Unit Load is the ΔI<sub>CC</sub> limit specified in the DC Electrical Table, e.g. 360 µA max at 25 °C.

---

## Prerequisite for Switching Specifications

### HC Types

| Parameter | Symbol | V<sub>CC</sub> (V) | 25 °C Min | −40 to 85 °C Min | −55 to 125 °C Min | Units |
|-----------|--------|---------|----------|------------------|-------------------|-------|
| Minimum Input Pulse Width (Ā) | t<sub>WL</sub> | 2 | 100 | 125 | 150 | ns |
| | | 4.5 | 20 | 25 | 30 | ns |
| | | 6 | 17 | 21 | 26 | ns |
| Minimum Input Pulse Width (B) | t<sub>WH</sub> | 2 | 100 | 125 | 150 | ns |
| | | 4.5 | 20 | 25 | 30 | ns |
| | | 6 | 17 | 21 | 26 | ns |
| Minimum Input Pulse Width (R̄) | t<sub>WL</sub> | 2 | 100 | 125 | 150 | ns |
| | | 4.5 | 20 | 25 | 30 | ns |
| | | 6 | 17 | 21 | 26 | ns |
| Ā and B Hold Time | t<sub>H</sub> | 2 | 50 | 65 | 75 | ns |
| | | 4.5 | 10 | 13 | 15 | ns |
| | | 6 | 9 | 11 | 13 | ns |
| Reset Removal Time | t<sub>REM</sub> | 2 | 50 | 65 | 75 | ns |
| | | 4.5 | 10 | 13 | 15 | ns |
| | | 6 | 9 | 11 | 13 | ns |
| Retrigger Time (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 0) | t<sub>rT</sub> | 5 | 50 (typ) | 63 (typ) | 76 (typ) | ns |
| Output Pulse Width (Q or Q̄, R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 nF) | t<sub>W</sub> | 5 | 40–50 | 38.7–51.3 | 38.2–51.8 | µs |

### HCT Types

| Parameter | Symbol | V<sub>CC</sub> (V) | 25 °C Min | −40 to 85 °C Min | −55 to 125 °C Min | Units |
|-----------|--------|---------|----------|------------------|-------------------|-------|
| Minimum Input Pulse Width (Ā) | t<sub>WL</sub> | 5 | 20 | 25 | 30 | ns |
| Minimum Input Pulse Width (B) | t<sub>WH</sub> | 5 | 20 | 25 | 30 | ns |
| Minimum Input Pulse Width (R̄) | t<sub>WL</sub> | 5 | 20 | 25 | 30 | ns |
| Ā and B Hold Time | t<sub>H</sub> | 5 | 10 | 13 | 15 | ns |
| Reset Removal Time | t<sub>REM</sub> | 5 | 10 | 13 | 15 | ns |
| Retrigger Time (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 0) (Note 3) | t<sub>rT</sub> | 5 | 50 (typ) | 63 (typ) | 76 (typ) | ns |
| Output Pulse Width (Q or Q̄, R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 nF) | t<sub>W</sub> | 5 | 40–50 | 38.7–51.3 | 38.2–51.8 | µs |

> **NOTE 3:** Time to trigger depends on the values of R<sub>X</sub> and C<sub>X</sub>. The output pulse width can only be extended when the time between the active-going edges of the trigger input pulses meets the minimum retrigger time requirement.

---

## Switching Specifications

(Input t<sub>r</sub>, t<sub>f</sub> = 6 ns, R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 0)

### HC Types

| Parameter | Symbol | Test Conditions | V<sub>CC</sub> (V) | 25 °C Typ/Max | −40 to 85 °C Max | −55 to 125 °C Max | Units |
|-----------|--------|-----------------|---------|--------------|------------------|-------------------|-------|
| Trigger Propagation Delay (Ā, B, R̄ to Q) | t<sub>PLH</sub> | C<sub>L</sub> = 50 pF | 2 | 300 | 375 | 450 | ns |
| | | C<sub>L</sub> = 50 pF | 4.5 | 60 | 75 | 90 | ns |
| | | C<sub>L</sub> = 15 pF | 5 | 25 (typ) | — | — | ns |
| | | C<sub>L</sub> = 50 pF | 6 | 51 | 64 | 76 | ns |
| Trigger Propagation Delay (Ā, B, R̄ to Q̄) | t<sub>PHL</sub> | C<sub>L</sub> = 50 pF | 2 | 320 | 400 | 480 | ns |
| | | C<sub>L</sub> = 50 pF | 4.5 | 64 | 80 | 96 | ns |
| | | C<sub>L</sub> = 15 pF | 5 | 26 (typ) | — | — | ns |
| | | C<sub>L</sub> = 50 pF | 6 | 54 | 68 | 82 | ns |
| Reset Propagation Delay (R̄ to Q or Q̄) | t<sub>PHL</sub>, t<sub>PLH</sub> | C<sub>L</sub> = 50 pF | 2 | 215 | 270 | 325 | ns |
| | | | 4.5 | 43 | 54 | 65 | ns |
| | | | 6 | 37 | 46 | 55 | ns |
| Output Transition Time | t<sub>THL</sub>, t<sub>TLH</sub> | C<sub>L</sub> = 50 pF | 2 | 75 | 95 | 110 | ns |
| | | | 4.5 | 15 | 19 | 22 | ns |
| | | | 6 | 13 | 16 | 19 | ns |
| Output Pulse Width (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 nF) | — | — | 5 | 45 (typ) | — | — | µs |
| Pulse Width Match Between Circuits in Same Package (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 pF) | — | — | 5 | ±2 (typ) | — | — | % |
| Power Dissipation Capacitance (Note 4) | C<sub>PD</sub> | C<sub>L</sub> = 15 pF | 5 | — | — | — | pF |
| Input Capacitance | C<sub>IN</sub> | C<sub>L</sub> = 50 pF | — | 10 | 10 | 10 | pF |

### HCT Types

| Parameter | Symbol | Test Conditions | V<sub>CC</sub> (V) | 25 °C Typ/Max | −40 to 85 °C Max | −55 to 125 °C Max | Units |
|-----------|--------|-----------------|---------|--------------|------------------|-------------------|-------|
| Trigger Propagation Delay (Ā, B, R̄ to Q) | t<sub>PLH</sub> | C<sub>L</sub> = 50 pF | 4.5 | 60 | 75 | 90 | ns |
| | | C<sub>L</sub> = 15 pF | 5 | 25 (typ) | — | — | ns |
| Trigger Propagation Delay (Ā, B, R̄ to Q̄) | t<sub>PHL</sub> | C<sub>L</sub> = 50 pF | 4.5 | 68 | 85 | 102 | ns |
| | | C<sub>L</sub> = 15 pF | 5 | 27 (typ) | — | — | ns |
| Reset Propagation Delay (R̄ to Q or Q̄) | t<sub>PHL</sub>, t<sub>PLH</sub> | C<sub>L</sub> = 50 pF | 4.5 | 48 | 60 | 72 | ns |
| Output Transition Time | t<sub>THL</sub>, t<sub>TLH</sub> | C<sub>L</sub> = 50 pF | 4.5 | 15 | 19 | 22 | ns |
| Output Pulse Width (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 nF) | — | — | 5 | 45 (typ) | — | — | µs |
| Pulse Width Match Between Circuits in Same Package (R<sub>X</sub> = 10 kΩ, C<sub>X</sub> = 10 pF) | — | — | 5 | ±2 (typ) | — | — | % |
| Input Capacitance | C<sub>IN</sub> | C<sub>L</sub> = 50 pF | — | 10 | 10 | 10 | pF |

> **NOTE 4:** C<sub>PD</sub> is used to determine the dynamic power consumption, per multivibrator:
>
> P<sub>D</sub> = (C<sub>PD</sub> + C<sub>X</sub>) V<sub>CC</sub>² f<sub>i</sub> + Σ(C<sub>L</sub> V<sub>CC</sub>² f<sub>O</sub>)
>
> where f<sub>i</sub> = input frequency, f<sub>O</sub> = output frequency, C<sub>L</sub> = output load capacitance, C<sub>X</sub> = external capacitance, V<sub>CC</sub> = supply voltage, assuming f<sub>i</sub> ≪ 1/t<sub>W</sub>.

---

## Test Circuits and Waveforms

- **Figure 1:** Output pulse control using Reset input (R̄) pulse for 123.
- **Figure 2:** Output pulse control using Reset input (R̄) for 423.
- **Figure 3:** Triggering of one-shot by input Ā or input B for a period t<sub>W</sub> (also illustrates retrigger pulse operation for 123 and 423).
- **Figure 4:** Typical output pulse width as a function of C<sub>X</sub> for R<sub>X</sub> = 10 kΩ and 100 kΩ (V<sub>CC</sub> = 5 V, T<sub>A</sub> = 25 °C).
- **Figure 5:** Typical "K" factor as a function of V<sub>CC</sub> (C<sub>X</sub> = 10 nF, R<sub>X</sub> = 10 kΩ to 100 kΩ, T<sub>A</sub> = 25 °C).

---

## Pulse Width Calculation

The output pulse width is given by:

**t<sub>W</sub> = 0.45 R<sub>X</sub> C<sub>X</sub>** (at V<sub>CC</sub> = 5 V)

The "K" factor (Figure 5) adjusts the calculation across the supply-voltage range, where t<sub>W</sub> = K · R<sub>X</sub> · C<sub>X</sub>. Minimum R<sub>X</sub> ≈ 5 kΩ; minimum C<sub>X</sub> = 0 pF.

---

## Package / Material Information (Summary)

Active production part numbers, all −55 °C to 125 °C, RoHS, NIPDAU lead finish (per TI Package Option Addendum):

| Orderable Part | Package | Pins | Pkg Qty / Carrier |
|----------------|---------|------|-------------------|
| CD74HC123E     | PDIP (N) | 16 | 25 / Tube |
| CD74HC123M96   | SOIC (D) | 16 | 2500 / Large T&R |
| CD74HC123NSR   | SOP (NS) | 16 | 2000 / Large T&R |
| CD74HC123PWR   | TSSOP (PW) | 16 | 2000 / Large T&R |
| CD74HC423E     | PDIP (N) | 16 | 25 / Tube |
| CD74HC423M96   | SOIC (D) | 16 | 2500 / Large T&R |
| CD74HC423NSR   | SOP (NS) | 16 | 2000 / Large T&R |
| CD74HCT123E    | PDIP (N) | 16 | 25 / Tube |
| CD74HCT123M96  | SOIC (D) | 16 | 2500 / Large T&R |
| CD74HCT423E    | PDIP (N) | 16 | 25 / Tube |
| CD74HCT423M96  | SOIC (D) | 16 | 2500 / Large T&R |

CERDIP (military, −55 °C to 125 °C) versions: CD54HC123F3A, CD54HCT123F3A, with SNPB lead finish.

Available package outlines include NS0016A (SOP, 2.00 mm max height) and PW0016A (TSSOP, 1.2 mm max height, JEDEC MO-153).

---

*Copyright © 2003, 2026 Texas Instruments Incorporated. This is a condensed markdown reproduction of the TI datasheet (SCHS142F) for reference; consult the original PDF for full mechanical drawings, board layouts, and legal disclaimers.*
