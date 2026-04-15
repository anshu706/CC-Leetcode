class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int clockwise     = (i - startIndex + n) % n;
                int counterClock  = (startIndex - i + n) % n;
                minDist = Math.min(minDist, Math.min(clockwise, counterClock));
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}