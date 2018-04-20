package com.innoq.blockchain0815;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;

import static com.innoq.blockchain0815.Block.GENESIS;
import static java.util.stream.Collectors.joining;

public final class Blockchain {

    private final List<Block> blocks = new ArrayList<>();
    private final BlockGenerator generator;

    public Blockchain() {
        this("000000");
    }

    public Blockchain(String zeros) {
        generator = new BlockGenerator(zeros);
        add(GENESIS);
    }

    public int getCurrentBlockHeight() {
        return blocks.size();
    }

    public MiningResult mine() throws Exception {
        final Block last = blocks.get(getCurrentBlockHeight() - 1);

        Stopwatch sw = Stopwatch.createStarted();
        final Block next = generator.generateSuccessorFor(last);
        final String message = "Mined a new block in " + sw.stop() + ".";

        add(next);
        return new MiningResult(message, next);
    }

    public String toJson() {
        return
            "{" +
                "\"blocks\":" + blocks.stream().map(Block::toJson).collect(joining(",", "[", "]")) + "," +
                "\"blockHeight\":" + getCurrentBlockHeight() +
            "}";
    }

    void add(Block block) {
        blocks.add(block);
    }

    public static final class MiningResult {

        private final String message;
        private final Block block;

        public MiningResult(String message, Block block) {
            this.message = message;
            this.block = block;
        }

        public String toJson() {
            return
                "{" +
                    "\"message\":\"" + message + "\"," +
                    "\"block\":" + block.toJson() +
                "}";
        }
    }
}
