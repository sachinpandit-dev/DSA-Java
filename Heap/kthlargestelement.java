// Online Java Compiler
import java.util.*;
// Use this editor to write, compile and run your Java code online
public class Main {

  public static int klargestelement(ArrayList<Integer> arr, int k){
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for(int i=0;i<arr.size();i++){
      minHeap.add(arr.get(i));
      if(minHeap.size()> k){
        minHeap.poll();
      }
    }

    return minHeap.peek();
  
  }
  public static void main(String[] args) {
      System.out.println("Hello, World!");
      ArrayList<Integer> arr = new ArrayList<>();
      arr.add(3);
      arr.add(2);
      arr.add(1);
      arr.add(5);
      arr.add(6);
      arr.add(4);

      int ans = klargestelement(arr, 2);

      System.out.println("Kth largest elemenet is: "+ ans);
      
      

      
  }
}