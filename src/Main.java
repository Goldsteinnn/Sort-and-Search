
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
  public static void main(String args[]) throws NumberFormatException, IOException {
    long start = System.currentTimeMillis();
    Random rand = new Random();
    int[] arr = new int[20];
    //Store random numbers into an array
    for(int i = 0; i < arr.length; i++) {
      arr[i] = rand.nextInt(10);
    }
   displayArr(arr); //Before
   sort(arr);
   displayArr(arr); // After
   
   int num = 0;
   while(search(arr, num, 0, arr.length-1)) {
     num++;
   }
   System.out.println("");
   System.out.println("Lowest number not in list:  "+ num);
  
  long end = System.currentTimeMillis();
  System.out.println("Elapsed Time: " + (end - start));
  }
  
  private static int partition(int[] arr, int lo, int hi) {
    //Pointer
    int i = lo;  
    int j = hi+1;
    while(true) {
      while(less(arr[++i], arr[lo])) {
        if(i == hi) { //find left item out of place
          break;
        }
      }
      while(less(arr[lo], arr[--j])) {
        if(j == lo) { // find right item out of place
          break;
        }
      }
      if(i>=j) {
        break;
      }
      exch(arr,i,j); // swap out of place items
    }
    exch(arr,lo,j);  // swap the partitioned item with the midpoint
    return j;
  }
  //First shuffle then sort for maximum efficiency
  private static void sort(int arr[]) {
    Random rand = new Random();
    //Shuffles the array
    for(int i = 0; i < arr.length; i++) {
      int randIndx = rand.nextInt(arr.length);
      int temp = arr[randIndx];
      arr[randIndx] = arr[i];
      arr[i] = temp;
    }
    sort(arr, 0, arr.length-1);
  }
  private static void sort(int arr[], int lo, int hi) {
    if(hi <= lo) {  
      return;
    }
    int j = partition(arr,lo,hi);  // separate the left and right
    sort(arr,lo, j-1); //sort the left
    sort(arr,j+1,hi); // sort the right
  }
  
  private static boolean search(int arr[], int key, int lo, int hi) {
    
    if(hi >= lo) {
      int mid = lo + (hi-lo)/2;
      
      if(arr[mid] == key) {
        //System.out.println("Element is at index " + mid);
        return true;
      }
      //If the key is in left half
      if(arr[mid] > key) {
        return search(arr,key,lo,mid-1);
      }
      //Only accessible if key is in right half
      return  search(arr,key,mid+1,hi);
      
    }
    //System.out.println("Element is not found");
    return false;  //if not found
  }
  
  private static void displayArr(int[] arr) {
    System.out.println("");
    for(int i = 0 ; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
  
  //Helper functions
  //Swap element at one index with another
  private static void exch(int[] arr, int firstIndx, int secIndx) {
    int temp = arr[firstIndx];
    arr[firstIndx] = arr[secIndx];
    arr[secIndx] = temp;
  }
  
  //Compares two elements and return a boolean value
  private static boolean less(int firstIndx, int secIndx) {
    //System.out.println(firstIndx + " " + secIndx + (firstIndx<secIndx));
    return firstIndx < secIndx;
  }
}
