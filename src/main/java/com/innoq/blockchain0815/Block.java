package com.innoq.blockchain0815;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public final class Block {

    public static final Block GENESIS =
        new Block(1, 0, 1917336, asList(new Transaction("b3c973e2-db05-4eb5-9668-3e81c7389a6d", 0, "I am Heribert Innoq")), "0");

    public final int index;
    public final long timestamp;
    public final List<Transaction> transactions;
    public final String previousBlockHash;

    public long proof;

    public Block(int index, long timestamp, long proof, List<Transaction> transactions, String previousBlockHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        this.transactions = unmodifiableList(transactions);
        this.previousBlockHash = previousBlockHash;
    }
}
