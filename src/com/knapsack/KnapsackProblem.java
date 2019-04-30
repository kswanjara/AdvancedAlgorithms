package com.knapsack;

import java.util.Scanner;

/**
 * This class holds the implementation of Unbounded Knapsack Problem.
 *
 * @author Kunal Wanjara
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Total number of weights : ");
        int num = scanner.nextInt();

        int[] wt = new int[num];
        int[] val = new int[num];

        System.out.println("Enter total " + num + " available weights(Press enter after each entry) : ");
        for (int i = 0; i < num; i++) {
            wt[i] = scanner.nextInt();
        }

        System.out.println("Enter total " + num + " values corresponding to each weight(Press enter after each entry) : ");
        for (int i = 0; i < num; i++) {
            val[i] = scanner.nextInt();
        }

        System.out.println("Enter capacity of Knapsack : ");

        int W = scanner.nextInt();

        System.out.println();
//        int[] wt = {10, 20, 50};
//        int[] val = {5, 5, 2};
//        int W = 75;

        System.out.println("Unbounded Knapsack Solution:");
        System.out.println(unboundedKnapsack(wt, val, W) + "\n");

        System.out.println("Fractional Knapsack Solution:");
        System.out.println(unboundedKnapsack(wt, val, W) + "\n");
    }


    private static int unboundedKnapsack(int[] wt, int[] val, int W) {

        int dpTable[] = new int[W + 1];

        for (int i = 0; i < W + 1; i++) {
            for (int j = 0; j < wt.length; j++) {
                if (wt[j] <= i) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[i - wt[j]] + val[j]);
                }
            }
        }
        return dpTable[W];
    }
}
