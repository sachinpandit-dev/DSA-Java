import java.util.*;

/**
 * BRUTE FORCE APPROACH
 * Time Complexity: O(n² log n)
 * Space Complexity: O(n)
 * 
 * Approach:
 * 1. Convert array to ArrayList
 * 2. Sort the list each iteration
 * 3. Pick two heaviest (last two elements)
 * 4. Remove them and add difference if not equal
 * 5. Repeat until 0 or 1 stone left
 */
class BruteForceSolution {
    
    // Method 1: Using ArrayList and Sorting
    public int lastStoneWeight(int[] stones) {
        // Convert to list for easy removal
        List<Integer> list = new ArrayList<>();
        for (int stone : stones) {
            list.add(stone);
        }
        
        while (list.size() > 1) {
            // Sort ascending
            Collections.sort(list);
            
            // Get two heaviest stones
            int first = list.remove(list.size() - 1);   // Heaviest
            int second = list.remove(list.size() - 1);  // Second heaviest
            
            // If not equal, add difference back
            if (first != second) {
                list.add(first - second);
            }
        }
        
        return list.isEmpty() ? 0 : list.get(0);
    }
}

/**
 * OPTIMAL APPROACH - MAX HEAP
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 * 
 * Why Max Heap?
 * - Always need the two largest elements
 * - Heap gives us O(1) access to max
 * - Insert/Delete are O(log n)
 */
class OptimalSolution {
    
    public int lastStoneWeight(int[] stones) {
        // Create Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        
        // Process stones
        while (maxHeap.size() > 1) {
            // Get two heaviest stones
            int first = maxHeap.poll();   // Heaviest
            int second = maxHeap.poll();  // Second heaviest
            
            // If not equal, add difference back
            if (first != second) {
                maxHeap.add(first - second);
            }
            // If equal, both destroyed (nothing to add)
        }
        
        // Return remaining stone or 0
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}

public class Main {
    public static void main(String[] args) {
        // Test both solutions
        int[] stones = {2, 7, 4, 1, 8, 1};
        
        BruteForceSolution brute = new BruteForceSolution();
        System.out.println("Brute Force Result: " + brute.lastStoneWeight(stones));
        
        OptimalSolution optimal = new OptimalSolution();
        System.out.println("Optimal Result: " + optimal.lastStoneWeight(stones));
    }
}