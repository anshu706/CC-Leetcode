class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int MOD = 1_000_000_007;

        // Process each query
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];

            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int)((1L * nums[idx] * v) % MOD);
            }
        }

        // XOR all elements
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
} 
