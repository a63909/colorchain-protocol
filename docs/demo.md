# Demo Plan

This document describes the minimum demo required for grant and investor applications.

## Demo goal

Show that ColorChain is not just an idea, but a working prototype.

The demo should prove that ColorChain can run as an independent mobile-first peer-to-peer network where nodes can connect, exchange information, create or receive blocks, and synchronize state without relying on a central platform or external blockchain ecosystem.

The demo should show:

- at least one mobile node;
- at least one desktop/JVM node;
- peer-to-peer connection;
- message or block propagation;
- synchronization between nodes;
- block/hash/log evidence;
- optional AI trust metadata.

## Demo scenario

1. Start the desktop/JVM node.
2. Start the Android node.
3. Show that nodes connect.
4. Send or create a test message/block.
5. Show that the message/block propagates.
6. Show block hash, height, synchronization log, or UI evidence.
7. Show mock or real AI trust metadata.

## Current public demo

Current public demo:

https://youtu.be/ejcNmULAKG8

What the current demo proves:

- node-to-node block propagation;
- same-hash acceptance;
- Android emulator node participation;
- physical Samsung phone node participation.

The current public demo is evidence of prototype behavior, not a production-readiness claim.

Runnable command-level demo scripts are planned for future public release.

## Optional future demo: wallet restore and transaction flow

A future demo may show the repaired wallet restore path and transaction flow in the Android prototype.

This demo must use a test wallet only. Seed phrases, private keys, personal wallet material, and sensitive addresses must be redacted or replaced with sanitized test data.

The goal is to show the restore path and transaction flow in controlled prototype testing, not to make production security claims.

## Optional future demo: Messenger MVP

A future demo may show the Messenger MVP foundation on top of ColorChain Core.

The demo should use test-only messages and should not expose private user messages, personal identifiers, device IDs, IP addresses, seed phrases, private keys, or raw wallet data.

The demo may show:

- signed message envelope creation;
- GEN to STD delivery;
- STD to GEN reverse delivery;
- DeliveryAck;
- local persistence after restart;
- redacted JVM/desktop archival metadata;
- non-minting MessageProofClaim.

The demo must not claim production messaging security, end-to-end encryption, or live LBU₽ rewards for messaging.

Example trust metadata:

```json
{
  "spam_score": 0.12,
  "duplication_risk": 0.08,
  "manipulation_risk": 0.31,
  "trust_signal": "medium"
}
```
