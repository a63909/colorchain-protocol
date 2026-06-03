# Messenger E2E Encrypted-v1 Evidence Note

## Summary

This document summarizes the current evidence for the experimental Messenger E2E encrypted-v1 path in the internal Android/JVM ColorChain prototype.

The Messenger layer remains prototype-stage and unaudited. This document does not claim production-grade secure messaging, audited encryption, complete metadata privacy, or public launch readiness.

## Current internal milestone

Internal prototype validation indicates:

- Messenger MVP foundation is integrated on top of the ColorChain P2P core;
- message payloads can use the experimental encrypted-v1 path;
- PC/JVM relay/archive nodes can store replayable encrypted envelopes;
- offline catch-up can deliver archived encrypted envelopes to the recipient;
- the recipient node can decrypt and store the message locally;
- DeliveryAck can be returned and applied;
- PC/JVM archive checks indicate plaintextArchived=false in controlled tests.

## Live/offline flow validated internally

A controlled internal live/offline scenario demonstrated:

1. STD was offline.
2. GEN sent an encrypted-v1 message.
3. A PC/JVM node stored a replayable encrypted envelope.
4. STD returned online.
5. STD sent MSG_CATCHUP_REQUEST.
6. PC/JVM returned MSG_CATCHUP_RESPONSE.
7. STD decrypted and stored the message locally.
8. STD sent ACK.
9. GEN showed the message as ACKED.

This demonstrates the intended relay/archive/catch-up behavior for encrypted message envelopes.

## Multi-relay archival behavior

Controlled internal testing also indicates:

- PC #1 can archive encrypted-v1 envelopes;
- PC #2 can participate in catch-up serving;
- the recipient can receive, decrypt, store, and acknowledge the message;
- relay/ACK storms were not observed in the checked scenario;
- plaintextArchived=false was reported for the checked archive path.

## What this proves

This evidence supports the claim that the internal Android/JVM Messenger MVP has an experimental E2E encrypted-v1 path that can support encrypted relay/archive/catch-up behavior in controlled testing.

It strengthens the Messenger MVP beyond plain-base64 prototype transport and supports the direction toward privacy-preserving offline delivery.

## What this does not claim

This evidence does not claim:

- production-grade secure messaging;
- audited end-to-end encryption;
- formal cryptographic review;
- complete metadata privacy;
- protection against all replay, spam, Sybil, or abuse scenarios;
- public messenger launch readiness;
- live LBU₽ rewards or spendable balances for messaging.

## Safety and publication policy

Public evidence must not include:

- real private message bodies;
- seed phrases;
- private keys;
- wallet material;
- IP addresses;
- device identifiers;
- raw logs containing operational endpoints or private data.

Any future public demo should use test-only messages and should avoid exposing private content or operational identifiers.

## Current status label

Suggested internal/public label:

```text
MESSENGER_E2E_ENCRYPTED_V1_INTERNAL_VALIDATION_OK
```

Suggested cautious wording:

```text
The internal Android/JVM Messenger MVP includes an experimental E2E encrypted-v1 path validated in controlled live/offline tests. It remains prototype-stage and unaudited, and should not be described as production-grade secure messaging.
```
