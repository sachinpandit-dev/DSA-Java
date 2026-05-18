/*
========================================================
Problem : Maximum Subarray
Platform: LeetCode
Difficulty: Medium

Given an integer array nums,
find the subarray with the largest sum
and return its sum.

========================================================
Approach 1 : Print All Subarrays

Idea:
- Generate all possible subarrays
- Used for understanding subarray problems

Time Complexity : O(N^3)
Space Complexity: O(1)

========================================================
Approach 2 : Better Approach

Idea:
- Avoid recalculating subarray sum
- Use running sum for every start index

Time Complexity : O(N^2)
Space Complexity: O(1)

========================================================
Approach 3 : Optimal Approach (Kadane's Algorithm)

Idea:
- Keep adding elements to current sum
- If current sum becomes negative,
  reset it to 0
- Track maximum sum continuously

Time Complexity : O(N)
Space Complexity: O(1)

========================================================
*/

public class MaximumSubarray {

    /*
    ----------------------------------------------------
    Approach 1 : Print All Subarrays
    ----------------------------------------------------
    */
    public static void printAllSubarrays(int[] arr) {

        System.out.println("All Subarrays:");

        for (int start = 0; start < arr.length; start++) {

            for (int end = start; end < arr.length; end++) {

                for (int i = start; i <= end; i++) {

                    System.out.print(arr[i] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    /*
    ----------------------------------------------------
    Approach 2 : Better Approach
    ----------------------------------------------------
    */
    public static int betterApproach(int[] arr) {

        int maxSum = Integer.MIN_VALUE;

        for (int start = 0; start < arr.length; start++) {

            int currentSum = 0;

            for (int end = start; end < arr.length; end++) {

                currentSum = currentSum + arr[end];

                maxSum = Math.max(currentSum, maxSum);
            }
        }

        return maxSum;
    }

    /*
    ----------------------------------------------------
    Approach 3 : Optimal Approach
    Kadane's Algorithm
    ----------------------------------------------------
    */
    public static int optimalApproach(int[] nums) {

        int currentSum = 0;

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {

            currentSum = currentSum + nums[i];

            maxSum = Math.max(currentSum, maxSum);

            if (currentSum < 0) {

                currentSum = 0;
            }
        }

        return maxSum;
    }

    /*
    ----------------------------------------------------
    Main Method
    ----------------------------------------------------
    */
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6};

        printAllSubarrays(arr);

        int betterAns = betterApproach(arr);

        System.out.println("Maximum Sum (Better Approach): "
                           + betterAns);

        int optimalAns = optimalApproach(arr);

        System.out.println("Maximum Sum (Kadane's Algorithm): "
                           + optimalAns);
    }
}