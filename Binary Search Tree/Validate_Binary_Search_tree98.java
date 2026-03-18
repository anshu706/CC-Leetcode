class Solution {

    public boolean isValidBST(TreeNode root) {
        return Validate(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public boolean Validate(TreeNode root,long minval,long maxval)
        {
            if(root == null) { 
                return true;
            }

            if(root.val <= minval || root.val >= maxval)
            {
                return false;
            }

            return (Validate(root.left,minval,root.val) && 
                   Validate(root.right,root.val,maxval));
        }
}