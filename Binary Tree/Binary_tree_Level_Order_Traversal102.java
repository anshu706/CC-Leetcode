class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> r = new ArrayList<>();
        if(root == null) return r;
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()){
            int sz = q.size();
            List<Integer> minr = new ArrayList<>();

            while(sz > 0){
                TreeNode temp = q.peek();
                q.poll();

                if(temp.left != null) q.offer(temp.left);
                if(temp.right != null) q.offer(temp.right);

                minr.add(temp.val);
                sz--;
            }
            r.add(minr);
        }
        return r;
    }
}