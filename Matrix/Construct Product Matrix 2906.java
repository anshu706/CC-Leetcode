class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length, m = grid[0].length;
        int total = n * m;

        long[] prefix = new long[total];
        long[] suffix = new long[total];
        prefix[0] = 1;
        suffix[total - 1] = 1;

        // Build prefix products
        for (int i = 1; i < total; i++) {
            int pi = (i - 1) / m, pj = (i - 1) % m;
            prefix[i] = (prefix[i-1] * (grid[pi][pj] % MOD)) % MOD;
        }

        // Build suffix products
        for (int i = total - 2; i >= 0; i--) {
            int pi = (i + 1) / m, pj = (i + 1) % m;
            suffix[i] = (suffix[i+1] * (grid[pi][pj] % MOD)) % MOD;
        }

        // Build result matrix
        int[][] p = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int idx = i * m + j;
                p[i][j] = (int)((prefix[idx] * suffix[idx]) % MOD);
            }

        return p;
    }
}
 
