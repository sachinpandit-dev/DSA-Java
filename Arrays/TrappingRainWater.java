/**
 * Problem: Trapping Rain Water (LeetCode 42)
 * 
 * Problem Statement:
 * Given n non-negative integers representing an elevation map where the width 
 * of each bar is 1, compute how much water it can trap after raining.
 * 
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The elevation map is represented by the array.
 * The trapped water = 6 units.
 * 
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * 
 * Visual Representation:
 * 
 * Example: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 
 *      █
 *      █
 *      █
 *  █   █
 *  █   █  █
 *  █ █ █  █  █
 * █████████████
 * 0 1 0 2 1 0 1 3 2 1 2 1
 * 
 * Water trapped = 6 units
 * 
 * Constraints:
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 * 
 * Time Complexity: 
 * - Brute Force: O(n²)
 * - Two Arrays (Optimal): O(n)
 * 
 * Space Complexity:
 * - Brute Force: O(1)
 * - Two Arrays (Optimal): O(n)
 */

public class TrappingRainWater {

    /**
     * APPROACH 1: BRUTE FORCE
     * 
     * Intuition:
     * For each bar, the water it can trap depends on the maximum height 
     * on its left and right sides.
     * 
     * Water at position i = min(maxLeft, maxRight) - height[i]
     * 
     * Where:
     * - maxLeft = maximum height from 0 to i-1
     * - maxRight = maximum height from i+1 to n-1
     * 
     * If the result is negative, it means no water can be trapped at that position.
     * 
     * Algorithm:
     * 1. Initialize totalWater = 0
     * 2. For each position i from 0 to n-1:
     *    - Find maximum height on left side (0 to i-1)
     *    - Find maximum height on right side (i+1 to n-1)
     *    - Calculate water = min(leftMax, rightMax) - height[i]
     *    - If water > 0, add to totalWater
     * 3. Return totalWater
     * 
     * Visualization:
     * height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     * 
     * At position i = 5 (height = 0):
     *   leftMax = max(0,1,0,2,1) = 2
     *   rightMax = max(1,3,2,1,2,1) = 3
     *   water = min(2,3) - 0 = 2 units
     * 
     * Time Complexity: O(n²) - For each element, we scan left and right
     * Space Complexity: O(1) - No extra space used
     * 
     * @param height Array representing elevation map
     * @return Total water that can be trapped
     */
    public static int trapBruteForce(int[] height) {
        int n = height.length;
        
        // Edge case: empty array
        if (n == 0) {
            return 0;
        }
        
        int totalWater = 0;
        
        // For each position
        for (int i = 0; i < n; i++) {
            // Find maximum height on left side
            int leftMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            
            // Find maximum height on right side
            int rightMax = 0;
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            
            // Water trapped at this position
            int waterAtPosition = Math.min(leftMax, rightMax) - height[i];
            
            // Add to total if positive
            if (waterAtPosition > 0) {
                totalWater += waterAtPosition;
            }
        }
        
        return totalWater;
    }

    /**
     * APPROACH 2: TWO ARRAYS (OPTIMAL)
     * 
     * Intuition:
     * Instead of calculating leftMax and rightMax for each position repeatedly,
     * we pre-compute them using two arrays.
     * 
     * Key Insight:
     * - leftMax[i] = maximum height from index 0 to i
     * - rightMax[i] = maximum height from index i to n-1
     * - Water at position i = min(leftMax[i], rightMax[i]) - height[i]
     * 
     * Algorithm:
     * 1. Create leftMax array of size n
     * 2. Create rightMax array of size n
     * 3. Fill leftMax:
     *    - leftMax[0] = height[0]
     *    - For i from 1 to n-1:
     *      leftMax[i] = max(leftMax[i-1], height[i])
     * 4. Fill rightMax:
     *    - rightMax[n-1] = height[n-1]
     *    - For i from n-2 down to 0:
     *      rightMax[i] = max(rightMax[i+1], height[i])
     * 5. For each position i:
     *    - water = min(leftMax[i], rightMax[i]) - height[i]
     *    - Add to total if positive
     * 6. Return totalWater
     * 
     * Visualization:
     * height   = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     * leftMax  = [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
     * rightMax = [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
     * min      = [0, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 1]
     * water    = [0, 0, 1, 0, 1, 2, 1, 0, 0, 1, 0, 0] = 6
     * 
     * Time Complexity: O(n) - Three passes through array
     * Space Complexity: O(n) - Two extra arrays
     * 
     * @param height Array representing elevation map
     * @return Total water that can be trapped
     */
    public static int trapTwoArrays(int[] height) {
        int n = height.length;
        
        // Edge case: empty array
        if (n == 0) {
            return 0;
        }
        
        // Arrays to store maximum heights from left and right
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        // Calculate total water trapped
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int waterAtPosition = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (waterAtPosition > 0) {
                totalWater += waterAtPosition;
            }
        }
        
