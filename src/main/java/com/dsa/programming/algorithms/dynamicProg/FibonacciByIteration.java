package com.dsa.programming.algorithms.dynamicProg;

public class FibonacciByIteration {
    private static int methodCounter=0;
    private static int[] fibbonacciList;
    public static void main(String[] args){
        int fibbonacciIndex=40;
        long startTime = System.nanoTime();
        fibbonacciList = new int[fibbonacciIndex+1];
        fibbonacciList[0] = 1;
        fibbonacciList[1] = 1;
        int fibbonacciNumber = calculateFibbonacci(fibbonacciIndex);
        long endTime = System.nanoTime();

        System.out.println("Calculating Fibbonacci using iteration");
        System.out.println("Fibbonacci index: " + fibbonacciIndex);
        System.out.println("Fibbonacci value: " + fibbonacciNumber);
        System.out.println("Time taken : " +(endTime-startTime)/1000+" ms");
        System.out.println("No. of method calls performed to calculate fibbonacci value: " + methodCounter);
    }

    private static int calculateFibbonacci(int n) {
        for(int index=2; index<=n; index++) {
            methodCounter++;
            fibbonacciList[index] = fibbonacciList[index-1] + fibbonacciList[index-2];
        }
        return fibbonacciList[n];
    }
}
