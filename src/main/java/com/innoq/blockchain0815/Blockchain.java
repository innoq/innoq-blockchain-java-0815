package com.innoq.blockchain0815;

import java.util.ArrayList;
import java.util.List;

import static com.innoq.blockchain0815.Block.GENESIS;
import static java.util.stream.Collectors.joining;

public final class Blockchain {

    private final List<Block> blocks = new ArrayList<>();

    public Blockchain() {
        add(GENESIS);
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public int getCurrentBlockHeight() {
        return blocks.size();
    }

    public String toJson() {
        return
            "{" +
                "\"blocks\":" + blocks.stream().map(Block::toJson).collect(joining(",", "[", "]")) + "," +
                "\"blockHeight\":" + getCurrentBlockHeight() +
            "}";
    }
}
