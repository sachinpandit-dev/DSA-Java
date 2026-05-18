/*
========================================================
Problem : Plus One
Platform: LeetCode
Difficulty: Easy

Given a non-empty array of digits representing
a non-negative integer, increment the integer by one.

The digits are stored such that the most significant
digit is at the beginning of the array.

========================================================
Approach : Optimal Approach

Idea:
- Traverse from last digit
- If digit is 9:
    make it 0 and carry forward
- Otherwise:
    increment digit and return answer

Special Case:
If all digits are 9
Example:
[9,9,9] → [1,0,0,0]

Time Complexity : O(N)
Space Complexity: O(1)
(Except new array in all-9 case)

========================================================
*/

import java.util.Arrays;

public class PlusOne {

    /*
    ----------------------------------------------------
    Optimal Approach
    ----------------------------------------------------
    */
    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            // If digit is 9
            if (digits[i] == 9) {

                digits[i] = 0;
            }

            // Increment and return
            else {

                digits[i]++;

                return digits;
            }
        }

        /*
        ------------------------------------------------
        If all digits are 9
        Example:
        [9,9,9] → [1,0,0,0]
        ------------------------------------------------
        */
        digits = new int[digits.length + 1];

        digits[0] = 1;

        return digits;
    }

    /*
    ----------------------------------------------------
    Main Method
    ----------------------------------------------------
    */
    public static void main(String[] args) {

        int[] digits = {9, 9, 9};

        int[] result = plusOne(digits);

        System.out.println(Arrays.toString(result));
    }
}