class Solution {
    private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited,
                        int r, int c, int pr, int pc) {
        visited[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Out of bounds or different character → skip
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) continue;
            if (grid[nr][nc] != grid[r][c]) continue;

            // Skip the cell we just came from (last visited cell rule)
            if (nr == pr && nc == pc) continue;

            // Already visited & not parent → CYCLE FOUND
            if (visited[nr][nc]) return true;

            if (dfs(grid, visited, nr, nc, r, c)) return true;
        }
        return false;
    }
}