# Messenger MVP Foundation

## Summary

After stabilizing the Core v1 prototype, ColorChain has started an application-layer Messenger MVP foundation on top of the core P2P/block propagation system.

The current internal Android/JVM prototype supports signed message envelopes, delivery acknowledgements, experimental E2E encrypted-v1 message payloads, local message persistence, and redacted JVM/desktop archival metadata.

A non-minting MessageProofClaim pool has also been introduced as a foundation for future communication-backed participation proofs.

This is an early prototype layer. It does not claim production-grade end-to-end encryption, production messaging readiness, audited messenger security, or live LBU₽ rewards for messaging.

## Current internal prototype status

The internal prototype currently includes:

- signed MessageEnvelope;
- DeliveryAck;
- experimental E2E encrypted-v1 payload path;
- non-minting MessageProofClaim;
- local message, ACK, and claim storage;
- P2P message types:
  - MSG_ENVELOPE
  - MSG_ACK
  - MSG_PROOF_CLAIM
- MSG_CATCHUP_REQUEST / MSG_CATCHUP_RESPONSE foundation for offline catch-up;
- basic Messenger UI;
- GEN to STD message delivery;
- STD to GEN reverse delivery;
- ACK flow;
- multi-message storage fix;
- restart persistence;
- PC/JVM encrypted replay/archive participation;
- PC/JVM redacted archive support;
- no plaintext stored in PC/JVM archive/logs in controlled tests;
- Core v1 compatibility preserved.

## Experimental E2E encrypted-v1 path

The internal Messenger MVP now includes an experimental E2E encrypted-v1 path.

In controlled live/offline tests:

- GEN sent an encrypted-v1 message envelope;
- PC/JVM archive nodes stored replayable encrypted envelope metadata;
- STD returned online and requested catch-up;
- PC/JVM returned a catch-up response;
- STD decrypted and stored the message locally;
- STD sent an ACK;
- GEN applied the ACK;
- PC/JVM archive checks reported plaintextArchived=false.

This means relay/archive nodes are intended to store and replay encrypted envelopes rather than plaintext message bodies.

This remains prototype-stage and unaudited. It should not be described as a production secure messenger, production-grade encrypted messaging, or complete protection against all metadata leakage, abuse, Sybil behavior, or implementation vulnerabilities.

## What this proves

This milestone shows that ColorChain can support an application-layer communication protocol on top of the existing Core v1 P2P/block foundation.

It extends the project beyond raw block propagation toward user-facing communication flows while preserving the core network architecture.

The E2E encrypted-v1 path strengthens the Messenger MVP direction by allowing relay/archive participation without plaintext message-body archival in controlled tests.

## What this does not claim

This milestone does not claim:

- production messaging readiness;
- production-grade end-to-end encryption;
- audited messenger deployment or security review;
- live LBU₽ rewards for messages;
- public messenger launch readiness;
- protection against all spam, abuse, Sybil behavior, metadata leakage, or implementation bugs;
- that relay/archive metadata is fully private.

## MessageProofClaim

MessageProofClaim is currently non-minting.

It is intended as a prototype foundation for future communication-backed participation proofs. It does not currently mint LBU₽, issue rewards, or create spendable balances.

Future work may explore capped communication-backed participation incentives, but only after technical validation, abuse-resistance design, and legal review.

## Privacy and archival scope

The JVM/desktop archive stores encrypted envelope metadata and redacted messaging metadata.

Plaintext message content should not be written into public archives, PC/JVM logs, or public evidence files.

Public demos and evidence packages must avoid private messages, personal identifiers, device identifiers, IP addresses, seed phrases, private keys, and raw wallet material.

## Reproducible reference demo

A standalone Java reference demo is available:

[docs/reproducible-messenger-demo.md](reproducible-messenger-demo.md)

It demonstrates a minimal local MessageEnvelope -> DeliveryAck -> non-minting MessageProofClaim flow between two reference nodes.

It also includes negative checks for a tampered envelope, a wrong ACK, and non-minting/non-spendable proof-claim behavior.

This reference demo is intentionally smaller than the internal Android/JVM prototype and does not claim production secure messaging, end-to-end encryption, real network transport, or LBU₽ rewards.

## E2E evidence note

A public evidence note for the internal E2E encrypted-v1 path is available here:

[docs/evidence-messenger-e2e-v1.md](evidence-messenger-e2e-v1.md)

## Future work

Future milestones include:

- production hardening of the experimental E2E encrypted-v1 path;
- stronger message identity and replay protection;
- spam and abuse resistance;
- MessageProofClaim validation hardening;
- privacy-preserving archival design;
- capped communication-backed LBU₽ reward prototype;
- reproducible test scenarios;
- public sanitized messenger demo;
- external security review when the messenger layer is mature enough.
