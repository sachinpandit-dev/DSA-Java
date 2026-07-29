// Online Java Compiler
// Use this editor to write, compile and run your Java code online
public class Main {
  public static void buildHeap(int[] arr){

    int lastparaent = (arr.length / 2) - 1;

    for(int i= lastparaent;i>=0;i--){
      hepifyDown(arr, arr.length,  i);
    }
  }

  public static void heapSort(int[] arr){
    int size = arr.length;

    buildHeap(arr);

    for(int j=size-1;j>=0;j--){
      // Swap root with last element
      int temp = arr[0];
      arr[0] = arr[j];
      arr[j] = temp;

      // Heapify remaining heap
      hepifyDown(arr, j, 0);
    }
  }

  public static void hepifyDown(int[] arr,int size, int parent){

    while(true){
      int left = 2 * parent + 1;
      int right = 2 * parent + 2;
      int largest = parent;
      
      if(left < size && arr[left] > arr[largest]){
        largest = left;
      }
      if(right < size && arr[right] > arr[largest]){
        largest = right;
      }

      if(largest != parent){
        swap(arr, parent, largest);
        parent = largest;
      }else{
        break;
      }


    }
  }

   public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
  public static void main(String[] args) {
      System.out.println("Hello, World!");
      int arr[] = {4, 10, 3, 5, 1};

      heapSort(arr);

      for (int x : arr)
        System.out.print(x + " ");
      }
  }
