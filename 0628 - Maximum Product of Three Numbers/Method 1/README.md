### Maximum Product of Three Numbers
#### LeetCode Solution Explanation

### **Intuition & Approach**
To find the maximum product of three numbers in an array, we can leverage the fact that the maximum product can be obtained by either multiplying the three largest numbers or two smallest (most negative) numbers and the largest number. This is because two negative numbers can result in a positive product, which can be maximized when multiplied by the largest number.

We can solve this by first sorting the input array. Then, we calculate two possible products: 
- `p1` is the product of the three largest numbers, which are the last three elements in the sorted array.
- `p2` is the product of the two smallest (most negative) numbers and the largest number, which are the first two elements and the last element in the sorted array.

Finally, we return the maximum of `p1` and `p2` as the result.

### **Dry Run**
Let's consider an example array `nums = [-10, -10, 1, 3, 2]`.

1. **Sorting the array**: After sorting, `nums` becomes `[-10, -10, 1, 2, 3]`.
2. **Calculating `p1`**: `p1` is the product of the three largest numbers, which are `1`, `2`, and `3`. So, `p1 = 1 * 2 * 3 = 6`.
3. **Calculating `p2`**: `p2` is the product of the two smallest numbers and the largest number, which are `-10`, `-10`, and `3`. So, `p2 = -10 * -10 * 3 = 300`.
4. **Returning the result**: The maximum of `p1` and `p2` is `300`, so the function returns `300`.

### **Complexity Analysis**
- **Time complexity**: O(n log n) due to the sorting operation, where n is the length of the input array.
- **Space complexity**: O(1) if we consider the space required for the output and variables, assuming the sorting is done in-place. However, if the sorting algorithm used by `Arrays.sort()` requires additional space (like Timsort in Java), the space complexity would be O(n). 

In this case, since we are using Java's built-in `Arrays.sort()` method, the actual space complexity depends on the implementation details of the sorting algorithm used.