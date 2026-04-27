class Solution {
    // Each street type's open directions: {LEFT, RIGHT, UP, DOWN} as booleans
    // Index: [streetType][direction] where directions: 0=LEFT, 1=RIGHT, 2=UP, 3=DOWN
    private static final boolean[][] OPENS = {
        {},                                    // unused (1-indexed)
        {true,  true,  false, false},          // 1: LEFT, RIGHT
        {false, false, true,  true },          // 2: UP, DOWN
        {true,  false, false, true },          // 3: LEFT, DOWN
        {false, true,  false, true },          // 4: RIGHT, DOWN
        {true,  false, true,  false},          // 5: LEFT, UP
        {false, true,  true,  false}           // 6: RIGHT, UP
    };

    // Direction deltas: LEFT, RIGHT, UP, DOWN
    private static final int[] DR = {0,  0, -1, 1};
    private static final int[] DC = {-1, 1,  0, 0};

    // Opposite direction index: LEFT<->RIGHT, UP<->DOWN
    private static final int[] OPPOSITE = {1, 0, 3, 2};

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            // Reached destination
            if (r == m - 1 && c == n - 1) return true;

            int streetType = grid[r][c];

            // Try all 4 directions
            for (int d = 0; d < 4; d++) {
                // Current cell must open toward direction d
                if (!OPENS[streetType][d]) continue;

                int nr = r + DR[d];
                int nc = c + DC[d];

                // Neighbor must be within bounds
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                // Neighbor must not be visited
                if (visited[nr][nc]) continue;

                // Neighbor must open back toward current cell (opposite direction)
                int neighborStreet = grid[nr][nc];
                if (!OPENS[neighborStreet][OPPOSITE[d]]) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return false;
    }
}