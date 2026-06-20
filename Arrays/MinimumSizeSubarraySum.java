/**
 * Problem: Minimum Size Subarray Sum (Sum >= Target)
 * 
 * Problem Statement:
 * Given an array of positive integers arr[] and a positive integer target,
 * find the minimal length of a contiguous subarray whose sum is greater than
 * or equal to target. If there is no such subarray, return 0.
 * 
 * Example 1:
 * Input: arr = [2, 3, 1, 2, 4, 3], target = 7
 * Output: 2
 * Explanation: Subarray [4, 3] has sum = 7 and length = 2 (minimum)
 * 
 * Example 2:
 * Input: arr = [1, 4, 4], target = 4
 * Output: 1
 * Explanation: Subarray [4] has sum = 4 and length = 1
 * 
 * Example 3:
 * Input: arr = [1, 1, 1, 1, 1, 1, 1, 1], target = 11
 * Output: 0
 * Explanation: No subarray has sum >= 11
 * 
 * Example 4:
 * Input: arr = [1, 2, 3, 4, 5], target = 11
 * Output: 3
 * Explanation: Subarray [2, 3, 4, 5] has sum = 14 (length 4) OR [3,4,5] has sum = 12 (length 3) ← minimum
 * 
 * Constraints:
 * - 1 <= arr.length <= 10^5
 * - 1 <= arr[i] <= 10^4
 * - 1 <= target <= 10^9
 * 
 * Note: All elements are POSITIVE integers (crucial for sliding window approach)
 * 
 * Time Complexity: 
 * - Brute Force: O(n^2)
 * - Sliding Window (Optimal): O(n)
 * 
 * Space Complexity: O(1) for both approaches
 */

public class MinimumSizeSubarraySum {

