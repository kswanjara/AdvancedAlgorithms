package com.longestsubseq;

public class LongestSubsequence {
    public static void main(String[] args) {
        int[] seq = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestSubsequence subSeq = new LongestSubsequence();
        int longestSubSeq = subSeq.findLongestSubsequence(seq);
        System.out.println("Length of longest subsequence : " + longestSubSeq);
    }

    private int findLongestSubsequence(int[] seq) {
        int longestSubseq = 1;
        if(seq.length == 0){
            return 0;
        }

        int[] dpTable = new int[seq.length];
        dpTable[0] = 1;

        for(int i = 0; i < seq.length ; i++){
            int temp = 0;
            for(int j = 0; j < i; j++){
                if(seq[i] > seq[j]){
                    temp = Math.max(temp, dpTable[j]);
                }
            }
            dpTable[i] = temp + 1;
            longestSubseq = Math.max(longestSubseq, dpTable[i]);
        }
        return longestSubseq;
    }
}
