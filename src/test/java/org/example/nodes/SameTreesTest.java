package org.example.nodes;

import org.example.nodes.models.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SameTreesTest {

    private final SameTrees sameTrees = new SameTrees();

    @ParameterizedTest
    @MethodSource("provideTreesAndResult")
    void shouldCheckIsSameTree(final TreeNode p, final TreeNode q, final boolean expected) {
        assertThat(sameTrees.isSameTree(p, q), is(expected));
    }

    private static Stream<Arguments> provideTreesAndResult() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new TreeNode(1, new TreeNode(2),new TreeNode(3)), new TreeNode(1, new TreeNode(2),new TreeNode(3)), true),
                Arguments.of(new TreeNode(1, new TreeNode(2), null), new TreeNode(1,null, new TreeNode(2)), false),
                Arguments.of(new TreeNode(1, new TreeNode(2),new TreeNode(1)), new TreeNode(1, new TreeNode(1),new TreeNode(2)), false),
                Arguments.of(null, null, true),
                Arguments.of(null, new TreeNode(), false),
                Arguments.of(new TreeNode(1, new TreeNode(2),new TreeNode(45)), new TreeNode(1, new TreeNode(2),new TreeNode(3)), false),
                Arguments.of(new TreeNode(1, new TreeNode(124),new TreeNode(3)), new TreeNode(1, new TreeNode(355),new TreeNode(3)), false)
        );
        //@formatter:on
    }
}