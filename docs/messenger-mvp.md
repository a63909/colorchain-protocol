# Messenger MVP Foundation

## Summary

After stabilizing the Core v1 prototype, ColorChain has started an application-layer Messenger MVP foundation on top of the core P2P/block propagation system.

The current internal Android/JVM prototype supports signed message envelopes, delivery acknowledgements, local message persistence, and redacted JVM/desktop archival metadata.

A non-minting MessageProofClaim pool has also been introduced as a foundation for future communication-backed participation proofs.

This is an early prototype layer. It does not claim production-grade end-to-end encryption, production messaging readiness, or live LBU₽ rewards for messaging.

## Current internal prototype status

The internal prototype currently includes:

- signed MessageEnvelope;
- DeliveryAck;
- non-minting MessageProofClaim;
- local message, ACK, and claim storage;
- P2P message types:
  - MSG_ENVELOPE
  - MSG_ACK
  - MSG_PROOF_CLAIM
- basic Messenger UI;
- GEN to STD message delivery;
- STD to GEN reverse delivery;
- ACK flow;
- multi-message storage fix;
- restart persistence;
- PC/JVM redacted archive support;
- no plaintext stored in PC/JVM archive/logs;
- Core v1 compatibility preserved.

## What this proves

This milestone shows that ColorChain can support an application-layer communication protocol on top of the existing Core v1 P2P/block foundation.

It extends the project beyond raw block propagation toward user-facing communication flows while preserving the core network architecture.

## What this does not claim

This milestone does not claim:

- production messaging readiness;
- production-grade end-to-end encryption;
- audited messenger deployment or security review;
- live LBU₽ rewards for messages;
- public messenger launch readiness;
- protection against all spam, abuse, Sybil behavior, or metadata leakage.

## MessageProofClaim

MessageProofClaim is currently non-minting.

It is intended as a prototype foundation for future communication-backed participation proofs. It does not currently mint LBU₽, issue rewards, or create spendable balances.

Future work may explore capped communication-backed participation incentives, but only after technical validation, abuse-resistance design, and legal review.

## Privacy and archival scope

The JVM/desktop archive stores redacted messaging metadata.

Plaintext message content should not be written into public archives, PC/JVM logs, or public evidence files.

Public demos and evidence packages must avoid private messages, personal identifiers, device identifiers, IP addresses, seed phrases, private keys, and raw wallet material.

## Reproducible reference demo

A standalone Java reference demo is available:

[docs/reproducible-messenger-demo.md](reproducible-messenger-demo.md)

It demonstrates a minimal local MessageEnvelope -> DeliveryAck -> non-minting MessageProofClaim flow between two reference nodes.

This reference demo is intentionally smaller than the internal Android/JVM prototype and does not claim production secure messaging, end-to-end encryption, real network transport, or LBU₽ rewards.

## Future work

Future milestones include:

- end-to-end encryption;
- stronger message identity and replay protection;
- spam and abuse resistance;
- MessageProofClaim validation hardening;
- privacy-preserving archival design;
- capped communication-backed LBU₽ reward prototype;
- reproducible test scenarios;
- public sanitized messenger demo.
