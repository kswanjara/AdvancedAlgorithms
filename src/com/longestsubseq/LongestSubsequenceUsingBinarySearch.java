package com.longestsubseq;

import java.util.Arrays;

public class LongestSubsequenceUsingBinarySearch {

    public static void main(String[] args) {
        int[] seq = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestSubsequenceUsingBinarySearch subSeq = new LongestSubsequenceUsingBinarySearch();
        int longestSubSeq = subSeq.findLongestSubsequenceWithBinarySearch(seq);
        System.out.println("Length of longest subsequence : " + longestSubSeq);
    }

    /**
     * The time complexity of this algorithm is O(n.log n).
     *
     * @param seq given sequence
     * @return int representing the length of the lognest increasing subsequence.
     */
    public int findLongestSubsequenceWithBinarySearch(int[] seq) {

        int[] dpTable = new int[seq.length];
        int size = 0;

        for (int i = 0; i < seq.length; i++) {
            int index = Arrays.binarySearch(dpTable, 0, size, seq[i]);
            if (index < 0) {
                index = -(index + 1);
            }
            dpTable[index] = seq[i];
            if (index == size) {
                size += 1;
            }
        }
        return size;

    }

}
