public class Main{
  public static void main(String[] args) {
    int arr[] = {1,1,2,3};
    int diff = 1;
    Solution sol = new Solution();
    int ans = sol.countSubsetWithDiff(arr, diff);
    System.out.println(ans);
  }
}

class Solution{
  // LEETCODE 494
  int arr[];
  int table[][];

  private int countSubsetSumBottomUp(int maxSum, int n){
    for(int i = 0; i <= n; i++){
      for(int j = 0; j <= maxSum; j++){
        if(i == 0 && j > 0) table[i][j] = 0;
        else if(j == 0) table[i][j] = 1;
        else{
          int currentValue = arr[i-1];
          if(currentValue > j)    table[i][j] = table[i-1][j];
          else{
            int include = table[i-1][j-currentValue];
            int exclude = table[i-1][j];
            table[i][j] = (exclude + include);
          }
        }
      }
    }
    return table[n][maxSum];
  }

  public int countSubsetWithDiff(int[] arr, int diff){
    int range = 0;
    for(int i : arr)  range += i;
    int s1 = (range + diff) / 2;

    this.arr = arr;
    this. table = new int[arr.length + 1][s1 + 1];
    return countSubsetSumBottomUp(s1, arr.length);
  }
}
