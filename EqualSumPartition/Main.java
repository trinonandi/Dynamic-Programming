public class Main{
  public static void main(String[] args){
    int arr[] = {1,2,3,5};
    Solution sol = new Solution(arr);
    System.out.println(sol.equalSumPartition());
  }
}

class Solution{
  // IN LEETCODE THE MEMOIZED SOLUTION IS GIVING TLE SO USED THE BOTTOM UP VERSION

  private int[] arr;
  private boolean[][] table;
  public Solution(int[] arr){
    this.arr = arr;
  }

  // using subset sum problem as a helper
  public boolean subsetSumBottomUp(int maxSum, int n){
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

  public boolean equalSumPartition(){
    int sum = 0;
    for(int i : this.arr) sum += i;

    if(sum % 2 != 0 ) return false;   // if the sum is odd

    int maxSum = sum/2;
    table = new boolean[arr.length + 1][maxSum + 1];
    return subsetSumBottomUp(sum/2, arr.length);
  }
}
