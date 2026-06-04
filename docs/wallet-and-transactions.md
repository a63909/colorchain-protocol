# Wallet and Transactions

This document provides high-level design notes for wallet restore and transaction flow in the ColorChain Android prototype.

## Scope

The Android prototype includes wallet-related functionality needed to support a mobile-first peer-to-peer network utility. The current internal prototype has repaired and validated:

- seed phrase restore / wallet recovery path;
- transaction flow;
- local wallet state visibility;
- transaction-related state changes in controlled testing.

These features are part of the internal Android prototype. They are not presented here as audited production wallet infrastructure.

## Seed restore

Seed phrase restore allows a test wallet identity to be recovered through the Android prototype restore path.

The expected behavior is:

- a test seed phrase is entered through the restore flow;
- the prototype restores the expected wallet identity;
- the restored state is visible in the application;
- private material is not logged or published.

## Transaction flow

The transaction flow supports prototype-level transaction creation and validation behavior.

The expected behavior is:

- a transaction can be created in the Android prototype;
- the transaction can be submitted, signed, or broadcast internally;
- the transaction can be reflected in a block or local state transition;
- sender and recipient state can be inspected in controlled testing.

## Limitations

The current implementation should be described as prototype-stage.

It is not a production wallet release, security audit, or guarantee of safety for real-value assets. Public evidence should avoid raw wallet data, personal addresses, private keys, seed phrases, device identifiers, and operational endpoints.

## Mobile network utility

Repairing seed restore and transaction flow strengthens the path toward user-controlled mobile network utility. It supports the longer-term goal of letting users recover local identity and exercise network actions from mobile devices without relying on a central platform.

## CENORA utility conversion

CENORA utility conversion is documented separately:

- [CENORA Utility Flow](cenora-utility-flow.md)
- [CENORA Utility Conversion Smoke Evidence](evidence-cenora-dex-smoke.md)

This is prototype-stage utility conversion evidence. It is not a production DEX, audited financial system, public token sale, or exchange listing claim.

## Safety note

Do not reuse demo seed phrases. No real wallet seed/private key is published.
