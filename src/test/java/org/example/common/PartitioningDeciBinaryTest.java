package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PartitioningDeciBinaryTest {

    private final PartitioningDeciBinary partitioning = new PartitioningDeciBinary();

    @ParameterizedTest
    @CsvSource(value = { "27346209830709182346:9", "32:3", "82734:8", "5:5", "4104:4" }, delimiter = ':')
    void minPartitions(final String n, final int minNumber) {
        assertThat(partitioning.minPartitions(n), is(minNumber));
    }
}