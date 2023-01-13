package org.example.nodes;

import org.example.nodes.models.ListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AddNumbersTest {

    private final AddNumbers numbers = new AddNumbers();

    @ParameterizedTest
    @MethodSource("provideTwoNumbersAndResult")
    void shouldReturnSumOfTwoNumbers(final ListNode l1, final ListNode l2, final ListNode expected) {
        assertThat(numbers.addTwoNumbers(l1, l2), is(expected));
    }

    private static Stream<Arguments> provideTwoNumbersAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new ListNode(0), new ListNode(0), new ListNode(0)),
                Arguments.of(
                        new ListNode(2, new ListNode(4, new ListNode(3))),
                        new ListNode(5, new ListNode(6, new ListNode(4))),
                        new ListNode(7, new ListNode(0, new ListNode(8)))),
                Arguments.of(new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))),
                             new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))),
                             new ListNode(8, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(1))))))
        );
        //@formatter:on
    }
}