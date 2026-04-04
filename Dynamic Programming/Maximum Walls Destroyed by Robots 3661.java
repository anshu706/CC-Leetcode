class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> robots[a] - robots[b]);

        int[] R = new int[n], D = new int[n];
        for (int i = 0; i < n; i++) {
            R[i] = robots[idx[i]];
            D[i] = distance[idx[i]];
        }

        int[] W = walls.clone();
        Arrays.sort(W);
        int m = W.length;

        // Count walls in [lo, hi] inclusive
        // lo > hi returns 0
        // Walls at blocking robot positions are NOT reachable

        // For robot i:
        // LEFT range:  [max(R[i-1]+1, R[i]-D[i]),  R[i]]   (blocked by prev robot exclusively)
        // RIGHT range: [R[i],  min(R[i+1]-1, R[i]+D[i])]   (blocked by next robot exclusively)

        // dp[0] = best walls if current robot fires LEFT
        // dp[1] = best walls if current robot fires RIGHT
        // Overlap only when robot i fires LEFT and robot i-1 fires RIGHT

        long[] dp = new long[2];

        for (int i = 0; i < n; i++) {
            // LEFT range for robot i
            int lLo = (i > 0) ? R[i - 1] + 1 : 1;
            lLo = Math.max(lLo, R[i] - D[i]);
            int lHi = R[i];
            long leftWalls = countWalls(W, lLo, lHi);

            // RIGHT range for robot i
            int rLo = R[i];
            int rHi = (i < n - 1) ? R[i + 1] - 1 : Integer.MAX_VALUE;
            rHi = (int) Math.min((long) rHi, (long) R[i] + D[i]);
            long rightWalls = countWalls(W, rLo, rHi);

            long prevLeft = dp[0];   // robot i-1 fired LEFT
            long prevRight = dp[1];  // robot i-1 fired RIGHT

            // If robot i fires LEFT and robot i-1 fired RIGHT:
            // robot i-1's right range: [R[i-1], min(R[i]-1, R[i-1]+D[i-1])]
            // robot i's left range:    [max(R[i-1]+1, R[i]-D[i]), R[i]]
            // These don't overlap! (i-1 right ends at R[i]-1, i left starts at R[i-1]+1)
            // Wait — they CAN share walls between R[i-1]+1 and R[i]-1 if both ranges cover that area

            // Robot i-1 RIGHT range end = min(R[i]-1, R[i-1]+D[i-1])
            // Robot i LEFT range start  = max(R[i-1]+1, R[i]-D[i])
            // Overlap = [max(R[i-1]+1, R[i]-D[i]),  min(R[i]-1, R[i-1]+D[i-1])]

            long overlap = 0;
            if (i > 0) {
                int prevRightEnd = (int) Math.min((long) R[i] - 1, (long) R[i - 1] + D[i - 1]);
                int curLeftStart = Math.max(R[i - 1] + 1, R[i] - D[i]);
                overlap = countWalls(W, curLeftStart, prevRightEnd);
            }

            long newLeft  = Math.max(prevLeft + leftWalls, prevRight + leftWalls - overlap);
            long newRight = Math.max(prevLeft + rightWalls, prevRight + rightWalls);

            dp[0] = newLeft;
            dp[1] = newRight;
        }

        return (int) Math.max(dp[0], dp[1]);
    }

    private long countWalls(int[] W, int lo, int hi) {
        if (lo > hi) return 0;
        int l = lowerBound(W, lo);
        int r = upperBound(W, hi);
        return r - l;
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
} 
