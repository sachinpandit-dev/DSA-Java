// Online Java Compiler
import java.util.*;
// Use this editor to write, compile and run your Java code online
public class Main {
  public static int[] topKFrequent(int[] nums, int k){

    HashMap<Integer, Integer> map = new HashMap<>();

    for(int i=0;i<nums.length;i++){
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> map.get(a) - map.get(b)
        );

    for(int num : map.keySet()){
      minHeap.add(num);

      if(minHeap.size() > k){
        minHeap.poll();
      }
    }

    // Step 4: Prepare result array
    int[] result = new int[k];

    // Step 5: Extract elements
    for (int i = k - 1; i >= 0; i--) {
      result[i] = minHeap.poll();
    }

    return result;

    
  }
  public static void main(String[] args) {
      System.out.println("Hello, World!");
      int[] nums = {1,1,1,2,2,3};
      int k = 2;

      System.out.println(Arrays.toString(topKFrequent(nums, k)));

      
      
  }
}