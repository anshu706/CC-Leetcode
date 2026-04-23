
class Solution {

    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];

        // Group indices by value
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : groups.values()) {
            int k = indices.size();
            if (k == 1) {
                continue; // arr[i] stays 0
            }
            // --- Left to Right Pass ---
            // For each index[i], add: i * index[i] - (sum of indices to the left)
            long prefixSum = 0;
            for (int i = 0; i < k; i++) {
                long idx = indices.get(i);
                // i elements are to the left of current position
                arr[(int) idx] += idx * i - prefixSum;
                prefixSum += idx;
            }

            // --- Right to Left Pass ---
            // For each index[i], add: (sum of indices to the right) - i * index[i]
            long suffixSum = 0;
            for (int i = k - 1; i >= 0; i--) {
                long idx = indices.get(i);
                // (k - 1 - i) elements are to the right of current position
                arr[(int) idx] += suffixSum - idx * (k - 1 - i);
                suffixSum += idx;
            }
        }

        return arr;
    }
}
