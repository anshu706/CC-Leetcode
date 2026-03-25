class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long total = 0;
        for (int[] row : grid)
            for (int val : row)
                total += val;

        // Check horizontal cuts (between rows)
        long prefix = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int val : grid[i])
                prefix += val;
            if (prefix * 2 == total)
                return true;
        }

        // Check vertical cuts (between columns)
        int m = grid.length, n = grid[0].length;
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++)
                prefix += grid[i][j];
            if (prefix * 2 == total)
                return true;
        }

        return false;
    }
} 
