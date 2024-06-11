package com.dsa.programming.algorithms.webcrawler;

import java.io.IOException;

public class WebsiteCrawler {
    private static final String rootUrl="https://www.bbc.com";
    public static void main(String[] args) {
        BFS bfs = new BFS(rootUrl);
        bfs.visitUrl();
    }
}
