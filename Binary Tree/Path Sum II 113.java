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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int remaining, 
                     List<Integer> current, List<List<Integer>> result) {
        if (node == null) return;

        current.add(node.val);              // choose

        if (node.left == null && node.right == null   // at a leaf
                && remaining == node.val) {           // sum matches
            result.add(new ArrayList<>(current));     // snapshot the path
        }

        dfs(node.left,  remaining - node.val, current, result);  // explore
        dfs(node.right, remaining - node.val, current, result);

        current.remove(current.size() - 1); // unchoose (backtrack)
    }
}