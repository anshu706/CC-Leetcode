class Solution {
    public int dia = 0;
    public int diameterOfBinaryTree(TreeNode root) {

        diameterOfBinaryTree2(root);
        return dia;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        if(root == null){
            return 0;
        }

        int leftHeight = diameterOfBinaryTree2(root.left);
        int rightHeight = diameterOfBinaryTree2(root.right);

        if(leftHeight + rightHeight > dia){
            dia = leftHeight + rightHeight;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}