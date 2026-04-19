class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxDist = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] >= nums1[i]) {
                // Valid pair: record distance, push j further
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                // nums2[j] < nums1[i]: need smaller nums1[i], so advance i
                // Also advance j if j <= i to maintain j >= i
                i++;
                if (j < i) j = i;
            }
        }

        return maxDist;
    }
}