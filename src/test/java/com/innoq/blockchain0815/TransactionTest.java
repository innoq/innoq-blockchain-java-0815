package com.innoq.blockchain0815;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    @Test
    public void toJson_shouldWork() {
        Transaction transaction =
            new Transaction("b3c973e2-db05-4eb5-9668-3e81c7389a6d", 0, "I am Heribert Innoq");

        String json = transaction.toJson();

        assertThat(json)
            .isEqualTo("{\"id\":\"b3c973e2-db05-4eb5-9668-3e81c7389a6d\",\"timestamp\":0,\"payload\":\"I am Heribert Innoq\"}");
    }
}
