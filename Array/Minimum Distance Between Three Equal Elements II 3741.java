class Solution {
    public int minimumDistance(int[] nums) {
        // Map each value to its list of indices (naturally sorted)
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDist = Integer.MAX_VALUE;

        for (List<Integer> indices : indexMap.values()) {
            if (indices.size() < 3) continue;

            // Slide window of size 3: distance = 2 * (k - i)
            for (int t = 0; t + 2 < indices.size(); t++) {
                int dist = 2 * (indices.get(t + 2) - indices.get(t));
                minDist = Math.min(minDist, dist);
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
} 
