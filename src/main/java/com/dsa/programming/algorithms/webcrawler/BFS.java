package com.dsa.programming.algorithms.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to crawl URL links from webpage using Breadth First Search technique.
 */
public class BFS {
    private Queue<String> urlToBeVisited;
    private List<String> discoveredUrls;
    public BFS(String root) {
        urlToBeVisited = new LinkedList<>();
        discoveredUrls = new ArrayList<>();
        urlToBeVisited.add(root);
        discoveredUrls.add(root);
    }

    public void visitUrl() {
        while(!urlToBeVisited.isEmpty()) {
            String currentUrl = urlToBeVisited.remove();
            try {
                System.out.println(currentUrl);
                String rawHtmlContent = getHtmlContent(currentUrl);
                String urlRegex = "https://(\\w+\\.)*(\\w+)";

                Pattern urlPattern = Pattern.compile(urlRegex);
                Matcher urlMatcher = urlPattern.matcher(rawHtmlContent);

                while(urlMatcher.find()){
                    String aUrlFromHtml = urlMatcher.group();
                    if(!discoveredUrls.contains(aUrlFromHtml)){
                        discoveredUrls.add(aUrlFromHtml);
                        urlToBeVisited.add(aUrlFromHtml);
                    }
                }
            } catch (Exception exp) {
                System.out.println("Problem encountered while crawling webpage: ");
                exp.printStackTrace();
            }
        }
    }

    private String getHtmlContent(String currentUrl) throws IOException {
        BufferedReader bufferedReader = null;
        StringBuilder htmlContentBuilder = new StringBuilder();
        try {
            URL curUrlObj = new URL(currentUrl);
            bufferedReader = new BufferedReader(new InputStreamReader(curUrlObj.openStream()));
            String aLine = null;
            while ((aLine = bufferedReader.readLine()) != null) {
                htmlContentBuilder.append(aLine);
            }
            System.out.println("Completed reading url: " + currentUrl);
        } catch (Exception exp) {
            System.out.println("Problem encountered while visiting url: " + currentUrl);
        } finally {
            if(null!=bufferedReader) {
                bufferedReader.close();
            }
        }
        return htmlContentBuilder.toString();
    }
}
