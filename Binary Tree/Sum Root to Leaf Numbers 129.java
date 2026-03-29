/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int current) {
        if (node == null) return 0;

        current = current * 10 + node.val;   // shift left, append digit

        // Leaf — completed number, contribute to sum
        if (node.left == null && node.right == null) return current;

        // Internal node — sum contributions from both subtrees
        return dfs(node.left, current) + dfs(node.right, current);
    }
}