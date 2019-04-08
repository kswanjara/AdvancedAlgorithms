package com.knapsack;

/**
 * This class holds the implementation of Unbounded Knapsack Problem.
 *
 * @author Kunal Wanjara
 */
public class UnboundedKnapsack {
    public static void main(String[] args) {
        int[] wt = {10, 20, 50};
        int[] val = {5, 5, 2};
        int W = 75;

        int maxVal = unboundedKnapsack(wt, val, W);
        System.out.println(maxVal);
    }

    private static int unboundedKnapsack(int[] wt, int[] val, int W) {

        int sub[] = new int[W + 1];

        for (int i = 0; i < W + 1; i++) {
            for (int j = 0; j < wt.length; j++) {
                if (wt[j] <= i) {
                    sub[i] = Math.max(sub[i], sub[i - wt[j]] +
                            val[j]);
                }
            }
        }
        return sub[W];
    }
}
