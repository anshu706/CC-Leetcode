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
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;

        // Left leaf found — contribute its value
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }

        // Pass direction context to children
        return dfs(node.left,  true)   // left child  → isLeft=true
             + dfs(node.right, false); // right child → isLeft=false
    }
}