class Solution {
    public int maxDepth(TreeNode root) {
        
        // base case
        if(root == null){
            return 0;
        }

        //recursive case
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}