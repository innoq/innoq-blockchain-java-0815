package com.innoq.blockchain0815;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockGeneratorTest {

    @Test
    public void mine_shouldFindGenesis() throws Exception {
        final BlockGenerator blockGenerator = new BlockGenerator("000000");

        Stopwatch sw = Stopwatch.createStarted();

        final Block genesis = blockGenerator.mine(
            Block.GENESIS.getIndex(),
            Block.GENESIS.getTimestamp(),
            Block.GENESIS.getTransactions(),
            Block.GENESIS.getPreviousBlockHash());

        System.out.println(sw.elapsed(MILLISECONDS));

        assertThat(genesis.getProof())
            .isEqualTo(Block.GENESIS.getProof());
    }
}
