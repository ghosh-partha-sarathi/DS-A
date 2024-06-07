package com.dsa.programming.algorithms.dynamicProg;

public class FibonacciByRecursion {
    private static int methodCounter=0;
    public static void main(String[] args){
        int fibbonacciIndex=40;
        long startTime = System.nanoTime();
        int fibbonacciNumber = calculateFibbonacci(fibbonacciIndex);
        long endTime = System.nanoTime();

        System.out.println("Calculating Fibbonacci using recursion");
        System.out.println("Fibbonacci index: " + fibbonacciIndex);
        System.out.println("Fibbonacci value: " + fibbonacciNumber);
        System.out.println("Time taken : " +(endTime-startTime)/1000+" ms");
        System.out.println("No. of method calls performed to calculate fibbonacci value: " + methodCounter);
    }

    private static int calculateFibbonacci(int n) {
        methodCounter++;
        if(n==0 || n==1) return n;
        return calculateFibbonacci(n-1) + calculateFibbonacci(n-2);
    }
}
