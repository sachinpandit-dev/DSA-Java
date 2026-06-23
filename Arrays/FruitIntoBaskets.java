/**
 * Problem: Fruit Into Baskets (LeetCode 904)
 * 
 * Problem Statement:
 * You are visiting a farm with a row of fruit trees. Each tree produces 
 * exactly one type of fruit. You have TWO baskets, and each basket can 
 * hold ONLY ONE type of fruit (but unlimited quantity of that type).
 * 
 * Starting from any tree, you must pick exactly one fruit from each tree
 * while moving to the right. You must stop when you encounter a third 
 * type of fruit that doesn't fit in either basket.
 * 
 * Goal: Find the maximum number of fruits you can collect.
 * 
 * Simplified: Find the length of the longest contiguous subarray that 
 * contains at most 2 distinct numbers.
 * 
 * Example 1:
 * Input: fruits = [1, 2, 1]
 * Output: 3
 * Explanation: We can pick all 3 fruits [1, 2, 1]
 * - Basket 1: all type 1 fruits (two 1's)
 * - Basket 2: type 2 fruit (one 2)
 * 
 * Example 2:
 * Input: fruits = [0, 1, 2, 2]
 * Output: 3
 * Explanation: We can pick [1, 2, 2]
 * - Basket 1: type 1 (one fruit)
 * - Basket 2: type 2 (two fruits)
 * 
 * Example 3:
 * Input: fruits = [1, 2, 3, 2, 2]
 * Output: 4
 * Explanation: We can pick [2, 3, 2, 2]
 * - Basket 1: type 2 (three fruits)
 * - Basket 2: type 3 (one fruit)
 * 
 * Example 4:
 * Input: fruits = [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4]
 * Output: 5
 * Explanation: We can pick [1, 2, 1, 1, 2] length = 5
 * 
 * Constraints:
 * - 1 <= fruits.length <= 10^5
 * - 0 <= fruits[i] < fruits.length
 * 
 * Time Complexity: 
 * - Brute Force: O(n^2)
 * - Sliding Window (Optimal): O(n)
 * 
 * Space Complexity: O(1) for both approaches 
 * (map stores at most 3 entries due to shrinking)
 */

import java.util.*;

public class FruitIntoBaskets {

    /**
     * APPROACH 1: BRUTE FORCE
     * 
     * Intuition:
     * Check every possible subarray and count distinct elements in it.
     * If a subarray has at most 2 distinct elements, update the max length.
     * 
     * Algorithm:
     * 1. Initialize maxFruits = 0
     * 2. For each starting index i from 0 to n-1:
     *    - Create a HashSet to track distinct fruits
     *    - For each ending index j from i to n-1:
     *      - Add fruits[j] to set
     *      - If set size <= 2, update maxFruits = max(maxFruits, j-i+1)
     *      - If set size > 2, break (adding more will only increase distinct count)
     * 3. Return maxFruits
     * 
     * Time Complexity: O(n^2) - Two nested loops
     * Space Complexity: O(1) - HashSet stores at most 3 elements
     * 
     * @param fruits Array of fruit types
     * @return Maximum number of fruits that can be collected
     */
    public static int totalFruitBruteForce(int[] fruits) {
        // Edge case: empty array
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        
        int n = fruits.length;
        int maxFruits = 0;
        
        // Try every possible starting position
        for (int i = 0; i < n; i++) {
            Set<Integer> basket = new HashSet<>();
            
            // Try every possible ending position
            for (int j = i; j < n; j++) {
                basket.add(fruits[j]);
                
                // If we have at most 2 types, update max
                if (basket.size() <= 2) {
                    maxFruits = Math.max(maxFruits, j - i + 1);
                } else {
                    // More than 2 types, stop expanding this window
                    break;
                }
            }
        }
        
        return maxFruits;
    }

