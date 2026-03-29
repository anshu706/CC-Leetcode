class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        // Step 1: Greedily assign characters
        char nextChar = 'a';
        for (int i = 0; i < n; i++) {
            if (word[i] != '\0')
                continue;
            if (nextChar > 'z')
                return "";
            word[i] = nextChar++;
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = word[i];
                }
            }
        }

        // Step 2: Check diagonal - lcp[i][i] must equal n - i
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i)
                return "";
        }

        // Step 3: Validate using DP from bottom-right
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    int expected = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                    if (lcp[i][j] != expected)
                        return "";
                } else {
                    if (lcp[i][j] != 0)
                        return "";
                }
            }
        }

        return new String(word);
    }
} 
