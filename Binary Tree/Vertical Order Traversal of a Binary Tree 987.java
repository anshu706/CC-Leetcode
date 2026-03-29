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
    private List<int[]> nodes = new ArrayList<>(); // [col, row, val]

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);

        // Sort: col first, then row, then val (for ties at same position)
        nodes.sort((a, b) -> a[0] != b[0] ? a[0] - b[0]   // by col
                           : a[1] != b[1] ? a[1] - b[1]   // by row
                           :                a[2] - b[2]);  // by val

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;

        for (int[] node : nodes) {
            if (node[0] != prevCol) {          // new column
                result.add(new ArrayList<>());
                prevCol = node[0];
            }
            result.get(result.size() - 1).add(node[2]);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col) {
        if (node == null) return;
        nodes.add(new int[]{col, row, node.val});
        dfs(node.left,  row + 1, col - 1);
        dfs(node.right, row + 1, col + 1);
    }
}