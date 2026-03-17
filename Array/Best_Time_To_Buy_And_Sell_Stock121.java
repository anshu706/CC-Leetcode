class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, bestBuy = prices[0];

        for(int i = 1; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - bestBuy);
            bestBuy = Math.min(bestBuy, prices[i]);
        }
        return maxProfit;
    }
}