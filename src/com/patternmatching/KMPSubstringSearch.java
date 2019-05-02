package com.patternmatching;

import java.util.ArrayList;
import java.util.List;

public class KMPSubstringSearch {

    private List<Integer> searchForPattern(char[] text, char[] pattern) {
        List<Integer> result = new ArrayList<>();

        int[] partialMatch = getPartialMatchTable(pattern);

        int m = 0;
        int i = 0;
        while (m < text.length) {
            if (text[m] == pattern[i]) {
                m++;
                i++;
                if (i == pattern.length) {
                    result.add(m - i);
                    i = 0;
                }
            } else {
                if (i == 0) {
                    m++;
                } else {
                    i = partialMatch[i - 1];
                }
            }

        }

        return result;
    }

    private int[] getPartialMatchTable(char[] pattern) {
        int[] partialMatch = new int[pattern.length];

        int j = 0;
        int i = 1;
        partialMatch[j] = 0;
        while (i < pattern.length) {
            if (pattern[j] == pattern[i]) {
                partialMatch[i] = j + 1;
                j++;
                i++;
            } else {
                if (j == 0) {
                    partialMatch[i] = 0;
                    i++;
                } else {
                    j = partialMatch[j - 1];
                }
            }
        }
        return partialMatch;
    }

    public static void main(String[] args) {
        String text = "abxabcabcabyabcaby";
        String pattern = "abcaby";

        KMPSubstringSearch kmp = new KMPSubstringSearch();
        List<Integer> positions = kmp.searchForPattern(text.toCharArray(), pattern.toCharArray());
        System.out.println("Pattern found at the indexes : " + positions.toString());
    }
}
