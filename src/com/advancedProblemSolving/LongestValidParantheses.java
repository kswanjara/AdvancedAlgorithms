package com.advancedProblemSolving;

import com.longestsubseq.LongestSubsequence;

public class LongestValidParantheses {
    public static void main(String[] args) {
        String paranString = "())((())";
        LongestValidParantheses l = new LongestValidParantheses();
        int maxValidLength = l.findMaxValidLength(paranString.toCharArray());
        System.out.println("Longest Valid parantheses length : " + maxValidLength);
    }

    private int findMaxValidLength(char[] charArray) {
        int max = 0;
        int[] dpTable = new int[charArray.length];

        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == ')' && charArray[i - 1] == '(') {
                int prevValidLength = 0;
                if (i >= 2) {
                    prevValidLength = dpTable[i - 2];
                }
                dpTable[i] = prevValidLength + 2;
            } else if (charArray[i] == ')' && charArray[i - 1] == ')') {
                int prevValidLength = dpTable[i - 1];
                if (i - dpTable[i - 1] > 0 && charArray[i - dpTable[i - 1] - 1] == '(') {
                    {
                        if (i - dpTable[i - 1] >= 2) {
                            prevValidLength += dpTable[i - dpTable[i - 1] - 2];
                        }
                    }
                    dpTable[i] = prevValidLength + 2;
                }
            }
            max = Math.max(max, dpTable[i]);
        }
        return max;
    }
}
