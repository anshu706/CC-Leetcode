class Solution {
    public int climbStairs(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return climbS(n, dp);
    }

    public int climbS(int n, int[] dp) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int oneStep = climbS(n - 1, dp);
        int twoStep = climbS(n - 2, dp);

        return dp[n] = (oneStep + twoStep);
    }
}