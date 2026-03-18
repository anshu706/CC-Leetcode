class Solution {
    public void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;

        dfs(root.left, res);     // Left
        dfs(root.right, res);    // Right
        res.add(root.val);       // Root
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}