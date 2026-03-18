class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if(root == null) return r;
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()){
            int sz = q.size();
            while(sz > 0){
                TreeNode temp = q.peek();
                q.poll();

                if(temp.left != null) q.offer(temp.left);
                if(temp.right != null) q.offer(temp.right);

                if(sz == 1) r.add(temp.val);
                sz--;
            }
        }
        return r;
    }
}