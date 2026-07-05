**Fibonacci Sequence using Memoization**
=====================================

### Code
```java
public class Solution {
    private int[] dp;

    public int solve(int n, int[] dp) {
        return solve(n, dp);
    }

    public void init(int n) {
        Arrays.fill(dp, -1);
    }

    public int fib(int n) {
        int[] dp = new int[n + 1];
        // Initialize dp array
        this.dp = dp;
        init(n);
        return solve(n, dp);
    }

    private int solve(int n, int[] dp) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        // If value already computed, return it directly
        if (dp[n] != -1) {
            return dp[n];
        }
        // Calculate the n-th Fibonacci number and store it in dp array
        dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
        return dp[n];
    }
}
```

### Approach

The code uses a recursive approach with memoization to calculate the `n`-th Fibonacci number. Memoization is a technique where we store intermediate results to avoid redundant computation.

Here's the high-level approach:

1.  Initialize a dynamic programming array `dp` of size `n + 1`.
2.  Fill the array `dp` with `-1` to indicate that the values have not been computed yet.
3.  Define a private method `solve(n, dp)` to compute the `n`-th Fibonacci number recursively.
4.  In the `solve` method:

    *   Base cases: If `n` is 0 or 1, return `n` as the `n`-th Fibonacci number.
    *   If the value of `dp[n]` is not `-1`, return it directly as it has already been computed.
    *   Otherwise, calculate the `n`-th Fibonacci number by recursively calling `solve(n - 1, dp)` and `solve(n - 2, dp)`, and store the result in `dp[n]`.
5.  Call the `fib` method to compute the `n`-th Fibonacci number.

### Time Complexity

The time complexity of the code is **O(n)**. The recursive `solve` method visits each node in the recursion tree, which has `n` levels, resulting in a time complexity of O(n).

However, if we consider the number of recursive calls, the time complexity would be **O(n) + O(n-1) + O(n-2) + ... + O(1)**, which simplifies to **O(2n - 1)**. But since we are using memoization, the time complexity is optimized to **O(n)**, because we store the intermediate results and reuse them instead of recalculating them.

### Space Complexity

The space complexity of the code is **O(n)**. The dynamic programming array `dp` requires `n + 1` space to store the intermediate results. Additionally, the recursive call stack requires space proportional to the maximum depth of the recursion tree, which is **O(n)** in the worst case.

In total, the space complexity is **O(n) + O(n) = O(2n)**, which simplifies to **O(n)** because we drop constant factors in Big O notation.