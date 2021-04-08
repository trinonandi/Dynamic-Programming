import java.util.*;
public class Main{
  public static void main(String[] args) {
    int arr[] = {3, 3, 3, 3};
    int maxSum = 6;
    Solution sol = new Solution(arr, maxSum);
    System.out.println(sol.countSubsetSumBottomUp(maxSum, arr.length));
  }
}

class Solution{
  int arr[];
  int[][] table;
  Solution(int[] arr, int maxSum){
    this.arr = arr;
    table = new int[arr.length + 1][maxSum + 1];
    for(int[] i : table){
      Arrays.fill(i, -1);
    }
  }
  // recursive solution
  public int countSubsetSum(int maxSum, int n){
    if(maxSum == 0 && n == 0)   return 1;
    if(n == 0)  return 0;

    int currentValue = arr[n-1];
    if(currentValue > maxSum){
      return countSubsetSum(maxSum, n-1);
    }
    int include = countSubsetSum(maxSum - currentValue, n-1);
    int exclude = countSubsetSum(maxSum, n-1);
    return (include + exclude);
  }

  // memoized solution
  public int countSubsetSumMemo(int maxSum, int n){
    if(maxSum == 0 && n == 0) return table[n][maxSum] = 1;
    if(n == 0) return 0;
    if(table[n][maxSum] != -1) return table[n][maxSum];

    int currentValue = arr[n-1];
    if(currentValue > maxSum){
      return table[n][maxSum] = countSubsetSumMemo(maxSum, n-1);
    }
    int include = countSubsetSumMemo(maxSum - currentValue, n-1);
    int exclude = countSubsetSumMemo(maxSum, n-1);
    return table[n][maxSum] = (include + exclude);
  }

  public int countSubsetSumBottomUp(int maxSum, int n){
    int i, j;
    for(i = 0, j = 1; j<= maxSum; j++)  table[i][j] = 0;
    for(i = 1, j = 0; i <= n; i++)  table[i][j] = 1;
    table[0][0] = 1;
    for(i = 1; i <= n; i++){
      for(j = 0; j <= maxSum; j++){
        int currentValue = arr[i-1];
        if(currentValue > j)    table[i][j] = table[i-1][j];
        else{
          int include = table[i-1][j-currentValue];
          int exclude = table[i-1][j];
          table[i][j] = (exclude + include);
        }
      }
    }
    return table[n][maxSum];
  }
}
