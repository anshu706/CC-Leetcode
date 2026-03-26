import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        Map<Long, Integer> bottomMap = new HashMap<>();
        Map<Long, Integer> topMap = new HashMap<>();
        Map<Long, Integer> leftMap = new HashMap<>();
        Map<Long, Integer> rightMap = new HashMap<>();

        // Initialize bottomMap and rightMap
        for (int[] row : grid) {
            for (int x : row) {
                total += x;
                bottomMap.put((long)x, bottomMap.getOrDefault((long)x, 0) + 1);
                rightMap.put((long)x, rightMap.getOrDefault((long)x, 0) + 1);
            }
        }

        long sumTop = 0;

        // Horizontal cuts
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                sumTop += val;

                topMap.put((long)val, topMap.getOrDefault((long)val, 0) + 1);

                bottomMap.put((long)val, bottomMap.get((long)val) - 1);
                if (bottomMap.get((long)val) == 0) {
                    bottomMap.remove((long)val);
                }
            }

            long sumBottom = total - sumTop;

            if (sumTop == sumBottom) return true;

            long diff = Math.abs(sumTop - sumBottom);

            if (sumTop > sumBottom) {
                if (check(topMap, grid, 0, i, 0, n - 1, diff)) return true;
            } else {
                if (check(bottomMap, grid, i + 1, m - 1, 0, n - 1, diff)) return true;
            }
        }

        long sumLeft = 0;

        // Vertical cuts
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                sumLeft += val;

                leftMap.put((long)val, leftMap.getOrDefault((long)val, 0) + 1);

                rightMap.put((long)val, rightMap.get((long)val) - 1);
                if (rightMap.get((long)val) == 0) {
                    rightMap.remove((long)val);
                }
            }

            long sumRight = total - sumLeft;

            if (sumLeft == sumRight) return true;

            long diff = Math.abs(sumLeft - sumRight);

            if (sumLeft > sumRight) {
                if (check(leftMap, grid, 0, m - 1, 0, j, diff)) return true;
            } else {
                if (check(rightMap, grid, 0, m - 1, j + 1, n - 1, diff)) return true;
            }
        }

        return false;
    }

    private boolean check(Map<Long, Integer> mp, int[][] grid,
                          int r1, int r2, int c1, int c2, long diff) {

        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;

        // single cell
        if (rows * cols == 1) return false;

        // 1D row
        if (rows == 1) {
            return (grid[r1][c1] == diff || grid[r1][c2] == diff);
        }

        // 1D column
        if (cols == 1) {
            return (grid[r1][c1] == diff || grid[r2][c1] == diff);
        }

        return mp.getOrDefault(diff, 0) > 0;
    }
}