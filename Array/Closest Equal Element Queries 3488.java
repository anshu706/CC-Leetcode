class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // Group indices by value
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> answer = new ArrayList<>();

        for (int q : queries) {
            int val = nums[q];
            List<Integer> indices = indexMap.get(val);

            // Only one occurrence means no other index exists
            if (indices.size() == 1) {
                answer.add(-1);
                continue;
            }

            // Binary search for position of q in sorted indices list
            int pos = Collections.binarySearch(indices, q);
            int minDist = Integer.MAX_VALUE;

            // Check right neighbor (next index with same value)
            int rightPos = (pos + 1) % indices.size();
            int rightIdx = indices.get(rightPos);
            int rightDist = Math.abs(rightIdx - q);
            minDist = Math.min(minDist, Math.min(rightDist, n - rightDist));

            // Check left neighbor (previous index with same value)
            int leftPos = (pos - 1 + indices.size()) % indices.size();
            int leftIdx = indices.get(leftPos);
            int leftDist = Math.abs(leftIdx - q);
            minDist = Math.min(minDist, Math.min(leftDist, n - leftDist));

            answer.add(minDist);
        }

        return answer;
    }
}