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

## License

innoq-blockchain-java-0815 is Open Source software released under the
[Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).

