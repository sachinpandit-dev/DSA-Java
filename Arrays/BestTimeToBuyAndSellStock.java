/*
========================================================
Problem : Best Time to Buy and Sell Stock
Platform: LeetCode
Difficulty: Easy

Approach:
- Track minimum buying price
- Calculate profit at every step
- Update maximum profit

Time Complexity : O(N)
Space Complexity: O(1)

========================================================
*/

class Solution {

    public int maxProfit(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        int bestBuy = prices[0];

        for (int i = 1; i < prices.length; i++) {

            maxProfit = Math.max(maxProfit,
                                 prices[i] - bestBuy);

            bestBuy = Math.min(bestBuy,
                               prices[i]);
        }

        return maxProfit;
    }
}