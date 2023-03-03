package org.example.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ConvertTemperatureTest {

    private final ConvertTemperature convertTemp = new ConvertTemperature();

    @Test
    void convertTemperature() {
        assertThat(convertTemp.convertTemperature(122.11), is(new double[]{395.26000,251.79800}));
    }
}