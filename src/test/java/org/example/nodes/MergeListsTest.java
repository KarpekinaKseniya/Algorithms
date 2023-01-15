package org.example.nodes;

import org.example.nodes.models.ListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MergeListsTest {

    private final MergeLists merge = new MergeLists();

    @ParameterizedTest
    @MethodSource("provideListNodes")
    void shouldReturnResultOfMergeTwoLists(final ListNode list1, final ListNode list2, final ListNode expected) {
        assertThat(merge.mergeTwoLists(list1, list2), is(expected));
    }

    private static Stream<Arguments> provideListNodes() {
        //@formatter:off
        return Stream.of(
                Arguments.of(
                        new ListNode(1, new ListNode(2, new ListNode(4))),
                        new ListNode(1, new ListNode(3, new ListNode(4))),
                        new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4))))))),
                Arguments.of(new ListNode(), new ListNode(2), new ListNode(0, new ListNode(2))),
                Arguments.of(new ListNode(), new ListNode(), new ListNode(0, new ListNode(0)))
        );
        //@formatter:on
    }
}
