# Evidence: STD to Physical GEN Block Propagation

## Summary

This document summarizes a successful diagnostic propagation run showing that a locally accepted STD block was sent over the network, received by a physical GEN node, parsed as a raw block, and accepted.

This is sanitized evidence. Raw logs are not published because they may contain IP addresses, device identifiers, and other operational details.

## Result

Classification: PASS

A block created and accepted on STD was observed on physical GEN with the same hash and accepted there.

## Block hash

000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af

## Evidence chain

1. STD locally accepted the mined block.

Sanitized log:

[PROP-PROBE][LOCAL_ACCEPT] hash=000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af source=LOCALLY_MINED.batch willBroadcast=true

2. STD sent an outbound WebSocket payload containing the same block hash.

Sanitized log:

[PROP-PROBE][WS_SEND] containsHash=true hash=000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af to=ws://<physical-gen-peer>:8081

3. Physical GEN received an inbound message containing the same block hash.

Sanitized log:

[PROP-PROBE][INBOUND_RAW] extractedHash=000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af

4. Physical GEN parsed the inbound data as a raw block with the same hash.

Sanitized log:

[PROP-PROBE][INBOUND_BLOCK_PARSED] type=RAW_BLOCK hash=000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af

5. Physical GEN accepted the block.

Sanitized log:

[PROP-PROBE][INBOUND_DECISION] hash=000b32f59a85bbb9677199e43799a7672bc8e984e2066162523a3defaf1198af result=accepted

## What this proves

This run demonstrates:

- local block acceptance on STD;
- outbound block propagation from STD;
- inbound raw block reception on physical GEN;
- block parsing on physical GEN;
- block acceptance on physical GEN;
- same-hash propagation across nodes.

## What this does not claim

This evidence does not claim production readiness.

It does not claim perfect synchronization under all network conditions.

It does not claim anonymity, legal safety in all jurisdictions, or complete resilience against all attacks.

It is a focused proof that a block accepted by one node can be propagated to and accepted by another physical node.

## Demo use

This evidence can be used as the basis for a short grant demo video showing:

STD accepted block -> outbound send -> physical GEN inbound receive -> physical GEN accepted same hash.
