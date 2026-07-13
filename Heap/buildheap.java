// Online Java Compiler
import java.util.*;
// Use this editor to write, compile and run your Java code online
public class Main {
  static ArrayList<Integer> heap = new ArrayList<>();

  public static void swap(int child, int parent, ArrayList<Integer> heap){
    int temp = heap.get(child);
    heap.set(child, heap.get(parent));
    heap.set(parent, temp);
  }
  
  public static void builHeap(ArrayList<Integer> heap){
    int lastparent = (heap.size()/2)-1;
    for(int i=lastparent;i>=0;i--){
      hepifyDown(i);
    }
  }

  public static void hepifyDown(int parent){
    while(true){
      

      int left = 2 * parent + 1;
      int right = 2 * parent + 2;

      int largest = parent;

      if(left < heap.size() && heap.get(left) > heap.get(largest)){
        largest = left;
      }

      if(right < heap.size() && heap.get(right) > heap.get(largest)){
        largest = right;
      }

      if(largest != parent){
        swap(largest, parent, heap);
        parent = largest;
      }else{
        break;
      }
    }
  }

  public static void main(String[] args) {
      System.out.println("Hello, World!");
      heap.add(10);
      heap.add(40);
      heap.add(30);
      heap.add(5);
      heap.add(12);
      heap.add(6);
      heap.add(2);
      builHeap(heap);
      System.out.println(heap);
      
  }
}
