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
    private int moves = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }

    // Returns net flow through this node's edge to parent
    // Positive = excess coins flowing UP to parent
    // Negative = deficit, parent must send coins DOWN
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftFlow  = dfs(node.left);
        int rightFlow = dfs(node.right);

        // Coins crossing left edge + coins crossing right edge
        moves += Math.abs(leftFlow) + Math.abs(rightFlow);

        // Flow to parent = all coins here minus the 1 this node keeps
        return node.val + leftFlow + rightFlow - 1;
    }
}