import java.util.*;
public class Main{
  public static void main(String[] args){
    // int[] values = {10, 40, 50, 70};
    // int[] weights = {1, 3, 4, 5};
    // int capacity = 8;      // ans = 110


    // int[] values = {1, 30};
    // int[] weights = {1, 50};
    // int capacity = 100;       // ans = 100

    int[] values = {10, 30, 20};
    int[] weights = {5, 10, 15};
    int capacity = 100;       // ans = 300

    Solution sol = new Solution(values, weights, capacity);
    int ans = sol.unboundedKnapSackBottomUp(capacity, values.length);

    System.out.println(ans);
  }
}

class Solution{
  int[] values;
  int[] weights;
  int[][] table;

  public Solution(int[] values, int[] weights, int capacity){
    this.values = values;
    this.weights = weights;
    this.table = new int[values.length + 1][capacity + 1];
    for(int i = 1 ; i <= values.length; i++){
      Arrays.fill(table[i], -1);
    }
  }

  public int unboundedKnapSackRec(int capacity, int n){
    if(capacity == 0 || n == 0) return 0;
    int currentValue = values[n-1];
    int currentWeight = weights[n-1];
    int remainingWeight = capacity - currentWeight;

    if(currentWeight > capacity)  return unboundedKnapSackRec(capacity, n-1);
    int include = currentValue + unboundedKnapSackRec(remainingWeight, n);
    int exclude = unboundedKnapSackRec(capacity, n - 1);

    return Math.max(include, exclude);
  }

  public int unboundedKnapSackMemo(int capacity, int n){
    if(capacity == 0 || n == 0) return 0;
    if(table[n][capacity] != -1) return table[n][capacity];

    int currentValue = values[n-1];
    int currentWeight = weights[n-1];
    int remainingWeight = capacity - currentWeight;

    if(currentWeight > capacity)  return table[n][capacity] = unboundedKnapSackMemo(capacity, n-1);
    int include = currentValue + unboundedKnapSackMemo(remainingWeight, n);
    int exclude = unboundedKnapSackMemo(capacity, n - 1);

    return table[n][capacity] = Math.max(include, exclude);
  }

  public int unboundedKnapSackBottomUp(int capacity, int n){
    for(int i=0; i<=n; i++){
      for(int j=0; j<=capacity; j++){
        if(i == 0 || j == 0 ) table[i][j] = 0;
        else{
          int currentValue = values[i-1];
          int currentWeight = weights[i-1];
          int remainingWeight = j - currentWeight;
          if(currentWeight > j)  table[i][j] = table[i-1][j];
          else{
            int include = currentValue + table[i][remainingWeight];
            int exclude = table[i-1][j];
            table[i][j] = Math.max(include, exclude);
          }
        }
      }
    }
    return table[n][capacity];
  }

}
