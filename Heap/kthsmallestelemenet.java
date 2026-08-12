// Online Java Compiler
import java.util.*;

// Use this editor to write, compile and run your Java code online
public class Main {

  // create node with value, row and col
  static class Node{
    int value;
    int row;
    int col;

    Node(int value, int row, int col){
      this.value = value;
      this.row = row;
      this.col = col;
    }
  }

  public static int kthsmnallest(int[][] matrix, int k){

    // create min heap with node
    PriorityQueue<Node> minheap = new PriorityQueue<>(
      (a , b) -> a.value - b.value
    );

    //add firt element of each row in minheap
    for(int row=0;row< matrix.length;row++){
      minheap.add(new Node (matrix[row][0], row, 0));
    }

    //remove smallest k times
    for(int count=0; count < k; count++){

      // remove smallest node from minheap
      Node current = minheap.poll();

      //check if this is kth element
      if(count == k-1){
        return current.value;
      }

      // add next element from same row
      int nextcol = current.col + 1;

      if(nextcol < matrix[0].length){
        minheap.add(new Node (matrix[current.row][nextcol],current.row, nextcol));
      }
    }

    return -1;

  
  }
  public static void main(String[] args) {
      System.out.println("Hello, World!");
      int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

      int k = 5;

      System.out.println(kthsmnallest(matrix, k));
  }
}