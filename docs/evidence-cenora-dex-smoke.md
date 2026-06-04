# CENORA Utility Conversion Smoke Evidence

## Scope

This document records sanitized internal smoke evidence for LBU₽ -> CENORA and CENORA -> LBU₽ prototype utility conversion.

It is not an audit and not production DEX evidence.

## Forward smoke: LBU₽ -> CENORA

Sanitized internal smoke evidence:

- action: 1 LBU₽ -> 1 CENORA;
- pending id prefix: 3c0c09d7;
- confirmed block prefix: 0003cc67;
- GEN CENORA: 1046 -> 1047;
- locked LBU₽ for CENORA: 1089 -> 1090;
- TokenLedger history: OK.

## Reverse smoke: CENORA -> LBU₽

Sanitized internal smoke evidence:

- action: 1 CENORA -> 1 LBU₽;
- redeem id prefix: 24d5dfbc;
- confirmed block prefix: 000c0516081c;
- pending state: disappeared after canonical confirmation;
- GEN CENORA: 1047 -> 1046;
- locked LBU₽ for CENORA: 1090 -> 1089;
- CENORA redeem index digest: changed consistently and matched mesh.

## Important observation

Confirmed LBU₽ did not increase immediately after the redeem button was pressed.

Spendable LBU₽ was released only after canonical confirmation, which supports that the prototype does not use fake UI-only confirmed balance for reverse conversion.

## Limitations

- internal smoke test only;
- small amount test;
- not an external audit;
- not production financial infrastructure;
- not legal or compliance review;
- not a public token sale;
- no exchange listing claim;
- no guaranteed value or return.

## Next work

- larger soak tests;
- public sanitized demo video;
- external review;
- legal/compliance review before any public game/token utility launch;
- clearer user-facing UX around pending versus confirmed state.
