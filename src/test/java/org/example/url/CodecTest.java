package org.example.url;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CodecTest {

    private static final String TEST_URL = "https://bool.dev/blog/detail/grasp-printsipy";

    private final Codec codec = new Codec();

    @Test
    void shouldReturnInitialUrl() {
        assertThat(codec.decode(codec.encode(TEST_URL)), is(TEST_URL));
    }
}