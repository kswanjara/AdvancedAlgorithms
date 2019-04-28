package com.trie;

import java.util.*;

public class ReplaceWord {
    public static void main(String[] args) {

        List<String> roots = new ArrayList<>();
        roots.add("cat");
        roots.add("bat");
        roots.add("rat");
        String sentence = "the cattle was rattled by the battery";

        System.out.println(replaceWords(roots, sentence));
    }

    private static String replaceWords(List<String> roots, String sentence) {
        TrieDS t = new TrieDS();

        for (String s : roots) {
            t.insert(s);
        }

        StringBuffer res = new StringBuffer(" ");

        for (String word : sentence.split(" ")) {
            TrieNode temp = t.getRoot();
            for (char c : word.toCharArray()) {
                if (temp.getChildren().containsKey(c)) {
                    temp = temp.getChildren().get(c);
                    if (temp.getWord() != null) {
                        res.append(temp.getWord() + " ");
                        break;
                    }
                } else {
                    res.append(word + " ");
                    break;
                }
            }
        }

        return res.toString();
    }
}

class TrieDS {
    private TrieNode root = new TrieNode(Character.MIN_VALUE, null);

    public void insert(String word) {
        insertHelper(word, 0, root);
    }

    public TrieNode insertHelper(String word, int index, TrieNode curr) {
        if (index == word.length()) {
            return null;
        }
        TrieNode newChar;
        if (curr.getChildren().containsKey(word.charAt(index))) {
            newChar = curr.getChildren().get(word.charAt(index));
        } else {
            newChar = new TrieNode(word.charAt(index), null);
        }

        if (index == word.length() - 1) {
            newChar.setWord(word);
        }
        curr.getChildren().put(word.charAt(index), newChar);
        return insertHelper(word, ++index, newChar);
    }

    public TrieNode getRoot() {
        return this.root;
    }
}

class TrieNode {
    private char c;
    private Map<Character, TrieNode> children;
    private String word;

    public TrieNode(char c, String word) {
        this.c = c;
        this.word = word;
        this.children = new HashMap<>();
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieNode node = (TrieNode) o;
        return c == node.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}