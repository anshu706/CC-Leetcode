class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int len = n + m - 1;
        char[] word = new char[len];
        Arrays.fill(word, 'a');
        boolean[] fixed = new boolean[len]; // positions forced by T constraints

        // Step 1: Apply T constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (fixed[i + j] && word[i + j] != str2.charAt(j)) {
                        return ""; // conflict between two T windows
                    }
                    word[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }

        // Step 2: Build diff count using sliding window
        // diff[i] = # positions where word[i+j] != str2[j]
        int[] diff = new int[n];
        for (int j = 0; j < m; j++) {
            if (word[j] != str2.charAt(j)) diff[0]++;
        }
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i-1];
            // remove contribution of position i-1, add position i+m-1
            if (word[i-1] != str2.charAt(0)) diff[i]--;  // wrong, need offset
            // redo with proper sliding
            diff[i] = 0; // will recompute properly below
        }

        // Recompute diff properly
        Arrays.fill(diff, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (word[i+j] != str2.charAt(j)) diff[i]++;
            }
        }

        // Step 3: Handle F constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F' && diff[i] == 0) {
                // window matches str2, must break it
                // find rightmost non-fixed position in [i, i+m-1]
                boolean broken = false;
                for (int j = m - 1; j >= 0; j--) {
                    if (!fixed[i + j]) {
                        char old = word[i + j];
                        word[i + j] = (str2.charAt(j) != 'a') ? 'a' : 'b';
                        // update diff for all windows covering position i+j
                        char nc = word[i + j];
                        // update diff array for affected windows
                        for (int k = Math.max(0, i+j - m + 1); k <= Math.min(n-1, i+j); k++) {
                            int jj = (i+j) - k;
                            if (old != str2.charAt(jj) && nc == str2.charAt(jj)) diff[k]--;
                            else if (old == str2.charAt(jj) && nc != str2.charAt(jj)) diff[k]++;
                        }
                        broken = true;
                        break;
                    }
                }
                if (!broken) return ""; // all fixed but window == str2 with T forcing
            }
        }

        // Final validation
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T' && diff[i] != 0) return "";
            if (str1.charAt(i) == 'F' && diff[i] == 0) return "";
        }

        return new String(word);
    }
}