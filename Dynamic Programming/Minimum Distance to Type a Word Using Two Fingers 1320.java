class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        
        // dp[i][j] = min cost after typing word[0..i]
        // one finger at word[i], other finger at j (26 = not placed)
        int[][] dp = new int[n][27];
        for (int[] row : dp) java.util.Arrays.fill(row, Integer.MAX_VALUE / 2);

        // Base case: typing word[0] is FREE (initial placement is free)
        // One finger lands on word[0], other finger not placed (26)
        dp[0][26] = 0;

        for (int i = 0; i < n - 1; i++) {
            int cur  = word.charAt(i)     - 'A';
            int nxt  = word.charAt(i + 1) - 'A';

            for (int j = 0; j <= 26; j++) {
                if (dp[i][j] == Integer.MAX_VALUE / 2) continue;

                // Option 1: finger at cur moves to nxt
                // other finger stays at j
                dp[i + 1][j] = Math.min(
                    dp[i + 1][j],
                    dp[i][j] + dist(cur, nxt)
                );

                // Option 2: other finger (at j) moves to nxt
                // finger at cur stays → cur becomes the new "other"
                int cost = (j == 26) ? 0 : dist(j, nxt); // free if not placed
                dp[i + 1][cur] = Math.min(
                    dp[i + 1][cur],
                    dp[i][j] + cost
                );
            }
        }

        // Find minimum over all "other finger" positions at last character
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= 26; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }

    private int dist(int a, int b) {
        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}