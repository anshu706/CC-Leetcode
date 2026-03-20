class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // prefixX[i][j] = count of X, prefixY[i][j] = count of Y in submatrix (0,0) to (i,j)
        int[][] prefixX = new int[m][n];
        int[][] prefixY = new int[m][n];
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = (grid[i][j] == 'X') ? 1 : 0;
                int y = (grid[i][j] == 'Y') ? 1 : 0;

                // 2D prefix sum using inclusion-exclusion
                if (i > 0) { x += prefixX[i-1][j]; y += prefixY[i-1][j]; }
                if (j > 0) { x += prefixX[i][j-1]; y += prefixY[i][j-1]; }
                if (i > 0 && j > 0) { x -= prefixX[i-1][j-1]; y -= prefixY[i-1][j-1]; }

                prefixX[i][j] = x;
                prefixY[i][j] = y;

                if (x > 0 && x == y) result++;
            }
        }

        return result;
    }
}