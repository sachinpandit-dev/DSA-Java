/**
 * Problem: Maximum Sum Subarray of Size K
 * 
 * Problem Statement:
 * Given an array of integers arr[] and a positive integer k, 
 * find the maximum sum of any contiguous subarray of size k.
 * 
 * Example 1:
 * Input: arr = [2, 1, 5, 1, 3, 2], k = 3
 * Output: 9
 * Explanation: Subarray [5, 1, 3] has maximum sum = 9
 * 
 * Example 2:
 * Input: arr = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
 * Output: 39
 * Explanation: Subarray [4, 2, 10, 23] has maximum sum = 39
 * 
 * Example 3:
 * Input: arr = [100, 200, 300, 400], k = 2
 * Output: 700
 * Explanation: Subarray [300, 400] has maximum sum = 700
 * 
 * Constraints:
 * - 1 <= arr.length <= 10^5
 * - -10^4 <= arr[i] <= 10^4
 * - 1 <= k <= arr.length
 * 
 * Time Complexity: 
 * - Brute Force: O(n * k)
 * - Sliding Window (Optimal): O(n)
 * 
 * Space Complexity: O(1) for both approaches
 */

public class MaximumSumSubarrayOfSizeK {

    /**
     * APPROACH 1: BRUTE FORCE
     * 
     * Intuition:
     * Calculate sum of every possible subarray of length k and track the maximum.
     * For each starting index i (0 to n-k), we sum elements from i to i+k-1.
     * 
     * Time Complexity: O(n * k) - For each of (n-k+1) windows, we sum k elements
     * Space Complexity: O(1) - No extra space used
     * 
     * @param arr Input array
     * @param k Window size
     * @return Maximum sum of subarray of size k
     */
    public static int maxSubarraySumBruteForce(int[] arr, int k) {
        // Edge case: invalid input
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return 0;
        }
        
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        
        // Iterate over all possible starting positions
        for (int i = 0; i <= n - k; i++) {
            int currentSum = 0;
            
            // Calculate sum of current window
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j];
            }
            
            // Update maximum sum
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    /**
     * APPROACH 2: SLIDING WINDOW (OPTIMAL)
     * 
     * Intuition:
     * Instead of recalculating sum for each window from scratch, we slide the window
     * by subtracting the element leaving the window and adding the new element.
     * 
     * Steps:
     * 1. Calculate sum of first window (indices 0 to k-1)
     * 2. Store it as maxSum
     * 3. For i from k to n-1:
     *    - Remove arr[i-k] (element leaving window)
     *    - Add arr[i] (new element entering window)
     *    - Update windowSum = windowSum - arr[i-k] + arr[i]
     *    - Update maxSum = max(maxSum, windowSum)
     * 
     * Visualization:
     * Window 1: [a, b, c, d, e] → sum = a+b+c
     * Window 2: [a, b, c, d, e] → sum = b+c+d (remove a, add d)
     * Window 3: [a, b, c, d, e] → sum = c+d+e (remove b, add e)
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(1) - No extra space used
     * 
     * @param arr Input array
     * @param k Window size
     * @return Maximum sum of subarray of size k
     */
    public static int maxSubarraySumSlidingWindow(int[] arr, int k) {
        // Edge case: invalid input
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return 0;
        }
        
        int n = arr.length;
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window from index k to n-1
        for (int i = k; i < n; i++) {
            // Remove leftmost element of previous window
            // Add rightmost element of new window
            windowSum = windowSum - arr[i - k] + arr[i];
            
            // Update maximum sum
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Normal case
        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("k = " + k1);
        System.out.println("Brute Force Result: " + maxSubarraySumBruteForce(arr1, k1));
        System.out.println("Sliding Window Result: " + maxSubarraySumSlidingWindow(arr1, k1));
        System.out.println("Expected Output: 9\n");
        
        // Test Case 2: All positive numbers
        int[] arr2 = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k2 = 4;
        System.out.println("Test Case 2:");
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("k = " + k2);
        System.out.println("Brute Force Result: " + maxSubarraySumBruteForce(arr2, k2));
        System.out.println("Sliding Window Result: " + maxSubarraySumSlidingWindow(arr2, k2));
        System.out.println("Expected Output: 39\n");
        
        // Test Case 3: k = 1 (single element)
        int[] arr3 = {100, 200, 300, 400};
        int k3 = 1;
        System.out.println("Test Case 3:");
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("k = " + k3);
        System.out.println("Brute Force Result: " + maxSubarraySumBruteForce(arr3, k3));
        System.out.println("Sliding Window Result: " + maxSubarraySumSlidingWindow(arr3, k3));
        System.out.println("Expected Output: 400\n");
        
        // Test Case 4: k = n (entire array)
        int[] arr4 = {5, 2, 9, 1, 7};
        int k4 = 5;
        System.out.println("Test Case 4:");
        System.out.println("Array: " + java.util.Arrays.toString(arr4));
        System.out.println("k = " + k4);
        System.out.println("Brute Force Result: " + maxSubarraySumBruteForce(arr4, k4));
        System.out.println("Sliding Window Result: " + maxSubarraySumSlidingWindow(arr4, k4));
        System.out.println("Expected Output: 24\n");
        
        // Test Case 5: Array with negative numbers
        int[] arr5 = {-2, -1, -5, -1, -3, -2};
        int k5 = 3;
        System.out.println("Test Case 5:");
        System.out.println("Array: " + java.util.Arrays.toString(arr5));
        System.out.println("k = " + k5);
        System.out.println("Brute Force Result: " + maxSubarraySumBruteForce(arr5, k5));
        System.out.println("Sliding Window Result: " + maxSubarraySumSlidingWindow(arr5, k5));
        System.out.println("Expected Output: -4");
    }
}