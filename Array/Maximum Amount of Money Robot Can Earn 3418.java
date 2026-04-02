class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;

        // dp[i][j][k] = max coins at (i,j) with k neutralizations remaining
        int[][][] dp = new int[m][n][3];
        int NEG_INF = Integer.MIN_VALUE / 2;

        // Initialize all to NEG_INF
        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, NEG_INF);

        // Base case: starting cell
        dp[0][0][2] = coins[0][0];
        dp[0][0][1] = coins[0][0] < 0 ? 0 : NEG_INF; // use 1 neutralization
        dp[0][0][0] = NEG_INF; // can't use 2 neutralizations on first cell alone

        // Handle using neutralizations at start
        if (coins[0][0] < 0) {
            dp[0][0][2] = coins[0][0]; // don't neutralize
            dp[0][0][1] = 0;           // use 1 neutralization
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int val = coins[i][j];

                for (int k = 0; k <= 2; k++) {
                    int best = NEG_INF;

                    // Come from top
                    if (i > 0 && dp[i-1][j][k] != NEG_INF)
                        best = Math.max(best, dp[i-1][j][k] + val);

                    // Come from left
                    if (j > 0 && dp[i][j-1][k] != NEG_INF)
                        best = Math.max(best, dp[i][j-1][k] + val);

                    dp[i][j][k] = best;

                    // Use a neutralization if val < 0 and we have one to spare
                    if (val < 0 && k < 2) {
                        int bestNeutralize = NEG_INF;

                        // Come from top with k+1 neutralizations remaining
                        if (i > 0 && dp[i-1][j][k+1] != NEG_INF)
                            bestNeutralize = Math.max(bestNeutralize, dp[i-1][j][k+1]);

                        // Come from left with k+1 neutralizations remaining
                        if (j > 0 && dp[i][j-1][k+1] != NEG_INF)
                            bestNeutralize = Math.max(bestNeutralize, dp[i][j-1][k+1]);

                        // Take max between neutralizing and not
                        if (bestNeutralize != NEG_INF)
                            dp[i][j][k] = Math.max(dp[i][j][k], bestNeutralize);
                    }
                }
            }
        }

        // Answer is best result at bottom-right with any neutralizations remaining
        return Math.max(dp[m-1][n-1][0], 
               Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}