    /**
     * APPROACH 2: SLIDING WINDOW (OPTIMAL)
     * 
     * Intuition:
     * Use two pointers (left and right) to maintain a dynamic window.
     * The window always contains at most 2 distinct fruit types.
     * When we encounter a third type, we shrink the window from left
     * until we have only 2 types again.
     * 
     * Key Insight: Since we only have 2 baskets, we can keep adding fruits
     * until we see a third type, then we remove from left until we're back
     * to 2 types.
     * 
     * Algorithm:
     * 1. Initialize left = 0, maxFruits = 0, basket = HashMap
     * 2. For right from 0 to n-1:
     *    - Add fruits[right] to basket (increment count)
     *    - WHILE basket.size() > 2:
     *      - Remove fruits[left] from basket (decrement count or remove)
     *      - left++ (shrink window)
     *    - Update maxFruits = max(maxFruits, right - left + 1)
     * 3. Return maxFruits
     * 
     * Why WHILE not IF?
     * - We may need to remove multiple fruits to get back to 2 types
     * - Example: [1, 1, 2, 3] - when we add 3, we have {1,2,3}
     *   - Remove 1: still have {1,2,3} (need to remove again!)
     *   - Remove 1: now have {2,3} (valid!)
     * 
     * Time Complexity: O(n) - Each fruit added once, removed at most once
     * Space Complexity: O(1) - Map stores at most 3 entries
     * 
     * @param fruits Array of fruit types
     * @return Maximum number of fruits that can be collected
     */
    public static int totalFruitSlidingWindow(int[] fruits) {
        // Edge case: empty array
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        
        int left = 0;
        int maxFruits = 0;
        Map<Integer, Integer> basket = new HashMap<>();
        
        // Expand window by moving right pointer
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to basket
            int currentCount = basket.getOrDefault(fruits[right], 0);
            basket.put(fruits[right], currentCount + 1);
            
            // Shrink window from left until we have at most 2 types
            while (basket.size() > 2) {
                int fruitCount = basket.get(fruits[left]);
                
                if (fruitCount == 1) {
                    // If this is the last fruit of this type, remove it
                    basket.remove(fruits[left]);
                } else {
                    // Otherwise, just decrement the count
                    basket.put(fruits[left], fruitCount - 1);
                }
                left++; // Move left pointer forward
            }
            
            // Update maximum fruits found
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }

    /**
     * OPTIONAL: SIMPLIFIED SLIDING WINDOW (Using Array instead of Map)
     * 
     * For better performance when fruit types are within a range.
     * This version uses an array as a frequency counter.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the frequency array
     */
    public static int totalFruitSlidingWindowOptimized(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        
        int left = 0;
        int maxFruits = 0;
        int[] freq = new int[fruits.length]; // Since fruits[i] < fruits.length
        
        int distinctCount = 0; // Track number of distinct types in window
        
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit
            if (freq[fruits[right]] == 0) {
                distinctCount++; // New fruit type
            }
            freq[fruits[right]]++;
            
            // Shrink while we have more than 2 types
            while (distinctCount > 2) {
                freq[fruits[left]]--;
                if (freq[fruits[left]] == 0) {
                    distinctCount--; // Removed a fruit type completely
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }

    /**
     * Main method to test all approaches with various test cases
     */
    public static void main(String[] args) {
        System.out.println("=== FRUIT INTO BASKETS ===\n");
        
        // Test Case 1: Simple case
        int[] fruits1 = {1, 2, 1};
        System.out.println("Test Case 1:");
        System.out.println("Fruits: " + Arrays.toString(fruits1));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits1));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits1));
        System.out.println("Expected: 3\n");
        
        // Test Case 2: Two types only
        int[] fruits2 = {0, 1, 2, 2};
        System.out.println("Test Case 2:");
        System.out.println("Fruits: " + Arrays.toString(fruits2));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits2));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits2));
        System.out.println("Expected: 3\n");
        
        // Test Case 3: All same type
        int[] fruits3 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 3:");
        System.out.println("Fruits: " + Arrays.toString(fruits3));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits3));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits3));
        System.out.println("Expected: 5\n");
        
        // Test Case 4: Complex case
        int[] fruits4 = {1, 2, 3, 2, 2};
        System.out.println("Test Case 4:");
        System.out.println("Fruits: " + Arrays.toString(fruits4));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits4));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits4));
        System.out.println("Expected: 4\n");
        
        // Test Case 5: Alternating pattern
        int[] fruits5 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println("Test Case 5:");
        System.out.println("Fruits: " + Arrays.toString(fruits5));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits5));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits5));
        System.out.println("Expected: 5\n");
        
        // Test Case 6: Single element
        int[] fruits6 = {5};
        System.out.println("Test Case 6:");
        System.out.println("Fruits: " + Arrays.toString(fruits6));
        System.out.println("Brute Force: " + totalFruitBruteForce(fruits6));
        System.out.println("Sliding Window: " + totalFruitSlidingWindow(fruits6));
        System.out.println("Expected: 1\n");
        
        // Performance Comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 5; // 5 different fruit types
        }
        
        long startTime = System.nanoTime();
        int bfResult = totalFruitBruteForce(largeArray);
        long bfTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int swResult = totalFruitSlidingWindow(largeArray);
        long swTime = System.nanoTime() - startTime;
        
        System.out.println("Array size: 10000");
        System.out.println("Brute Force Time: " + bfTime / 1000000 + " ms");
        System.out.println("Sliding Window Time: " + swTime / 1000000 + " ms");
        System.out.println("Brute Force Result: " + bfResult);
        System.out.println("Sliding Window Result: " + swResult);
    }
}