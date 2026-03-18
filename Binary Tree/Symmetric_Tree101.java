class Solution {

    public boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }
    public boolean isSymmetric(TreeNode root) {

        return isMirror(root.left, root.right);

    }
}