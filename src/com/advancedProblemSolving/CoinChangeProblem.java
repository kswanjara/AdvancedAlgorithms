package com.advancedProblemSolving;

public class CoinChangeProblem {
    public static void main(String[] args) {
        int total = 12;
        int[] coins = {2, 3, 5};
        
        CoinChangeProblem c = new CoinChangeProblem();
        
        int minCoins = c.getMinCoinsRequired(coins, total);

        System.out.println("Min coins required : " + minCoins);
    }

    private int getMinCoinsRequired(int[] coins, int total) {
        if(total <= 0)
            return 0;
        
        return coinChangeHelper(coins, total, new int[total]);
    }

    private int coinChangeHelper(int[] coins, int bal, int[] dPTable) {
        if(bal < 0)
            return -1;

        if(bal == 0)
            return 0;

        if(dPTable[bal - 1] != 0)
            return dPTable[bal - 1];

        int minVal = Integer.MAX_VALUE;
        for(int i  = 0; i < coins.length ; i++){
            int nextVal = coinChangeHelper(coins, bal - coins[i], dPTable);
            if(nextVal >= 0 && nextVal < minVal){
                minVal = nextVal + 1;
            }
        }
        if(minVal != Integer.MAX_VALUE){
            dPTable[bal - 1] = minVal;
        }else{
            dPTable[bal - 1] = -1;
        }
        return dPTable[bal - 1];
    }
}
