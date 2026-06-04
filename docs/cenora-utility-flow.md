# CENORA Utility Flow

## Summary

CENORA is a prototype utility asset connected to ColorChain's internal LBU₽ lock/redeem mechanics.

It is part of the internal Android/JVM prototype and is documented as a prototype-stage utility conversion flow, not as a production DEX, exchange product, public token sale, or audited financial system.

## Forward path: LBU₽ -> CENORA

The forward utility path currently follows this prototype pattern:

- the user locks LBU₽ into a CENORA vault mechanism;
- the canonical transaction is observed by TokenLedger;
- the current prototype rate is 1 LBU₽ -> 1 CENORA;
- CENORA balance is derived from canonical lock state.

This document does not publish internal wallet addresses, private wallet material, seed phrases, device identifiers, or operational endpoints.

## Reverse path: CENORA -> LBU₽

The reverse utility path currently follows this prototype pattern:

- the user creates a signed CENORA redeem request;
- the request enters pending state;
- a miner includes a valid redeem in a canonical block;
- validators and rebuild logic can replay the result deterministically;
- vault UTXO release creates normal spendable LBU₽ only after canonical confirmation;
- the UI must not fake confirmed LBU₽ before canonical confirmation.

## What this demonstrates

- prototype utility conversion;
- canonical confirmation requirement;
- TokenLedger-derived CENORA accounting;
- deterministic redeem direction;
- no immediate fake confirmed balance after button press.

## What this does not claim

This documentation does not claim:

- not production DEX readiness;
- no exchange listing;
- no public token sale;
- no audited financial product status;
- no legal or compliance readiness;
- no guaranteed value or return;
- no mainnet production readiness.

## Relation to ColorChain

CENORA is an application and economic utility layer around the ColorChain core.

The core protocol remains focused on P2P synchronization, block/state validation, UTXO wallet behavior, and application-layer utility flows.
