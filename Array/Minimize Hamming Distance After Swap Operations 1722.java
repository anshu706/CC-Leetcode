class Solution {
    private int[] parent, rank;

    private int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        // union by rank
        if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) rank[ra]++;
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // Step 1: Initialize Union-Find
        parent = new int[n];
        rank   = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Step 2: Union indices connected by allowedSwaps
        for (int[] swap : allowedSwaps)
            union(swap[0], swap[1]);

        // Step 3: Group indices by root → track source freq per group
        // Map: root -> (value -> count) for source elements
        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groups.computeIfAbsent(root, k -> new HashMap<>())
                  .merge(source[i], 1, Integer::sum);
        }

        // Step 4: For each index, check if target[i] can be matched
        int hammingDist = 0;
        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> freq = groups.get(root);
            int cnt = freq.getOrDefault(target[i], 0);
            if (cnt == 0) {
                hammingDist++; // no source value in this group matches target[i]
            } else {
                freq.put(target[i], cnt - 1); // consume one match
            }
        }

        return hammingDist;
    }
} 
