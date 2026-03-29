/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    record Pair(TreeNode node, long idx) {}

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0L));

        while (!queue.isEmpty()) {
            int size  = queue.size();
            long base = queue.peek().idx(); // leftmost index this level

            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair p    = queue.poll();
                long idx  = p.idx() - base;   // normalize to prevent overflow

                if (i == 0)        first = idx;
                if (i == size - 1) last  = idx;

                if (p.node().left  != null) queue.offer(new Pair(p.node().left,  2 * idx));
                if (p.node().right != null) queue.offer(new Pair(p.node().right, 2 * idx + 1));
            }

            maxWidth = (int) Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
}