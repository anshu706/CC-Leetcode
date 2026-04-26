
class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        // Linearize points onto perimeter [0, 4*side)
        int n = points.length;
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            pos[i] = perimeterPos(points[i][0], points[i][1], side);
        }
        Arrays.sort(pos);

        long lo = 1, hi = 2L * side, ans = 0;
        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if (canPlace(pos, k, mid, side)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) ans;
    }

    // Convert (x, y) on square boundary to perimeter position
    private long perimeterPos(int x, int y, int side) {
        if (y == 0) {
            return x;                        // bottom: left→right

        }
        if (x == side) {
            return side + y;              // right: bottom→top

        }
        if (y == side) {
            return 3L * side - x;         // top: right→left

        }
        return 4L * side - y;                        // left: top→bottom
    }

    // Check if we can place k points each at least minDist apart (perimeter distance)
    private boolean canPlace(long[] pos, int k, long minDist, int side) {
        long perimeter = 4L * side;
        int n = pos.length;

        // Try each point as the starting point
        for (int start = 0; start < n; start++) {
            int count = 1;
            long cur = pos[start];
            long startPos = pos[start];
            int idx = start;

            for (int step = 1; step < k; step++) {
                long target = cur + minDist;
                // Binary search for first pos >= target in circular array
                int next = lowerBound(pos, target, idx + 1, n);

                if (next == n) {
                    // Wrap around - but must not overlap with start's window
                    // Points after wrap: their effective pos = pos[j] + perimeter
                    long wrappedTarget = target - perimeter;
                    next = lowerBound(pos, wrappedTarget, 0, start);
                    if (next >= start) {
                        break; // no valid point
                    }                    // Check circular distance back to start is ok
                    // Last point chosen: pos[next] (+ perimeter if wrapped)
                    cur = pos[next] + perimeter;
                } else {
                    cur = pos[next];
                }
                idx = next % n;
                count++;
            }

            if (count == k) {
                // Verify last point to first point distance
                long lastPos = cur;
                long firstPos = pos[start] + perimeter;
                long gap = firstPos - lastPos;
                if (gap >= minDist) {
                    return true;
                }
            }
        }
        return false;
    }

    private int lowerBound(long[] arr, long target, int lo, int hi) {
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
 
