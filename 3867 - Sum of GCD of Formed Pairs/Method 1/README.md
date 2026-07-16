## LeetCode Solution Explanation: GCD Sum
The given LeetCode solution calculates the sum of the greatest common divisors (GCDs) of pairs of prefix GCDs in a given array. This explanation breaks down the approach, provides a step-by-step dry run, and analyzes the time and space complexity.

### Intuition & Approach
To solve this problem, we first calculate the prefix GCDs by finding the GCD of each element with the maximum element encountered so far. We store these prefix GCDs in an array. Then, we sort the prefix GCD array and initialize two pointers, one at the start and one at the end of the array. We calculate the GCD of the elements at the current positions of the two pointers and add it to the sum. We move the pointers towards each other until they meet.

The key insight here is that the GCD of two numbers can be calculated using the Euclidean algorithm, which is implemented in the `gcd` function. This function takes two integers as input and returns their GCD.

### Dry Run
Let's consider an example array `nums = [2, 4, 6, 8]`. We will go through the steps of the solution with this example.

1. Initialize `mxi` with the first element of the array: `mxi = 2`.
2. Calculate the prefix GCDs:
   - `prefixGcd[0] = gcd(2, 2) = 2`
   - `mxi = max(2, 4) = 4`, `prefixGcd[1] = gcd(4, 4) = 4`
   - `mxi = max(4, 6) = 6`, `prefixGcd[2] = gcd(6, 6) = 6`
   - `mxi = max(6, 8) = 8`, `prefixGcd[3] = gcd(8, 8) = 8`
   So, `prefixGcd = [2, 4, 6, 8]`.
3. Sort the `prefixGcd` array: `prefixGcd = [2, 4, 6, 8]` (already sorted).
4. Initialize the pointers: `low = 0`, `high = 3`.
5. Calculate the sum of GCDs:
   - `g = gcd(prefixGcd[0], prefixGcd[3]) = gcd(2, 8) = 2`, `sum = 2`
   - `low = 1`, `high = 2`
   - `g = gcd(prefixGcd[1], prefixGcd[2]) = gcd(4, 6) = 2`, `sum = 2 + 2 = 4`
   - `low = 2`, `high = 1`, so the loop ends.
6. Return the sum: `sum = 4`.

### Complexity Analysis
- **Time Complexity:** O(n log n) due to the sorting of the `prefixGcd` array, where n is the length of the input array. The calculation of prefix GCDs and the GCD sum takes O(n) time.
- **Space Complexity:** O(n) for storing the `prefixGcd` array, where n is the length of the input array. The space required does not change with the size of the input array, making it linear.