class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            // check if this number was seen as reverse before
            if (map.containsKey(curr)) {
                minDist = Math.min(minDist, i - map.get(curr));
            }

            // store reverse of current number
            int rev = reverse(curr);
            map.put(rev, i);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}