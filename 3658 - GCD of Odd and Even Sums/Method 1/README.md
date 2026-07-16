### LeetCode Solution: GCD of Odd and Even Sums
#### **Intuition & Approach**
To find the greatest common divisor (GCD) of the sum of odd and even numbers up to `n`, we can leverage mathematical properties to simplify the problem. The sum of all odd numbers up to `n` can be represented as `n * n`, and the sum of all even numbers up to `n` can be represented as `n * (n + 1)`. We can then use the Euclidean algorithm to find the GCD of these two sums.

The Euclidean algorithm is a method for finding the GCD of two numbers based on the principle that the GCD of two numbers does not change if the larger number is replaced by its difference with the smaller number. This process is repeated until the two numbers become equal, which is the GCD.

#### **Dry Run**
Let's consider an example with `n = 3`. 

1. Calculate the sum of odd numbers: `sumOdd = n * n = 3 * 3 = 9`.
2. Calculate the sum of even numbers: `sumEven = n * (n + 1) = 3 * (3 + 1) = 3 * 4 = 12`.
3. Apply the Euclidean algorithm to find the GCD:
   - `a = 9`, `b = 12`
   - `temp = b = 12`, `b = a % b = 9 % 12 = 9`, `a = temp = 12`
   - `temp = b = 9`, `b = a % b = 12 % 9 = 3`, `a = temp = 9`
   - `temp = b = 3`, `b = a % b = 9 % 3 = 0`, `a = temp = 3`
   - Since `b` is now `0`, the algorithm stops, and `a = 3` is the GCD.

#### **Complexity Analysis**
- **Time Complexity:** O(log min(sumOdd, sumEven))
  - The time complexity is determined by the Euclidean algorithm used to find the GCD. The number of steps required is proportional to the number of times we can divide the smaller number by the larger one until the remainder is zero, which is logarithmic in the size of the smaller number.
- **Space Complexity:** O(1)
  - The space complexity is constant because we only use a fixed amount of space to store the variables `sumOdd`, `sumEven`, `a`, `b`, and `temp`, regardless of the input size `n`.