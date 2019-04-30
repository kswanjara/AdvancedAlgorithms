package com.binpacking;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BinPackingAlgorithm {

    private int findFirstFitDecreasing(int num, int capacity, Integer[] wt) {

        // sort weights in descending order
        Arrays.sort(wt, Collections.reverseOrder());
        int[] bins = new int[num];
        int totalBins = 0;

        for (int i = 0; i < num; i++) {
            int possibleBin = -1;
            for (int j = 0; j < totalBins; j++) {
                if (bins[j] + wt[i] <= capacity) {
                    possibleBin = j;
                    break;
                }
            }
            if (possibleBin != -1) {
                bins[possibleBin] += wt[i];
            } else {
                bins[totalBins] = wt[i];
                totalBins++;
            }
        }
        return totalBins;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = 7;
        Integer[] wt = {2, 5, 4, 7, 1, 3, 8};
        int capacity = 10;

        char c = '0';
        do {
            System.out.println("User input(press u) or Default values(press d): ");
            c = sc.nextLine().charAt(0);
        } while (c != 'u' && c != 'U' && c != 'd' && c != 'D');

        if (c != 'd' && c != 'D') {
            System.out.println("Total number of weights : ");
            num = sc.nextInt();

            wt = new Integer[num];
            System.out.println("Enter total " + num + " available weights(Press enter after each entry) : ");
            for (int i = 0; i < num; i++) {
                wt[i] = sc.nextInt();
            }

            System.out.println("Enter capacity of each bin : ");
            capacity = sc.nextInt();
        }

        BinPackingAlgorithm b = new BinPackingAlgorithm();
        int totalBins = b.findFirstFitDecreasing(num, capacity, wt);
        System.out.println("Minimum bins required : " + totalBins);
    }

}
