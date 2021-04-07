public class Main{
  public static void main(String[] args) {
    Solution sol = new Solution();
    int arr[] = {1,2,7};
    int ans = sol.minDiffernce(arr, arr.length);
    System.out.println(ans);
  }
}

class Solution{
  private int arr[];
  private boolean table[][];

  private boolean subsetSumBottomUp(int maxSum, int n){    // Time = Space O(maxSum * n)
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

  public int minDiffernce(int arr[], int n) {   // time = space = O(n * range)
    int range = 0;
    for(int i : arr)  range += i;   // range = summation(arr[i])

    // inti for subsetSumBottomUp method
    this.arr = arr;
    this.table = new boolean[arr.length  + 1][range + 1];

    subsetSumBottomUp(range, arr.length);

    // s1 will be the max true index from the first half
    int s1 = 0;
    for(int i = 0; i <= range/2; i++){
      if(table[arr.length][i])  s1 = i;
    }

    // range - s1 will be the min flase index from the second half
    int min = (range - s1) - s1 ;
    return min;
  }
}
