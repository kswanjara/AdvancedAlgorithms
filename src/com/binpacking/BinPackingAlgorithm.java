package com.binpacking;

import java.util.Scanner;

public class BinPackingAlgorithm {

    private void findBestFit(int num, int capacity, int[] wt) {
        int[] bins = new int[num];
        int totalBins = 0;

        for (int i = 0; i < num; i++) {
            int possibleBin = -1;
            int minGap = Integer.MAX_VALUE;
            for (int j = 0; j < totalBins; j++) {
                if (bins[j] + wt[i] <= capacity && (capacity - (bins[j] + wt[i])) <= minGap) {
                    possibleBin = j;
                    minGap = capacity - (bins[j] + wt[i]);
                }
            }

            if (possibleBin != -1) {
                bins[possibleBin] += wt[i];
            } else {
                bins[totalBins] = wt[i];
                totalBins++;
            }
        }

        System.out.println("Minimum bins required : " + totalBins);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = 7;
        int[] wt = {2, 5, 4, 7, 1, 3, 8};
        int capacity = 10;

        char c = '0';
        do {
            System.out.println("User input(press u) or Default values(press d): ");
            c = sc.nextLine().charAt(0);
        } while (c != 'u' && c != 'U' && c != 'd' && c != 'D');

        if (c != 'd' && c != 'D') {
            System.out.println("Total number of weights : ");
            num = sc.nextInt();

            wt = new int[num];
            System.out.println("Enter total " + num + " available weights(Press enter after each entry) : ");
            for (int i = 0; i < num; i++) {
                wt[i] = sc.nextInt();
            }

            System.out.println("Enter capacity of each bin : ");
            capacity = sc.nextInt();
        }

        new BinPackingAlgorithm().findBestFit(num, capacity, wt);
    }

}
