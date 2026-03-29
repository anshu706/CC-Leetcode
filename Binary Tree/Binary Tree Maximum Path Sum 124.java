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
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    // Returns best single-arm gain from this node upward
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // Ignore negative contributions — take 0 instead
        int left  = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        // Best path THROUGH this node (the "bend") — update global answer
        maxSum = Math.max(maxSum, node.val + left + right);

        // Return best single arm to parent (can only extend one direction)
        return node.val + Math.max(left, right);
    }
}