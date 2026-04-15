class Solution {
    private long[] robot;
    private int[][] factory;
    private long[][] memo;

    public long minimumTotalDistance(List<Integer> robotList, int[][] factory) {
        // Sort robots
        Collections.sort(robotList);
        // Sort factories by position
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int n = robotList.size();
        int m = factory.length;

        // Convert robot list to array for easy indexing
        robot = new long[n];
        for (int i = 0; i < n; i++) {
            robot[i] = robotList.get(i);
        }
        this.factory = factory;

        // memo[i][j] = min cost to repair robots[i..n-1] using factories[j..m-1]
        memo = new long[n + 1][m + 1];
        for (long[] row : memo) Arrays.fill(row, -1);

        return dp(0, 0, n, m);
    }

    private long dp(int i, int j, int n, int m) {
        // All robots repaired
        if (i == n) return 0;

        // No factories left but robots remain → impossible (problem guarantees valid input)
        if (j == m) return Long.MAX_VALUE / 2;

        if (memo[i][j] != -1) return memo[i][j];

        long result = Long.MAX_VALUE / 2;

        // Option 1: Skip factory j entirely (assign 0 robots to it)
        result = Math.min(result, dp(i, j + 1, n, m));

        // Option 2: Assign k robots (1..limit[j]) starting from robot i to factory j
        int limit = factory[j][1];
        long factoryPos = factory[j][0];
        long distSum = 0;

        for (int k = 1; k <= limit && i + k - 1 < n; k++) {
            // Add distance for the k-th robot assigned (robot at index i+k-1)
            distSum += Math.abs(robot[i + k - 1] - factoryPos);
            long future = dp(i + k, j + 1, n, m);
            result = Math.min(result, distSum + future);
        }

        memo[i][j] = result;
        return result;
    }
} 
