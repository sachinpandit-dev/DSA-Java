import java.util.Arrays;
import java.util.HashMap;

/*
========================================================
Problem : Two Sum
Platform: LeetCode
Difficulty: Easy

Goal:
Find indices of two numbers such that:
nums[i] + nums[j] = target

========================================================
Approach 1 : Brute Force

Idea:
- Check every possible pair
- If pair sum equals target, print indices

Time Complexity : O(N^2)
Space Complexity: O(1)

========================================================
Approach 2 : Better Approach (Sorting + Two Pointer)

Idea:
- Sort the array using built-in sort
- Use two pointers:
    start -> beginning
    end   -> last

- If sum is smaller:
    move start forward

- If sum is larger:
    move end backward

Time Complexity : O(N log N)
Space Complexity: O(1)

NOTE:
This approach works well for checking pair existence,
but original indices are lost after sorting.

========================================================
Approach 3 : Optimal Approach (HashMap)

Idea:
- Store visited numbers in HashMap
- Check whether remaining value already exists

Time Complexity : O(N)
Space Complexity: O(N)

========================================================
*/

public class TwoSum {

    /*
    ----------------------------------------------------
    Approach 1 : Brute Force
    ----------------------------------------------------
    */
    public static void bruteForce(int[] nums, int target) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                int sum = nums[i] + nums[j];

                if (sum == target) {

                    System.out.println(
                        "Pair Found at Indices : [" + i + ", " + j + "]"
                    );

                    count++;
                }
            }
        }

        System.out.println("Total Pairs : " + count);
    }

    /*
    ----------------------------------------------------
    Approach 2 : Better Approach
    Sorting + Two Pointer
    ----------------------------------------------------
    */
    public static void betterApproach(int[] nums, int target) {

        // Built-in sorting function
        Arrays.sort(nums);

        System.out.println("Sorted Array : " + Arrays.toString(nums));

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {

            int sum = nums[start] + nums[end];

            if (sum == target) {

                System.out.println(
                    "Pair Found : " + nums[start] + " + " + nums[end]
                );

                start++;
                end--;
            }
            else if (sum < target) {

                start++;
            }
            else {

                end--;
            }
        }
    }

    /*
    ----------------------------------------------------
    Approach 3 : Optimal Approach
    HashMap
    ----------------------------------------------------
    */
    public static void optimalApproach(int[] nums, int target) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];

            int remaining = target - current;

            if (hm.containsKey(remaining)) {

                System.out.println(
                    "Pair Found at Indices : ["
                    + hm.get(remaining)
                    + ", "
                    + i
                    + "]"
                );

                return;
            }

            hm.put(current, i);
        }

        System.out.println("No Pair Found");
    }

    /*
    ----------------------------------------------------
    Main Method
    ----------------------------------------------------
    */
    public static void main(String[] args) {

        int[] nums = {2, 3, 8, 4, 1};
        int target = 5;

        System.out.println("========== Brute Force ==========");
        bruteForce(nums, target);

        System.out.println();

        System.out.println("========== Better Approach ==========");
        betterApproach(nums.clone(), target);

        System.out.println();

        System.out.println("========== Optimal Approach ==========");
        optimalApproach(nums, target);
    }
}