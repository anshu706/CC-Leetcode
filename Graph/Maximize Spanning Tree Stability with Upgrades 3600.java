class Solution {
    private int[] parent, rank_;

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private boolean unite(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return false;
        if (rank_[a] < rank_[b]) { int tmp = a; a = b; b = tmp; }
        parent[b] = a;
        if (rank_[a] == rank_[b]) rank_[a]++;
        return true;
    }

    private boolean check(int mid, int n, int[][] edges, int k) {
        for (int i = 0; i < n; i++) { parent[i] = i; rank_[i] = 0; }

        int edgesUsed = 0, upgradesUsed = 0;

        // First pass: must-include edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (s < mid) return false; // must edge below threshold
                if (!unite(u, v)) return false; // cycle with must edges
                edgesUsed++;
            }
        }

        // Second pass: optional edges that don't need upgrade
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s >= mid) {
                if (unite(u, v)) edgesUsed++;
            }
        }

        // Third pass: optional edges that need upgrade
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 0 && s < mid && 2 * s >= mid) {
                if (upgradesUsed < k && unite(u, v)) {
                    edgesUsed++;
                    upgradesUsed++;
                }
            }
        }

        return edgesUsed == n - 1;
    }

    public int maxStability(int n, int[][] edges, int k) {
        parent = new int[n];
        rank_ = new int[n];

        // Collect all candidate values
        TreeSet<Integer> candidates = new TreeSet<>();
        for (int[] e : edges) {
            candidates.add(e[2]);
            candidates.add(e[2] * 2);
        }

        int[] vals = candidates.stream().mapToInt(Integer::intValue).toArray();

        // Check if spanning tree is even possible
        if (!check(1, n, edges, k)) return -1;

        int lo = 0, hi = vals.length - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (check(vals[mid], n, edges, k)) {
                ans = vals[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