        return totalWater;
    }

    /**
     * APPROACH 3: TWO POINTERS (MOST OPTIMAL)
     * 
     * Intuition:
     * Instead of using two arrays, we can use two pointers.
     * We maintain leftMax and rightMax as we move pointers inward.
     * 
     * Algorithm:
     * 1. Initialize left = 0, right = n-1
     * 2. Initialize leftMax = height[0], rightMax = height[n-1]
     * 3. While left < right:
     *    - If leftMax <= rightMax:
     *      - Move left pointer forward
     *      - Update leftMax = max(leftMax, height[left])
     *      - Water at left = leftMax - height[left]
     *      - Add to total
     *    - Else:
     *      - Move right pointer backward
     *      - Update rightMax = max(rightMax, height[right])
     *      - Water at right = rightMax - height[right]
     *      - Add to total
     * 4. Return totalWater
     * 
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - No extra space
     * 
     * @param height Array representing elevation map
     * @return Total water that can be trapped
     */
    public static int trapTwoPointers(int[] height) {
        int n = height.length;
        
        // Edge case: empty array
        if (n == 0) {
            return 0;
        }
        
        int left = 0;
        int right = n - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int totalWater = 0;
        
        while (left < right) {
            if (leftMax <= rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                totalWater += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                totalWater += rightMax - height[right];
            }
        }
        
        return totalWater;
    }

    /**
     * Main method to test all approaches
     */
    public static void main(String[] args) {
        System.out.println("=== TRAPPING RAIN WATER ===\n");
        
        // Test Case 1
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test Case 1:");
        System.out.print("Height: ");
        printArray(height1);
        System.out.println("Brute Force Result: " + trapBruteForce(height1));
        System.out.println("Two Arrays Result: " + trapTwoArrays(height1));
        System.out.println("Two Pointers Result: " + trapTwoPointers(height1));
        System.out.println("Expected Output: 6\n");
        
        // Test Case 2
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Test Case 2:");
        System.out.print("Height: ");
        printArray(height2);
        System.out.println("Brute Force Result: " + trapBruteForce(height2));
        System.out.println("Two Arrays Result: " + trapTwoArrays(height2));
        System.out.println("Two Pointers Result: " + trapTwoPointers(height2));
        System.out.println("Expected Output: 9\n");
        
        // Test Case 3: No water
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3 (No water):");
        System.out.print("Height: ");
        printArray(height3);
        System.out.println("Brute Force Result: " + trapBruteForce(height3));
        System.out.println("Two Arrays Result: " + trapTwoArrays(height3));
        System.out.println("Two Pointers Result: " + trapTwoPointers(height3));
        System.out.println("Expected Output: 0\n");
        
        // Test Case 4: All zeros
        int[] height4 = {0, 0, 0, 0};
        System.out.println("Test Case 4 (All zeros):");
        System.out.print("Height: ");
        printArray(height4);
        System.out.println("Brute Force Result: " + trapBruteForce(height4));
        System.out.println("Two Arrays Result: " + trapTwoArrays(height4));
        System.out.println("Two Pointers Result: " + trapTwoPointers(height4));
        System.out.println("Expected Output: 0\n");
        
        // Test Case 5: Single element
        int[] height5 = {5};
        System.out.println("Test Case 5 (Single element):");
        System.out.print("Height: ");
        printArray(height5);
        System.out.println("Brute Force Result: " + trapBruteForce(height5));
        System.out.println("Two Arrays Result: " + trapTwoArrays(height5));
        System.out.println("Two Pointers Result: " + trapTwoPointers(height5));
        System.out.println("Expected Output: 0\n");
        
        // Performance Comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int)(Math.random() * 100);
        }
        
        long startTime = System.nanoTime();
        int bfResult = trapBruteForce(largeArray);
        long bfTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int taResult = trapTwoArrays(largeArray);
        long taTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int tpResult = trapTwoPointers(largeArray);
        long tpTime = System.nanoTime() - startTime;
        
        System.out.println("Array size: 10000");
        System.out.println("Brute Force Time: " + bfTime / 1000000 + " ms, Result: " + bfResult);
        System.out.println("Two Arrays Time: " + taTime / 1000000 + " ms, Result: " + taResult);
        System.out.println("Two Pointers Time: " + tpTime / 1000000 + " ms, Result: " + tpResult);
    }
    
    // Helper method to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}