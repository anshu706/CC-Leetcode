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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;  // key not found

        if (key < root.val) {
            // Target is in left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // Target is in right subtree
            root.right = deleteNode(root.right, key);
        } else {
            // Found the node to delete — handle 3 cases

            // Case 1 & 2: zero or one child
            if (root.left  == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: two children
            // Find in-order successor (smallest in right subtree)
            TreeNode successor = getMin(root.right);

            // Replace value, then delete the successor below
            root.val   = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    // Leftmost node = smallest value in subtree
    private TreeNode getMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}