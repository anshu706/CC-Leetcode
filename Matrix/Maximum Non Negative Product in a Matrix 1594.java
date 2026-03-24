class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int MOD = 1_000_000_007;

        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dpMax[0][j] = dpMax[0][j-1] * grid[0][j];
            dpMin[0][j] = dpMin[0][j-1] * grid[0][j];
        }

        // Fill first column
        for (int i = 1; i < m; i++) {
            dpMax[i][0] = dpMax[i-1][0] * grid[i][0];
            dpMin[i][0] = dpMin[i-1][0] * grid[i][0];
        }

        // Fill rest of grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                long fromTop_max = dpMax[i-1][j] * val;
                long fromTop_min = dpMin[i-1][j] * val;
                long fromLeft_max = dpMax[i][j-1] * val;
                long fromLeft_min = dpMin[i][j-1] * val;

                dpMax[i][j] = Math.max(Math.max(fromTop_max, fromTop_min),
                                       Math.max(fromLeft_max, fromLeft_min));
                dpMin[i][j] = Math.min(Math.min(fromTop_max, fromTop_min),
                                       Math.min(fromLeft_max, fromLeft_min));
            }
        } 

        long result = dpMax[m-1][n-1];
        if (result < 0) return -1;
        return (int)(result % MOD);
    }
}
