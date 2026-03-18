class Solution {
    public void dfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);          // Root
        dfs(root.left, result);        // Left
        dfs(root.right, result);       // Right
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }
}