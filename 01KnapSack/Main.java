import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] values = { 60, 100, 120 };
        int[] weights = { 10, 20, 30 };
        int capacity = 50;

        Solution sol = new Solution(values, weights, capacity);
        int ans = sol.knapSackBottomUp(capacity, values.length);
        System.out.println(ans);
    }
}

class Solution {

  private int[] values;
  private int[] weights;
  private int[][] table;

  public Solution(int[] values, int[] weights,int capacity){
    this.values = new int[values.length];
    this.weights = new int[weights.length];
    for(int i = 0; i<values.length; i++){
      this.values[i] = values[i];
      this.weights[i] = weights[i];
    }

    // init table for DP solution
    this.table = new int[values.length + 1][capacity + 1];
    for(int i = 0; i<values.length + 1; i++){
      Arrays.fill(this.table[i], -1);
    }
  }

  // Recursive Solution
  public int knapSackRecursive(int capacity, int n){
    // capacity denotes the remaining weight in the knapSack
    // n denotes the number of items left

    if (capacity == 0 || n == 0){   // base
      // capacity == 0 means knapSack is full, n == 0 means no items left
        return 0;
    }
    //  choosing the last item or n-1 th item from the list
    int currentVal = this.values[n-1];
    int currentWeight = this.weights[n-1];

    if(currentWeight > capacity){   // we will discard the current item
        return knapSackRecursive(capacity, n-1);  // moving on to the rest of the items
    }

    int remainingCapacity = capacity - currentWeight; // remaining capacity of knapscak if we choose the current item
    int val1 = currentVal + knapSackRecursive(remainingCapacity, n-1);  // if we choose the current item

    int val2 = knapSackRecursive(capacity, n-1);  // if we discard the current item
    return Math.max(val1, val2);
  }

  // Memoized / Top Down  solution
  public int knapSackMemo(int capacity, int n){
    if(capacity == 0 || n == 0){    //base
      table[n][capacity] = 0;
      return 0;
    }

    if(table[n][capacity] != -1)  return table[n][capacity];    //base

    int currentVal = this.values[n-1];
    int currentWeight = this.weights[n-1];
    if(currentWeight > capacity){   // we will discard the current item
        return table[n][capacity] = knapSackRecursive(capacity, n-1);  // moving on to the rest of the items
    }
    int remainingCapacity = capacity - currentWeight; // remaining capacity of knapscak if we choose the current item
    int val1 = currentVal + knapSackRecursive(remainingCapacity, n-1);  // if we choose the current item
    int val2 = knapSackRecursive(capacity, n-1);  // if we discard the current item
    return table[n][capacity] = Math.max(val1, val2);
  }

  // Bottom Up Solution
  public int knapSackBottomUp(int capacity, int n){
    for(int i=0; i<=n;i++){
      for(int j=0; j<=capacity; j++){
        if(i==0 || j == 0){   // initialization
          table[i][j] = 0;
        }
        else if(weights[i-1] <= j){
          int val1 = values[i-1] + table[i-1][j - weights[i-1]];
          int val2 = table[i-1][j];
          table[i][j] = Math.max(val1, val2);
        }
        else{
          table[i][j] = table[i-1][j];
        }
      }
    }
    return table[n][capacity];
  }
}
