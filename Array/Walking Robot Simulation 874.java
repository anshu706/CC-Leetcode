class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: North, East, South, West
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };

        // Store obstacles in a HashSet for O(1) lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }

        int x = 0, y = 0; // Current position
        int dir = 0; // 0=North, 1=East, 2=South, 3=West
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn right
                dir = (dir + 1) % 4;
            } else if (cmd == -2) {
                // Turn left
                dir = (dir + 3) % 4;
            } else {
                // Move forward k steps, one unit at a time
                for (int step = 0; step < cmd; step++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // Only move if no obstacle at next position
                    if (!obstacleSet.contains(nx + "," + ny)) {
                        x = nx;
                        y = ny;
                        maxDist = Math.max(maxDist, x * x + y * y);
                    } else {
                        break; // Blocked — stop this command
                    }
                }
            }
        }

        return maxDist;
    }
}