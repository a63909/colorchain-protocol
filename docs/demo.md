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

## Optional future demo: wallet restore and transaction flow

A future demo may show the repaired wallet restore path and transaction flow in the Android prototype.

This demo must use a test wallet only. Seed phrases, private keys, personal wallet material, and sensitive addresses must be redacted or replaced with sanitized test data.

The goal is to show the restore path and transaction flow in controlled prototype testing, not to make production security claims.

Example trust metadata:

```json
{
  "spam_score": 0.12,
  "duplication_risk": 0.08,
  "manipulation_risk": 0.31,
  "trust_signal": "medium"
}
```
