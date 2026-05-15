/*
========================================================
Problem : Remove Duplicates from Sorted Array
Platform: LeetCode
Difficulty: Easy

Given a sorted array nums,
remove duplicates in-place such that each
unique element appears only once.

========================================================
Approach 1 : Better Approach

Idea:
- Create a new temporary array
- Store only unique elements

Time Complexity : O(N)
Space Complexity: O(N)

========================================================
Approach 2 : Optimal Approach

Idea:
- Use two pointers
- i -> tracks unique elements
- j -> traverses array

Time Complexity : O(N)
Space Complexity: O(1)

========================================================
*/

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    /*
    ----------------------------------------------------
    Better Approach
    ----------------------------------------------------
    */
    public static void betterApproach(int[] nums) {

        int n = nums.length;

        int[] arr = new int[n];

        arr[0] = nums[0];

        int t = 0;

        for (int i = 1; i < n; i++) {

            if (nums[i] != arr[t]) {

                t++;

                arr[t] = nums[i];
            }
        }

        System.out.println("Unique Elements (Better Approach):");

        for (int i = 0; i <= t; i++) {

            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    /*
    ----------------------------------------------------
    Optimal Approach
    ----------------------------------------------------
    */
    public static void optimalApproach(int[] nums) {

        int n = nums.length;

        int i = 0;

        for (int j = 1; j < n; j++) {

            if (nums[i] != nums[j]) {

                i++;

                nums[i] = nums[j];
            }
        }

        System.out.println("Unique Elements (Optimal Approach):");

        for (int k = 0; k <= i; k++) {

            System.out.print(nums[k] + " ");
        }

        System.out.println();

        System.out.println("Total Unique Elements : " + (i + 1));
    }

    /*
    ----------------------------------------------------
    Main Method
    ----------------------------------------------------
    */
    public static void main(String[] args) {

        int[] nums1 = {0,0,1,1,1,2,2,3,3,4};

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};

        betterApproach(nums1);

        System.out.println();

        optimalApproach(nums2);
    }
}