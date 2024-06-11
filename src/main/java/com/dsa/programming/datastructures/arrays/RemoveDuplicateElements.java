package com.dsa.programming.datastructures.arrays;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

/**
 * Given an array of integers nums and a value val.
 * Remove all occurrences of val in-place from the array and return the new length of the array
 * after removal. The order of elements can be changed. No need to consider elements beyond the new length.
 *
 * Method Signature:
 * public static int removeElement(int[] nums, int val)
 *
 * Constraints
 * The array nums can contain negative and positive numbers, and 0.
 *
 * Example
 * Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * val = 1
 * Output: new length: 7, modified array: [-2, -3, 4, -1, 2, -5, 4, -5, 4]
 */
public class RemoveDuplicateElements {
    private static int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    private static int val = 1;
    private static int result = -1;
    public static void main(String[] args){
        int[] expectedArr = {-2, -3, 4, -1, 2, -5, 4, -5, 4};
        System.out.print("Input array: [ ");
        Arrays.stream(input).forEach(elem -> System.out.print(+elem+ " \t"));
        System.out.print("]"); System.out.println();
        result = removeElement(input, val);
        System.out.print("Modified array: [ ");
        Arrays.stream(input).forEach(elem -> System.out.print(+elem+ " \t"));
        System.out.print("]"); System.out.println();
        Assertions.assertEquals(7, result, "Result length does NOT match expected length");
        Assertions.assertArrayEquals(expectedArr, input,"Result does not match expected array");

    }
    public static int removeElement(int[] nums, int val){
        int resultLength=0;
        for(int index=0; index<nums.length; index++) {
            if(nums[index] != val) {
                nums[resultLength] = nums[index];
                resultLength++;
            }
        }
        return resultLength;
    }
}
