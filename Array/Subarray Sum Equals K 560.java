class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);  // empty prefix — sum 0 seen once

        int count = 0;
        int sum   = 0;

        for (int num : nums) {
            sum += num;

            // How many previous prefixes make a valid subarray ending here?
            count += prefixCount.getOrDefault(sum - k, 0);

            // Record this prefix sum
            prefixCount.merge(sum, 1, Integer::sum);
        }

        return count;
    }
}