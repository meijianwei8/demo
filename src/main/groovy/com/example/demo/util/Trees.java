package com.example.demo.util;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Trees extends Treeable<Trees>{

    private String id;
    private String pid;
    private String content;

    @Override
    public boolean connection(Trees trees) {
        return id.equalsIgnoreCase(pid);
    }

    @Override
    boolean root(Trees trees) {
        return false;
    }

    public static void main(String[] args) {
        TreeUtils<Trees> treesTreeUtils = new TreeUtils<>();

    }
}
