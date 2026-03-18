class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        boolean leftToRight = true;

        while (!q.isEmpty()) {

            int size = q.size();
            Integer[] level = new Integer[size];

            for (int i = 0; i < size; i++) {

                TreeNode node = q.poll();

                // Determine index based on direction
                int index = leftToRight ? i : (size - 1 - i);
                level[index] = node.val;

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            result.add(Arrays.asList(level));

            // Change direction
            leftToRight = !leftToRight;
        }

        return result;
    }
}