package org.example.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StudentsTest {

    private final Students student = new Students();

    @ParameterizedTest
    @MethodSource("provideStudentsAndSandwiches")
    void shouldCountStudents(final int[] students, final int[] sandwiches, final int expected) {
        assertThat(student.countStudents(students, sandwiches), is(expected));
    }

    private static Stream<Arguments> provideStudentsAndSandwiches() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}, 0),
                Arguments.of(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0 , 0, 0, 1, 1}, 3),
                Arguments.of(new int[]{1, 1, 0, 1}, new int[]{1, 0 , 0, 0, 1, 1}, 2),
                Arguments.of(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 1}, 0),
                Arguments.of(new int[]{}, new int[]{1}, 0)
        );
        //@formatter:on
    }
}