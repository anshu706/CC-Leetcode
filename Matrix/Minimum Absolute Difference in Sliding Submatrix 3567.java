class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int rows = m - k + 1, cols = n - k + 1;
        int[][] ans = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TreeSet<Integer> vals = new TreeSet<>();
                for (int r = i; r < i + k; r++)
                    for (int c = j; c < j + k; c++)
                        vals.add(grid[r][c]);

                if (vals.size() < 2) { ans[i][j] = 0; continue; }

                int minDiff = Integer.MAX_VALUE;
                int prev = vals.first();
                for (int val : vals) {
                    if (val != vals.first())
                        minDiff = Math.min(minDiff, val - prev);
                    prev = val;
                }
                ans[i][j] = minDiff;
            }
        }
        return ans;
    }
} 
