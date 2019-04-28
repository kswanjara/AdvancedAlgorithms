package com.advancedProblemSolving;

public class DistinctSubsequence {

    private int findNumOfDistinctSubseqDynamic(String s, String t) {
        int[][] dynamicTable = new int[t.length() + 1][s.length() + 1];

        for(int i = 0; i < s.length(); i++){
            dynamicTable[0][i] = 1;
        }

        for(int i = 0; i < t.length(); i++){
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) == t.charAt(i))
                {
                    dynamicTable[i+1][j+1] = dynamicTable[i][j] + dynamicTable[i+1][j];
                }else{
                    dynamicTable[i+1][j+1] = dynamicTable[i+1][j];
                }
            }
        }

        return dynamicTable[t.length()][s.length()];
    }

    private int findNumOfDistinctSubseq(String s, String t) {
        if (s.equals(t))
            return 1;
        return helperFunction(s.toCharArray(), t.toCharArray(), s.length() - 1, t.length() - 1);
    }

    private int helperFunction(char[] s, char[] t, int indexS, int indexT) {
        if (indexT == -1)
            return 1;

        if (indexS == -1 && indexT == -1)
            return 1;

        if(indexS == -1)
            return 0;

        if (s[indexS] == t[indexT]) {
            return helperFunction(s, t, indexS - 1, indexT - 1) + helperFunction(s, t, indexS - 1, indexT);
        } else {
            return helperFunction(s, t, indexS - 1, indexT);
        }
    }

    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";

//        String s = "rabbbit";
//        String t = "rabbit";

        DistinctSubsequence d = new DistinctSubsequence();
        int num = d.findNumOfDistinctSubseqDynamic(s, t);
        System.out.println("Total distinct subsequences : " + num);
    }
}
