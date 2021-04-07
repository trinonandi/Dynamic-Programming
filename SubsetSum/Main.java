public class Main{
  public static void main(String[] args){
    int[] arr = {2, 3, 7, 8, 10};
    int maxSum = 14;
    Solution sol = new Solution(arr, maxSum);
    boolean ans = sol.subsetSumBottomUp(maxSum, arr.length);
    System.out.println(ans);
  }
}

class Solution{
  // The problem is Subjected to constraint maxSum >= 0 and n >= 0
  // The problem is NP complete so there is no polynomial time solution. The DP solutions are pseudo polynomial
  private int[] arr;
  private boolean[][] table;
  public Solution(int[] arr, int maxSum){
    this.arr = arr;
    this.table = new boolean[arr.length  + 1][maxSum + 1];
  }

  public boolean subsetSumRecursion(int maxSum, int n){   // time and space exponential
    // if(maxSum < 0 )   return false;
    // if(maxSum == 0){
    //   if(n >= 0)  return true;
    //   else        return false;
    // }
    // if(maxSum > 0 && n <= 0) return false;

    // base
    if(maxSum == 0)  return true;
    if(maxSum > 0 && n == 0) return false;

    int currentWeight = arr[n-1];
    if(currentWeight > maxSum){
      return subsetSumRecursion(maxSum, n-1);
    }
    boolean inclusive = subsetSumRecursion(maxSum - currentWeight , n-1);
    boolean exclusive = subsetSumRecursion(maxSum, n-1);

    return (inclusive || exclusive);
  }

  // memoized Solution
  public boolean subsetSumMemo(int maxSum, int n){    // Time = Space O(maxSum * n)
    // base
    if(maxSum == 0)  return table[n][maxSum] = true;
    if(maxSum > 0 && n == 0) return false;
    if(table[n][maxSum]) return table[n][maxSum];

    int currentWeight = arr[n-1];
    if(currentWeight > maxSum){
      return table[n][maxSum] = subsetSumMemo(maxSum, n-1);
    }
    boolean inclusive = subsetSumMemo(maxSum - currentWeight , n-1);
    boolean exclusive = subsetSumMemo(maxSum, n-1);

    return table[n][maxSum] = (inclusive || exclusive);

  }

  // Bottom up Solution
  public boolean subsetSumBottomUp(int maxSum, int n){    // Time = Space O(maxSum * n)
    int i, j;
    for(i=0;i<=n;i++){
      for(j=0;j<=maxSum;j++){
        // initialization
        if(i == 0 && j == 0)    table[i][j] = true;
        else if(j == 0)  table[i][j] = true;
        else if(i == 0) table[i][j] = false;

        else{
          // body
          int currentWeight = arr[i-1];
          if(currentWeight > j)  table[i][j] = table[i-1][j];
          else{
            boolean inclusive = table[i-1][j-currentWeight];
            boolean exclusive = table[i-1][j];
            table[i][j] = (inclusive || exclusive);
          }
        }
      }
    }
    return table[n][maxSum];
  }
}
