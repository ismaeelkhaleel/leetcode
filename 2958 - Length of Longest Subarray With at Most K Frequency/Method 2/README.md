### LeetCode Solution: Maximum Length of Subarray with Given Constraints
#### Problem Overview
The problem requires finding the maximum length of a subarray within a given array `nums` where each element appears at most `k` times.

### **Intuition & Approach**
To solve this problem, we can utilize a sliding window approach along with a `Map` data structure to track the frequency of each element within the current window. The `Map` will store the frequency of each element, and we will adjust the window boundaries based on the frequency constraint.

We maintain two pointers, `low` and `high`, representing the start and end of the window, respectively. As we expand the window to the right by incrementing `high`, we update the frequency of the newly added element in the `Map`. If the frequency of any element exceeds `k`, we shrink the window from the left by incrementing `low` until the frequency constraint is satisfied.

### **Dry Run**
Let's consider an example with `nums = [1, 2, 1, 2, 3, 2, 2]` and `k = 2`.

1. Initialize `map = new Map()`, `low = 0`, `maxLen = 0`, and `high = 0`.
2. At `high = 0`, `nums[high] = 1`, so `map.set(1, 1)`. The window is `[1]`, and `maxLen = 1`.
3. At `high = 1`, `nums[high] = 2`, so `map.set(2, 1)`. The window is `[1, 2]`, and `maxLen = 2`.
4. At `high = 2`, `nums[high] = 1`, so `map.set(1, 2)`. The window is `[1, 2, 1]`, and `maxLen = 3`.
5. At `high = 3`, `nums[high] = 2`, so `map.set(2, 2)`. The window is `[1, 2, 1, 2]`, and `maxLen = 4`.
6. At `high = 4`, `nums[high] = 3`, so `map.set(3, 1)`. The window is `[1, 2, 1, 2, 3]`, and `maxLen = 5`.
7. At `high = 5`, `nums[high] = 2`, so `map.set(2, 3)`. Since `map.get(2) > k`, we increment `low` until `map.get(2) <= k`. We remove `nums[low] = 1` from the window, so `map.set(1, 1)`. The window is `[2, 1, 2, 3, 2]`, and `maxLen = 5`.
8. At `high = 6`, `nums[high] = 2`, so `map.set(2, 4)`. Since `map.get(2) > k`, we increment `low` until `map.get(2) <= k`. We remove `nums[low] = 2` and `nums[low] = 1` from the window, so `map.set(2, 2)` and `map.set(1, 0)`. The window is `[2, 3, 2, 2]`, and `maxLen = 5`.

### **Complexity Analysis**
* Time complexity: **O(n)**, where n is the length of the input array `nums`. This is because we process each element in the array once.
* Space complexity: **O(n)**, where n is the length of the input array `nums`. This is because in the worst case, we might need to store all elements in the `Map`.