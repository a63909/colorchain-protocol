# Java Reference Implementation

This is a small standalone Java reference implementation for public review.

It is not production consensus code and is not copied from the private Android application.

It demonstrates how ColorChain-style public-interest messages, hashes, and advisory trust metadata can be represented openly.

From repository root:

```bash
javac src/reference/java/org/colorchain/protocol/*.java
```

Run:

```bash
java -cp src/reference/java org.colorchain.protocol.ReferenceDemo
```

## Two-node local demo

`ReferenceTwoNodeDemo` demonstrates a minimal same-hash propagation flow between two local reference nodes.

Compile:

```bash
mkdir -p /tmp/colorchain-reference-build
javac -d /tmp/colorchain-reference-build src/reference/java/org/colorchain/protocol/*.java
```

Run:

```bash
java -cp /tmp/colorchain-reference-build org.colorchain.protocol.ReferenceTwoNodeDemo
```

For Windows PowerShell, refer to:

[docs/reproducible-local-demo.md](../../../docs/reproducible-local-demo.md)

## Messenger two-node local demo

`ReferenceMessengerTwoNodeDemo` demonstrates a minimal Messenger MVP flow between two local reference nodes: MessageEnvelope -> DeliveryAck -> non-minting MessageProofClaim.

Compile:

```bash
mkdir -p /tmp/colorchain-reference-build
javac -d /tmp/colorchain-reference-build src/reference/java/org/colorchain/protocol/*.java
```

Run:

```bash
java -cp /tmp/colorchain-reference-build org.colorchain.protocol.ReferenceMessengerTwoNodeDemo
```

For Windows PowerShell, refer to:

[docs/reproducible-messenger-demo.md](../../../docs/reproducible-messenger-demo.md)
