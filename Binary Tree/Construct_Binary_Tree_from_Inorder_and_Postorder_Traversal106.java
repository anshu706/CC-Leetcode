class Solution {
    int postIndex;
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;

        // postIndex
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderMap.get(rootVal); // inorder

        root.right = build(inorder, postorder, inorderIndex + 1, right); // right subtree

        root.left = build(inorder, postorder, left, inorderIndex - 1); // left subtree

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;

        // Store inorder indices
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }
}