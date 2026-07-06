### Approach
The approach used in this code is a recursive solution using the concept of exponentiation by squaring. This method reduces the number of multiplications required to calculate `x` raised to the power of `n`. The basic idea is to break down the problem into smaller sub-problems by dividing the exponent `n` by 2 and solving for `x` raised to the power of `n/2`. This approach takes advantage of the fact that `x` raised to the power of `n` can be expressed as `(x * x)` raised to the power of `n/2` when `n` is even, and `x` multiplied by `(x * x)` raised to the power of `(n-1)/2` when `n` is odd.

### Time Complexity
The time complexity of this solution is **O(log n)**. This is because with each recursive call, the exponent `n` is divided by 2, effectively reducing the problem size by half. This logarithmic reduction in problem size leads to a time complexity that is proportional to the logarithm of the input size `n`.

### Space Complexity
The space complexity of this solution is **O(log n)**. This is due to the recursive call stack, which can grow up to a depth of `log n` in the worst case. Each recursive call adds a layer to the call stack, and since the maximum depth of the recursion tree is `log n`, the space complexity is proportional to the logarithm of the input size `n`.