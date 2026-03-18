class Solution {
    Map<Integer, Integer> mp = new HashMap<>();
    int preIndex = 0;

    public TreeNode build(int[] preorder, int left, int right) {
        if (left > right)
            return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int index = mp.get(rootVal);

        root.left = build(preorder, left, index - 1);
        root.right = build(preorder, index + 1, right);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            mp.put(inorder[i], i);

        return build(preorder, 0, inorder.length - 1);
    }
}