package org.example.common;

/*
    You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i]
    and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
    If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
    Note that the nodes have no values and that we only use the node numbers in this problem.
*/
public class BinaryTree {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int parents[] = new int[n], root = -1;
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) parents[leftChild[i]]++;
            if (rightChild[i] != -1) parents[rightChild[i]]++;
        }
        for (int i = 0; i < n; i++) {
            if (parents[i] > 1 || (parents[i] == 0 && root != -1)) return false;
            else if (parents[i] == 0) root = i;
        }
        return dfs(root, leftChild, rightChild, new boolean[n]) == n;
    }

    private int dfs(int start, int[] leftChild, int[] rightChild, boolean[] visited) {
        if (start == -1) return 0;
        visited[start] = true;
        int left = leftChild[start], right = rightChild[start];
        if ((left != -1 && visited[left]) || (right != -1 && visited[rightChild[start]])) return 1000000;
        return 1 + dfs(left, leftChild, rightChild, visited) + dfs(right, leftChild, rightChild, visited);
    }

}
