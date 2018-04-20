package com.innoq.blockchain0815;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

import static java.util.concurrent.Executors.newFixedThreadPool;

public final class Miner {

    private final ExecutorService es;
    private final int numberOfParallelWorkers;
    private final Predicate<Block> validator;

    public Miner(Predicate<Block> validator) {
        this(Runtime.getRuntime().availableProcessors() - 1, validator);
    }

    private Miner(int parallelism, Predicate<Block> validator) {
        this.es = newFixedThreadPool(parallelism);
        this.numberOfParallelWorkers = parallelism;
        this.validator = validator;
    }

    public Block mine(int index, long timestamp, List<Transaction> transactions,
                      String previousHash) throws Exception {
        final List<Worker> workers = new ArrayList<>(numberOfParallelWorkers);
        for (int proofStart = 0; proofStart < numberOfParallelWorkers; proofStart++) {
            final Worker worker =
                new Worker(index, timestamp, previousHash, transactions, proofStart);
            workers.add(worker);
        }

        return es.invokeAny(workers);
    }

    private final class Worker implements Callable<Block> {

        private final int index;
        private final long timestamp;
        private final String previousHash;
        private final List<Transaction> transactions;

        private long proof;

        private Worker(int index, long timestamp, String previousHash,
                       List<Transaction> transactions, long proof) {
            this.index = index;
            this.timestamp = timestamp;
            this.previousHash = previousHash;
            this.transactions = transactions;
            this.proof = proof;
        }

        @Override
        public Block call() throws InterruptedException {
            int tries = 1;
            Block candidate = new Block(index, timestamp, proof, transactions, previousHash);
            while (!validator.test(candidate)) {
                proof += numberOfParallelWorkers;
                if (tries++ % 100 == 0) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                }
                candidate = new Block(index, timestamp, proof, transactions, previousHash);
            }
            return candidate;
        }
    }
}
