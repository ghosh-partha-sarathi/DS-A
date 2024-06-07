package com.dsa.programming.algorithms.dynamicProg;

public class FibonacciWithMemoization {
    private static int methodCounter=0;

    private static Integer[] memo;
    public static void main(String[] args){
        int fibbonacciIndex=40;
        long startTime = System.nanoTime();
        memo=new Integer[fibbonacciIndex+1];
        int fibbonacciNumber = calculateFibbonacci(fibbonacciIndex);
        long endTime = System.nanoTime();

        System.out.println("Calculating Fibbonacci using memoization");
        System.out.println("Fibbonacci index: " + fibbonacciIndex);
        System.out.println("Fibbonacci value: " + fibbonacciNumber);
        System.out.println("Time taken : " +(endTime-startTime)/1000+" ms");
        System.out.println("No. of method calls performed to calculate fibbonacci value: " + methodCounter);
    }

    private static int calculateFibbonacci(int n) {
        if(n==0 || n==1) return n;
        if(memo[n] != null) {
            return memo[n];
        }
        methodCounter++;
        memo[n]=calculateFibbonacci(n-1) + calculateFibbonacci(n-2);
        return memo[n];
    }
}
