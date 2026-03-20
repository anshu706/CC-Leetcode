class Solution {
    public String getHappyString(int n, int k) {
        // Each position has 2 choices (except first which has 3)
        // Total = 3 * 2^(n-1)
        int total = 3 * (1 << (n - 1));
        if (k > total)
            return "";

        StringBuilder result = new StringBuilder();
        k--; // 0-indexed

        // First character: 3 choices, each subtree has 2^(n-1) strings
        int subtree = 1 << (n - 1);
        result.append((char) ('a' + k / subtree));
        k %= subtree;

        // Subsequent characters: 2 choices each
        for (int i = 1; i < n; i++) {
            subtree >>= 1;
            char prev = result.charAt(result.length() - 1);

            // Available chars excluding prev, in sorted order
            StringBuilder choices = new StringBuilder();
            for (char c : new char[] { 'a', 'b', 'c' }) {
                if (c != prev)
                    choices.append(c);
            }

            int divisor = (subtree == 0) ? 1 : subtree;
            result.append(choices.charAt(k / divisor));
            k %= divisor;
        }

        return result.toString();
    }
}