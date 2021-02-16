package com.leetcode.dp;

public class solution322 {
    int memo[];
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        memo = new int[amount+1];
        return dp(coins,amount);
    }
    private int dp(int []coins, int amount){
        int res =  Integer.MAX_VALUE;
        if(amount == 0) return 0;
        if(amount < 0) return -1;
        if(memo[amount] != 0) return memo[amount];
        for(int coin:coins){
            int subproblem = dp(coins,amount - coin);
            if(subproblem != -1)
            res = Math.min(res,subproblem+1);
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }
}
