package com.advancedProblemSolving;

public class CommonChildProblem {
    public static void main(String[] args) {
        String a = "ABCD";
        String b = "ABDC";

        CommonChildProblem c = new CommonChildProblem();

        int maxLength = c.findMaxLengthCommonChild(a, b);
        System.out.println("Common child with maximum length : " + maxLength);
    }

    private int findMaxLengthCommonChild(String a, String b) {
        int[][] dpTable = new int[a.length() + 1][b.length() + 1];

        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        for (int i = 0; i < charA.length; i++) {
            for (int j = 0; j < charB.length; j++) {
                if (charA[i] == charB[j]) {
                    dpTable[i + 1][j + 1] = dpTable[i][j] + 1;
                } else {
                    dpTable[i + 1][j + 1] = Math.max(dpTable[i + 1][j], dpTable[i][j + 1]);
                }
            }
        }
        return dpTable[a.length()][b.length()];
    }
}
