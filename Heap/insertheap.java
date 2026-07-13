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
  public static void insert(int value){
    heap.add(value);

    int child = heap.size()-1;

    while(child > 0){
      int parent = (child -1 )/ 2;

      if(heap.get(child) > heap.get(parent)){
        swap(child, parent, heap);
        child = parent;
      }else{
        break;
      }
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

  public static int remove(ArrayList<Integer> heap){
    int removeElement = heap.get(0);

    swap(0,heap.size()-1,heap);
    heap.remove(heap.size()-1);
    hepifyDown(0);
    return removeElement;
  }
  public static void main(String[] args) {
      System.out.println("Hello, World!");
      insert(20);
      insert(30);
      insert(40);
      insert(50);
      insert(60);
      System.out.println(heap);
      System.out.println(remove(heap));
      System.out.println(heap);
  }
}