    /**
     * APPROACH 1: BRUTE FORCE
     * 
     * Intuition:
     * Check all possible subarrays and find the minimum length with sum >= target.
     * For each starting index i, expand j until sum >= target, then update minLength.
     * 
     * Algorithm:
     * 1. Initialize minLength = Integer.MAX_VALUE
     * 2. For i from 0 to n-1:
     *    - Initialize currentSum = 0
     *    - For j from i to n-1:
     *      - Add arr[j] to currentSum
     *      - If currentSum >= target:
     *        - Update minLength = min(minLength, j-i+1)
     *        - Break (no need to check longer subarrays starting at i)
     * 3. Return minLength (or 0 if not found)
     * 
     * Time Complexity: O(n^2) - Two nested loops
     * Space Complexity: O(1) - No extra space
     * 
     * @param arr Input array of positive integers
     * @param target Target sum
     * @return Minimum length of subarray with sum >= target
     */
    public static int minSubArrayLenBruteForce(int[] arr, int target) {
        // Edge case: invalid input
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        int minLength = Integer.MAX_VALUE;
        
        // Try every possible starting index
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Expand window from i
            for (int j = i; j < n; j++) {
                currentSum += arr[j];
                
                // If sum >= target, update minLength and break
                if (currentSum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // No need to check longer subarrays
                }
            }
        }
        
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    /**
     * APPROACH 2: SLIDING WINDOW (OPTIMAL)
     * 
     * Intuition:
     * Use two pointers (left and right) to maintain a dynamic window.
     * Expand window by moving right pointer, and shrink from left when sum >= target.
     * Keep track of minimum window length.
     * 
     * Key Insight: Since all numbers are positive, when sum >= target,
     * we can safely shrink the window from left to find smaller valid windows.
     * 
     * Algorithm:
     * 1. Initialize left = 0, currentSum = 0, minLength = Integer.MAX_VALUE
     * 2. For right from 0 to n-1:
     *    - Add arr[right] to currentSum (expand window)
     *    - While currentSum >= target:
     *      - Update minLength = min(minLength, right - left + 1)
     *      - Remove arr[left] from currentSum (shrink window)
     *      - left++ (move left pointer)
     * 3. Return minLength (or 0 if not found)
     * 
     * IMPORTANT: Use WHILE loop (not if) to shrink as much as possible
     * 
     * Visualization:
     * arr = [2, 3, 1, 2, 4, 3], target = 7
     * 
     * Step 1: right=0, sum=2 (<7) → expand
     * Step 2: right=1, sum=5 (<7) → expand
     * Step 3: right=2, sum=6 (<7) → expand
     * Step 4: right=3, sum=8 (>=7) → shrink!
     *         Window [2,3,1,2] len=4 → min=4
     *         Remove 2, sum=6, left=1
     * Step 5: right=4, sum=10 (>=7) → shrink!
     *         Window [3,1,2,4] len=4 → min=4
     *         Remove 3, sum=7, left=2
     *         Window [1,2,4] len=3 → min=3
     *         Remove 1, sum=6, left=3
     * Step 6: right=5, sum=9 (>=7) → shrink!
     *         Window [2,4,3] len=3 → min=3
     *         Remove 2, sum=7, left=4
     *         Window [4,3] len=2 → min=2 ← Answer
     *         Remove 4, sum=3, left=5
     * 
     * Time Complexity: O(n) - Each element processed at most twice
     * Space Complexity: O(1) - No extra space
     * 
     * @param arr Input array of positive integers
     * @param target Target sum
     * @return Minimum length of subarray with sum >= target
     */
    public static int minSubArrayLenSlidingWindow(int[] arr, int target) {
        // Edge case: invalid input
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        
        // Expand window by moving right pointer
        for (int right = 0; right < arr.length; right++) {
            // Add current element to window
            currentSum += arr[right];
            
            // Shrink window from left while sum >= target
            while (currentSum >= target) {
                // Update minimum length
                minLength = Math.min(minLength, right - left + 1);
                
                // Remove leftmost element and move left pointer
                currentSum -= arr[left];
                left++;
            }
        }
        
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        System.out.println("=== MINIMUM SIZE SUBARRAY SUM ===\n");
        
        // Test Case 1: Normal case
        int[] arr1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Test Case 1:");
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Target: " + target1);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr1, target1));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr1, target1));
        System.out.println("Expected Output: 2 (Subarray [4, 3])\n");
        
        // Test Case 2: Target equals single element
        int[] arr2 = {1, 4, 4};
        int target2 = 4;
        System.out.println("Test Case 2:");
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Target: " + target2);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr2, target2));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr2, target2));
        System.out.println("Expected Output: 1 (Subarray [4])\n");
        
        // Test Case 3: No subarray meets target
        int[] arr3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println("Test Case 3:");
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Target: " + target3);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr3, target3));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr3, target3));
        System.out.println("Expected Output: 0 (No subarray found)\n");
        
        // Test Case 4: Large array
        int[] arr4 = {1, 2, 3, 4, 5};
        int target4 = 11;
        System.out.println("Test Case 4:");
        System.out.println("Array: " + java.util.Arrays.toString(arr4));
        System.out.println("Target: " + target4);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr4, target4));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr4, target4));
        System.out.println("Expected Output: 3 (Subarray [3, 4, 5])\n");
        
        // Test Case 5: Target exactly equals entire array sum
        int[] arr5 = {1, 2, 3, 4};
        int target5 = 10;
        System.out.println("Test Case 5:");
        System.out.println("Array: " + java.util.Arrays.toString(arr5));
        System.out.println("Target: " + target5);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr5, target5));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr5, target5));
        System.out.println("Expected Output: 4 (Entire array)\n");
        
        // Test Case 6: Large target
        int[] arr6 = {5, 10, 15, 20};
        int target6 = 50;
        System.out.println("Test Case 6:");
        System.out.println("Array: " + java.util.Arrays.toString(arr6));
        System.out.println("Target: " + target6);
        System.out.println("Brute Force Result: " + minSubArrayLenBruteForce(arr6, target6));
        System.out.println("Sliding Window Result: " + minSubArrayLenSlidingWindow(arr6, target6));
        System.out.println("Expected Output: 0 (No subarray found)\n");
        
        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        int[] largeArr = new int[10000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = 1;
        }
        int largeTarget = 5000;
        
        long startTime = System.nanoTime();
        int bfResult = minSubArrayLenBruteForce(largeArr, largeTarget);
        long bfTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int swResult = minSubArrayLenSlidingWindow(largeArr, largeTarget);
        long swTime = System.nanoTime() - startTime;
        
        System.out.println("Array size: 10000, Target: 5000");
        System.out.println("Brute Force Time: " + bfTime / 1000000 + " ms");
        System.out.println("Sliding Window Time: " + swTime / 1000000 + " ms");
        System.out.println("Brute Force Result: " + bfResult);
        System.out.println("Sliding Window Result: " + swResult);
    }
}