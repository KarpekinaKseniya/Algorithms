package org.example.nodes;

import org.example.nodes.models.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BoolTreeTest {

    private final BoolTree boolTree = new BoolTree();

    @ParameterizedTest
    @MethodSource("provideTreeNodeAndExpectedValue")
    void evaluateTree(final TreeNode root, final boolean expected) {
        assertThat(boolTree.evaluateTree(root), is(expected));
    }

    private static Stream<Arguments> provideTreeNodeAndExpectedValue() {
        //@formatter:off
        return Stream.of(
                Arguments.of(new TreeNode(2, new TreeNode(1), new TreeNode(3, new TreeNode(0), new TreeNode(1))), true),
                Arguments.of(new TreeNode(2, new TreeNode(3, new TreeNode(0), new TreeNode(1)), new TreeNode(1)), true),
                Arguments.of(new TreeNode(3, new TreeNode(1), new TreeNode(0)), false),
                Arguments.of(new TreeNode(3, new TreeNode(0), new TreeNode(0)), false),
                Arguments.of(new TreeNode(3, new TreeNode(1), new TreeNode(1)), true),
                Arguments.of(new TreeNode(2, new TreeNode(1), new TreeNode(0)), true),
                Arguments.of(new TreeNode(2, new TreeNode(0), new TreeNode(0)), false),
                Arguments.of(new TreeNode(2, new TreeNode(1), new TreeNode(1)), true),
                Arguments.of(new TreeNode(3, new TreeNode(0), new TreeNode(1)), false),
                Arguments.of(new TreeNode(0), false)
        );
        //@formatter:on
    }
}