package com.dsa.practice.arrays.codeSignal;

/**
 * You are part of a software development team working on a text analysis tool that examines strings for specific patterns. One of
 * the features requested by your client is to identify and count all triplets of consecutive characters within strings where the first
 * and last characters of each triplet are the same. Detecting such triplets can help in recognizing repetitive structures and
 * potential anomalies in the text, which is valuable for many natural language processing tasks.
 * Given a string text consisting of English letters (both uppercase and lowercase), your task is to count all triplets of
 * consecutive characters where the first and last characters are the same (case insensitive).
 * Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than
 * 0(text.length²) will fit within the execution time limit.
 * Example
 * For text = "aXA", the output should be solution(text) = 1
 * Explanation:
 * The only triplet in the string is "aXA", and it satisfies the condition where the first and last characters are the same (case
 * insensitive). Hence, the answer is 1.
 * For text = "", the output should be solution(text) = 0
 * Explanation:
 * There are no triplets in the initial string at all.
 * For text = "abCXccc", the output should be solution(text) = 2.
 * Explanation:
 * There are two triplets that meet the condition: "CXc" and "ccc". Therefore, the answer is 2.
 * For text = "aaa", the output should be solution(text) = 1
 * Explanation:
 * The string consists of a single valid triplet "aaa". The first and last characters are the same, so the answer is 1.
 */
public class TripletCount {
    public int getTripletCount(String input) {
        int resultCount=0;
        if(input==null || input.isEmpty()) return resultCount;
        char[] inputArr = input.toLowerCase().toCharArray();
        if(inputArr.length<3) return resultCount;
        for(int startIndex=0; startIndex<=inputArr.length-3; startIndex++) {
            int endIndex = startIndex+2;
            if(inputArr[startIndex]==inputArr[endIndex]) {
                resultCount++;
            }
        }
        return resultCount;
    }
}