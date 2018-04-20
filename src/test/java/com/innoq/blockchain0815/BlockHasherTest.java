package com.innoq.blockchain0815;

import com.google.common.hash.HashCode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockHasherTest {

    @Test
    public void hash_forGenesisBlock_shouldReturnCorrectHash() {
        BlockHasher sut = new BlockHasher(new BlockSerializer(Block.GENESIS));

        HashCode hash = sut.hash();

        assertThat(hash.toString())
            .isEqualTo("000000b642b67d8bea7cffed1ec990719a3f7837de5ef0f8ede36537e91cdc0e");
    }
}
