package com.innoq.blockchain0815;

public final class Transaction {

    private final String id;
    private final long timestamp;
    private final String payload;

    public Transaction(String id, long timestamp, String payload) {
        this.id = id;
        this.timestamp = timestamp;
        this.payload = payload;
    }

    public String toJson() {
        return
            "{" +
                "\"id\":\"" + id + "\"," +
                "\"timestamp\":" + timestamp + "," +
                "\"payload\":\"" + payload + "\"" +
            "}";
    }

}
