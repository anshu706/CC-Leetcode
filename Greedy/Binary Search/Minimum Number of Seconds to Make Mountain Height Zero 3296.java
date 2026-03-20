class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long lo = 0;
        long hi = (long) Arrays.stream(workerTimes).max().getAsInt()
                  * mountainHeight * (mountainHeight + 1) / 2;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (canFinish(mid, mountainHeight, workerTimes)) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    private boolean canFinish(long t, int mountainHeight, int[] workerTimes) {
        long total = 0;
        for (int wt : workerTimes) {
            total += maxReduction(t, wt);
            if (total >= mountainHeight) return true;
        }
        return false;
    }

    private long maxReduction(long t, long wt) {
        // wt * x*(x+1)/2 <= t  =>  x^2 + x - 2t/wt <= 0
        return (long) ((-1 + Math.sqrt(1 + 8.0 * t / wt)) / 2);
    }
}