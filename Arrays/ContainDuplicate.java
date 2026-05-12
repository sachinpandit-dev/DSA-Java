// Problem: Contains Duplicate
// LeetCode: 217
// Topic: Arrays, Hashing

import java.util.HashSet;

public class ContainsDuplicate {

    // Brute Force Approach
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static boolean bruteForceDuplicate(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    // Optimal Approach using HashSet
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static boolean optimalContainsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1};

        System.out.println("Brute Force Result: " + bruteForceDuplicate(nums));

        System.out.println("Optimal Result: " + optimalContainsDuplicate(nums));
    }
}