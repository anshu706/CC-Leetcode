class Solution {
    public char findTheDifference(String s, String t) {

        int[] freq = new int[26];

        // count characters in s
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // subtract characters in t
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i) - 'a']--;
        }

        // find the leftover character
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return (char) ('a' + i);
            }
        }

        return ' '; // should never reach here
    }
}