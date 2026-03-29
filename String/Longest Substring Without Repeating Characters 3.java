class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 0;
        int left   = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If duplicate found WITHIN current window, jump left past it
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                left = lastIndex.get(c) + 1;
            }

            lastIndex.put(c, right);                    // update last seen index
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}