# innoq-blockchain-java-0815

This blockchain implementation was build at the 2018 INNOQ Hands-On-Event.

## Getting Started

These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes.

### Prerequisites

You'll need to install:

 - [Java 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)

### Building

To build the project execute

```shell
./bin/build
```

### Running

To run the project execute

```shell
./bin/run
```

If you want to change the used port (default 3000) supply the port number as
first argument.

## Interacting with a running blockchain

### Retrieving node information

To retrieve some information about the running blockchain node execute

```shell
./bin/node-info
```

This will output something like

```shell
Retrieving node info from blockchain runnig at: http://localhost:3000
{
  "nodeId": "83cf6954-2e59-499b-865a-07b7f667e947",
  "currentBlockHeight": 2
}
```

### Retrieving blocks

To retrieve all existing blocks from the running blockchain execute

```shell
./bin/blocks
```

This will output something like

```shell
Retrieving blocks from blockchain running at: http://localhost:3000
{
  "blocks": [
    {
      "index": 1,
      "timestamp": 0,
      "proof": 1917336,
      "transactions": [
        {
          "id": "b3c973e2-db05-4eb5-9668-3e81c7389a6d",
          "timestamp": 0,
          "payload": "I am Heribert Innoq"
        }
      ],
      "previousBlockHash": "0"
    },
    {
      "index": 2,
      "timestamp": 1524588413541,
      "proof": 2573043,
      "transactions": [],
      "previousBlockHash": "000000b642b67d8bea7cffed1ec990719a3f7837de5ef0f8ede36537e91cdc0e"
    }
  ],
  "blockHeight": 2
}
```

### Mining a new block

To mine a new block execute

```shell
./bin/mine
```

This will output something like

```shell
Mining a new block in blockchain running at: http://localhost:3000
{
  "message": "Mined a new block in 1.013 s.",
  "block": {
    "index": 2,
    "timestamp": 1524588413541,
    "proof": 2573043,
    "transactions": [],
    "previousBlockHash": "000000b642b67d8bea7cffed1ec990719a3f7837de5ef0f8ede36537e91cdc0e"
  }
}
```

## License

innoq-blockchain-java-0815 is Open Source software released under the
[Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).

