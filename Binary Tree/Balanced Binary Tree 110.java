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
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    // Returns height if balanced, -1 if unbalanced
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        if (left == -1) return -1;          // left subtree already unbalanced

        int right = dfs(node.right);
        if (right == -1) return -1;         // right subtree already unbalanced

        if (Math.abs(left - right) > 1) return -1;  // current node unbalanced

        return 1 + Math.max(left, right);   // return height to parent
    }
}
