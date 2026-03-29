class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int maxLen = 0;

        for (int num : set) {
            // Only start counting from the beginning of a sequence
            if (!set.contains(num - 1)) {
                int length = 1;
                while (set.contains(num + length)) length++;
                maxLen = Math.max(maxLen, length);
            }
        }

        return maxLen;
    }
}