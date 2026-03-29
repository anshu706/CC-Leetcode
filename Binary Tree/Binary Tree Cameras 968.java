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
    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        // If root itself is uncovered after DFS, place camera there
        if (dfs(root) == 0) cameras++;
        return cameras;
    }

    private int dfs(TreeNode node) {
        // Null nodes are considered "covered" — don't force camera on leaf's parent
        if (node == null) return 1;

        int left  = dfs(node.left);
        int right = dfs(node.right);

        // Any child uncovered → must place camera HERE
        if (left == 0 || right == 0) {
            cameras++;
            return 2;  // this node has a camera
        }

        // Both children covered; if either has camera → this node is covered
        if (left == 2 || right == 2) {
            return 1;  // covered by child's camera, no camera needed here
        }

        // Both children covered but no camera nearby → this node NOT covered
        // Defer: let parent place the camera (greedy — push camera up)
        return 0;
    }
}