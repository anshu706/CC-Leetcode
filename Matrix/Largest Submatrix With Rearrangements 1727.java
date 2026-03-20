class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // Build histogram: for each cell, count consecutive 1s upward
        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] != 0)
                    matrix[i][j] = matrix[i-1][j] + 1;

        int ans = 0;

        // For each row, sort heights descending
        // The k-th tallest column (1-indexed) can form width k
        for (int i = 0; i < m; i++) {
            int[] row = matrix[i].clone();
            Arrays.sort(row);
            for (int j = n - 1; j >= 0 && row[j] > 0; j--)
                ans = Math.max(ans, row[j] * (n - j));
        }

        return ans;
    }
}