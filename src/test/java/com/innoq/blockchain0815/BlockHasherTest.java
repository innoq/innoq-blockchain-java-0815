package com.innoq.blockchain0815;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockHasherTest {

    @Test
    public void hash_forGenesisBlock_shouldReturnCorrectHash() {
        BlockHasher sut = new BlockHasher(new BlockSerializer(Block.GENESIS));

        String hash = sut.hash();

        assertThat(hash)
            .isEqualTo("000000b642b67d8bea7cffed1ec990719a3f7837de5ef0f8ede36537e91cdc0e");
    }
}
