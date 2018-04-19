package com.innoq.blockchain0815;

import com.google.common.base.Stopwatch;

import static com.innoq.blockchain0815.Block.GENESIS;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class Blockchain {

    public static void main(String[] args) throws Exception {
        final BlockGenerator blockGenerator = new BlockGenerator("000000");

        final Stopwatch stopwatch = Stopwatch.createStarted();

        final Block nextBlock =
            blockGenerator.generateSuccessorFor(GENESIS);

        System.out.println(stopwatch.elapsed(SECONDS));

        System.out.println(nextBlock.hash());
        System.out.println(nextBlock.getProof());
    }
}
