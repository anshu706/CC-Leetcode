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
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        // Base case: no elements in range
        if (left > right) return null;

        // Pick middle element as root (left-biased mid)
        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        // Recursively build left and right subtrees
        node.left  = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);

        return node;
    }
}
 
