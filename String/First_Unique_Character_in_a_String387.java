class Solution {
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        int size = s.length();

        for (int i = 0; i < size; i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < size; i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}