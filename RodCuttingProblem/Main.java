
public class Main{
  public static void main(String[] args){
    // int[] price = {1,5,8,9,10,17,17,20};
    int[] price = {3,5,8,9,10,17,17,20};
    int N = 8;
    Solution sol = new Solution();
    int ans = sol.cutRod(price, N);
    System.out.println(ans);
  }
}

class Solution{
  // problem link : https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
  private int[][] table;
  public int cutRod(int price[], int n) {
    int[] len = new int[n];
    int i;
    for(i=1; i <= n; i++){
      len[i-1] = i;
    }

    table = new int[price.length + 1][n + 1];
    for(int[] arr : table){
      // Arrays.fill(arr, -1);  // Cannot import java.util in GFG -_-
      for(int j = 0; j < arr.length;j++){
        arr[j] = -1;
      }
    }
    for(int j = 0 ;j <= price.length; j++){
      table[j][0] = 0;
    }
    for(int j = 0 ;j <= n; j++){
      table[0][j] = 0;
    }

    return helper(n, price.length, price, len);
  }

  private int helper(int N, int n, int[] price, int[] len){
  // N is the total length of the rod
  // n is the length of the arrays
    if(N == 0 || n == 0)  return 0;
    if(table[n][N] != -1) return table[n][N];
    int currentLen = len[n-1];
    int currentPrice = price[n-1];
    int remainingLen = N - currentLen;

    if(currentLen > N){
      return table[n][N] = helper(N, n-1, price, len);
    }

    int include = currentPrice + helper(remainingLen, n, price, len);
    int exclude = helper(N, n-1, price, len);

    return table[n][N] = Math.max(exclude, include);
  }
}
