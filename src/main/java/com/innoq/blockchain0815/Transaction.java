package com.innoq.blockchain0815;

public final class Transaction {

    public final String id;
    public final long timestamp;
    public final String payload;

    public Transaction(String id, long timestamp, String payload) {
        this.id = id;
        this.timestamp = timestamp;
        this.payload = payload;
    }
}
