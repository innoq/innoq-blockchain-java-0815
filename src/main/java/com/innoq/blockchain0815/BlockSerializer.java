package com.innoq.blockchain0815;

import java.util.List;

import static com.google.common.base.Charsets.UTF_8;
import static java.util.stream.Collectors.joining;

final class BlockSerializer {

    private final Block block;

    private final byte[] preProof;
    private final byte[] postProof;

    public BlockSerializer(Block block) {
        this.block = block;
        this.preProof = calculatePreProof(block);
        this.postProof = calculatePostProof(block);
    }

    public byte[] serialize() {
        final byte[] proof = toBytes(block.proof);
        byte[] result = new byte[preProof.length + proof.length + postProof.length];
        System.arraycopy(preProof, 0, result, 0, preProof.length);
        System.arraycopy(proof, 0, result, preProof.length, proof.length);
        System.arraycopy(postProof, 0, result, preProof.length + proof.length, postProof.length);
        return result;
    }

    byte[] getPreProof() {
        return preProof;
    }

    byte[] getPostProof() {
        return postProof;
    }

    byte[] getProof() {
        return toBytes(block.proof);
    }

    private static byte[] calculatePreProof(Block block) {
        return ("{" +
            "\"index\":" + block.index + "," +
            "\"timestamp\":" + block.timestamp + "," +
            "\"proof\":").getBytes(UTF_8);
    }

    private static byte[] calculatePostProof(Block block) {
        return ("," +
            "\"transactions\":" + toJson(block.transactions) + "," +
            "\"previousBlockHash\":\"" + block.previousBlockHash + "\"" +
            "}").getBytes(UTF_8);
    }

    static String toJson(List<Transaction> transactions) {
        return transactions
            .stream()
            .map(BlockSerializer::toJson)
            .collect(joining(",", "[", "]"));
    }

    static String toJson(Transaction transaction) {
        return
            "{" +
                "\"id\":\"" + transaction.id + "\"," +
                "\"timestamp\":" + transaction.timestamp + "," +
                "\"payload\":\"" + transaction.payload + "\"" +
            "}";
    }

    private static byte[] toBytes(long x) {
        return Long.toString(x).getBytes(UTF_8);
    }
}
