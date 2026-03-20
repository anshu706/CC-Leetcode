
class Solution {

    public int numberOfStableArrays(int zero, int one, int limit) {
        final long MOD = 1_000_000_007L;
        // dp[i][j][k] = # stable arrays with i zeros, j ones, last element = k
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Single element base cases
        if (zero >= 1) {
            dp[1][0][0] = 1;
        }
        if (one >= 1) {
            dp[0][1][1] = 1;
        }

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 1 && j == 0) {
                    continue; // already set

                }
                if (i == 0 && j == 1) {
                    continue; // already set
                }
                if (i > 0) {
                    if (j == 0) {
                        // Pure zeros row: valid only if length <= limit
                        dp[i][0][0] = (i <= limit) ? 1 : 0;
                    } else {
                        dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                        // Subtract arrays where last (limit+1) elements are all 0s
                        if (i > limit) {
                            dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                        }
                    }
                }
                if (j > 0) {
                    if (i == 0) {
                        // Pure ones column: valid only if length <= limit
                        dp[0][j][1] = (j <= limit) ? 1 : 0;
                    } else {
                        dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                        // Subtract arrays where last (limit+1) elements are all 1s
                        if (j > limit) {
                            dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                        }
                    }
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}
