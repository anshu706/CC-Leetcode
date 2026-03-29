/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int cur = root.val;

        // Both in left subtree
        if (p.val < cur && q.val < cur) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Both in right subtree
        if (p.val > cur && q.val > cur) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Split here — current node is the LCA
        // (covers: p==cur, q==cur, or p and q on opposite sides)
        return root;
    }
}