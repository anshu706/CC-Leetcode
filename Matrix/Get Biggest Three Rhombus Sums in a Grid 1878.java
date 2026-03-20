class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> top3 = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // k = 0: single cell
                addToTop3(top3, grid[r][c]);

                // k >= 1: actual rhombus border
                for (int k = 1; ; k++) {
                    if (r - k < 0 || r + k >= m || c - k < 0 || c + k >= n)
                        break;

                    int sum = 0;

                    // Top -> Right
                    for (int i = 0; i < k; i++)
                        sum += grid[r - k + i][c + i];

                    // Right -> Bottom
                    for (int i = 0; i < k; i++)
                        sum += grid[r + i][c + k - i];

                    // Bottom -> Left
                    for (int i = 0; i < k; i++)
                        sum += grid[r + k - i][c - i];

                    // Left -> Top
                    for (int i = 0; i < k; i++)
                        sum += grid[r - i][c - k + i];

                    addToTop3(top3, sum);
                }
            }
        }

        // Return in descending order
        int[] result = new int[top3.size()];
        int idx = 0;
        for (int val : top3.descendingSet())
            result[idx++] = val;
        return result;
    }

    private void addToTop3(TreeSet<Integer> top3, int val) {
        top3.add(val);
        if (top3.size() > 3)
            top3.pollFirst(); // remove smallest
    }
} 
