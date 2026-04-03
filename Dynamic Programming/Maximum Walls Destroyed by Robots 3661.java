class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // Sort robots with their distances
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> robots[a] - robots[b]);

        int[] sortedRobots = new int[n];
        int[] sortedDist = new int[n];
        for (int i = 0; i < n; i++) {
            sortedRobots[i] = robots[idx[i]];
            sortedDist[i] = distance[idx[i]];
        }

        // Sort walls + prefix sum for range count queries
        int[] sortedWalls = walls.clone();
        Arrays.sort(sortedWalls);
        int m = sortedWalls.length;
        int[] prefix = new int[m + 1];
        for (int i = 0; i < m; i++) prefix[i + 1] = prefix[i] + 1;

        int total = 0;

        for (int i = 0; i < n; i++) {
            int pos = sortedRobots[i];
            int dist = sortedDist[i];

            // Left fire range: [leftBound, pos]
            // Blocked by previous robot
            int leftBound = (i > 0) ? sortedRobots[i - 1] : 0;
            int leftStart = Math.max(leftBound, pos - dist);
            // If previous robot is AT same pos or closer, leftStart = max(prevRobot, pos-dist)
            // But if prevRobot >= pos - dist, bullet is blocked before reaching pos-dist
            // Actually leftStart = max(prevRobotPos, pos - dist)
            // but wall at prevRobotPos can still be hit only if prevRobotPos >= pos-dist
            // Robot blocks bullet BEFORE it reaches the wall only if wall is beyond robot
            // Since we go LEFT: bullet travels left, blocked by prev robot (smaller pos)
            // Walls between [max(prevRobot, pos-dist), pos] are reachable
            int leftWalls = countWalls(sortedWalls, prefix, leftStart, pos);

            // Right fire range: [pos, rightBound]
            // Blocked by next robot
            int rightBound = (i < n - 1) ? sortedRobots[i + 1] : Integer.MAX_VALUE;
            int rightEnd = (int) Math.min((long) rightBound, (long) pos + dist);
            int rightWalls = countWalls(sortedWalls, prefix, pos, rightEnd);

            total += Math.max(leftWalls, rightWalls);
        }

        return total;
    }

    // Count walls in [lo, hi] inclusive using binary search
    private int countWalls(int[] sortedWalls, int[] prefix, int lo, int hi) {
        if (lo > hi) return 0;
        int left = lowerBound(sortedWalls, lo);   // first index >= lo
        int right = upperBound(sortedWalls, hi);   // first index > hi
        return prefix[right] - prefix[left];
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}