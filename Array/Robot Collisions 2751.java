
class Solution {

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        // Create indices array and sort by position
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> positions[a] - positions[b]);

        // Stack holds original indices of right-moving robots
        Deque<Integer> stack = new ArrayDeque<>();
        // Track which robots are removed
        boolean[] removed = new boolean[n];

        for (int idx : indices) {
            char dir = directions.charAt(idx);

            if (dir == 'R') {
                // Push right-moving robot; it may collide with future L robots
                stack.push(idx);
            } else {
                // Left-moving robot: resolve collisions with R robots on stack
                while (!stack.isEmpty()) {
                    int topIdx = stack.peek();

                    if (healths[topIdx] > healths[idx]) {
                        // R robot wins: reduce its health, L robot dies
                        healths[topIdx]--;
                        removed[idx] = true;
                        break;
                    } else if (healths[topIdx] < healths[idx]) {
                        // L robot wins: reduce its health, R robot dies
                        healths[idx]--;
                        removed[topIdx] = true;
                        stack.pop(); // remove dead R robot, continue checking
                    } else {
                        // Equal health: both die
                        removed[topIdx] = true;
                        removed[idx] = true;
                        stack.pop();
                        break;
                    }
                }
                // If stack is empty and L robot survived, it just moves left freely
            }
        }

        // Collect survivors in original order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
                result.add(healths[i]);
            }
        }

        return result;
    }
}
 
