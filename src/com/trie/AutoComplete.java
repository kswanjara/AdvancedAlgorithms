package com.trie;

import java.util.*;

public class AutoComplete {
    public static void main(String[] args) {

        Trie t = new Trie();

        t.insert("hello");
        t.insert("dog");
        t.insert("hell");
        t.insert("cat");
        t.insert("a");
        t.insert("hel");
        t.insert("help");
        t.insert("helps");
        t.insert("helping");

        t.printAutoSuggestions("hel");

    }
}

class Trie {
    private Node root = new Node(Character.MIN_VALUE, false);

    public void insert(String word) {
        insertHelper(word, 0, root);
    }

    public Node insertHelper(String word, int index, Node curr) {
        if (index == word.length()) {
            return null;
        }
        Node newChar;
        if (curr.getChildren().containsKey(word.charAt(index))) {
            newChar = curr.getChildren().get(word.charAt(index));
        } else {
            newChar = new Node(word.charAt(index), false);
        }

        if (index == word.length() - 1) {
            newChar.setEndOfWord(true);
        }
        curr.getChildren().put(word.charAt(index), newChar);
        return insertHelper(word, ++index, newChar);
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    public boolean searchHelper(String word, int index, Node curr) {
        if (curr == null) {
            return false;
        }
        if (index == word.length()) {
            return curr.isEndOfWord();
        }
        Node nextChar = curr.getChildren().get(word.charAt(index));
        return searchHelper(word, ++index, nextChar);
    }

    public void printAutoSuggestions(String word){
        Node temp = root;
        int index = 0;
        while(index < word.length()){
            temp = temp.getChildren().get(word.charAt(index));
            index++;
            if(temp == null){
                System.out.println("No suggestions for the given word!");
                return;
            }
        }

        traverseAll(temp, word);
    }

    public void traverseAll(Node temp, String word){
        if(temp.isEndOfWord()){
            System.out.println(word);
        }
        for(Node n : temp.getChildren().values()){
            traverseAll(n, word + n.getC());
        }
    }
}

class Node {
    private char c;
    private Map<Character, Node> children;
    private boolean isEndOfWord;

    public Node(char c, boolean isEndOfWord) {
        this.c = c;
        this.isEndOfWord = isEndOfWord;
        this.children = new HashMap<>();
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, Node> children) {
        this.children = children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return c == node.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}