package com.innoq.blockchain0815;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import static java.util.Collections.emptyList;
import static java.util.concurrent.Executors.newFixedThreadPool;

public final class BlockGenerator {

    private static final int PARALLELISM = Runtime.getRuntime().availableProcessors() - 1;

    private final Predicate<String> validator;
    private final CompletionService<Block> cs =
        new ExecutorCompletionService<>(newFixedThreadPool(PARALLELISM));

    public BlockGenerator(String zeros) {
        validator = s -> s.startsWith(zeros);
    }

    public Block generateSuccessorFor(Block block) throws Exception {
        final int index = block.getIndex() + 1;
        final long timestamp = System.currentTimeMillis();
        final String previousHash = block.hash();

        return mine(index, timestamp, emptyList(), previousHash);
    }

    Block mine(int index, long timestamp, List<Transaction> transactions, String previousHash) throws Exception {
        final List<Future<Block>> workers = new ArrayList<>(PARALLELISM);
        for (int proofStart = 0; proofStart < PARALLELISM; proofStart++) {
            workers.add(cs.submit(new Worker(index, timestamp, proofStart, previousHash, transactions, validator)));
        }

        try {
            return cs.take().get();
        } finally {
            workers.forEach(w -> w.cancel(true));
        }
    }


    private static final class Worker implements Callable<Block> {

        private final int index;
        private final long timestamp;
        private long proof;
        private final String previousHash;
        private final List<Transaction> transactions;
        private final Predicate<String> validator;

        private Worker(int index, long timestamp, long proof, String previousHash, List<Transaction> transactions, Predicate<String> validator) {
            this.index = index;
            this.timestamp = timestamp;
            this.proof = proof;
            this.previousHash = previousHash;
            this.transactions = transactions;
            this.validator = validator;
        }

        @Override
        public Block call() {
            Block candidate = new Block(index, timestamp, proof, transactions, previousHash);
            while (candidate.isInvalid(validator)) {
                proof += PARALLELISM;
                candidate = new Block(index, timestamp, proof, transactions, previousHash);
            }
            return candidate;
        }
    }

}
