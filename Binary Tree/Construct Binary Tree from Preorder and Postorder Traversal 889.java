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
    private Map<Integer, Integer> postIndex = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Build index map for O(1) postorder lookups
        for (int i = 0; i < postorder.length; i++) {
            postIndex.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                     postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] pre, int preL, int preR,
                           int[] post, int postL, int postR) {
        if (preL > preR) return null;

        TreeNode root = new TreeNode(pre[preL]);

        // Only one node
        if (preL == preR) return root;

        // pre[preL+1] = left subtree root
        // Find it in postorder to get left subtree size
        int leftRootVal  = pre[preL + 1];
        int leftRootPost = postIndex.get(leftRootVal);  // index in postorder
        int leftSize     = leftRootPost - postL + 1;    // size of left subtree

        // Recurse into left and right subtrees
        root.left  = build(pre,  preL + 1, preL + leftSize,
                           post, postL,    leftRootPost);

        root.right = build(pre,  preL + leftSize + 1, preR,
                           post, leftRootPost + 1,    postR - 1);

        return root;
    }
}