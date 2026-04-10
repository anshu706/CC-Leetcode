class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDist = Integer.MAX_VALUE;

        for (List<Integer> indices : indexMap.values()) {
            if (indices.size() < 3) continue;

            for (int i = 0; i + 2 < indices.size(); i++) {
                int dist = 2 * (indices.get(i + 2) - indices.get(i));
                minDist = Math.min(minDist, dist);
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}