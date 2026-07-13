### LeetCode Solution: Sequential Digits
#### Problem Overview
The problem requires generating a list of sequential digits within a given range. A sequential digit is a number where each digit is one more than the previous digit.

### **Intuition & Approach**
To solve this problem, we can use a brute force approach by generating all possible sequential digits and checking if they fall within the given range. We start by iterating over each possible starting digit from 1 to 9. For each starting digit, we generate all possible sequential digits by appending the next digit to the current number. We check if the generated number falls within the given range and add it to the result list if it does.

### **Dry Run**
Let's take a simple example to illustrate the approach. Suppose we have a range of `low = 100` and `high = 300`. We want to find all sequential digits within this range.

1. Start with `start = 1`:
   - `num = 1`, `next = 2`: `num = 12`, which is less than `low`, so we continue.
   - `num = 12`, `next = 3`: `num = 123`, which is within the range, so we add it to the result list.
   - We continue this process until `next` exceeds 9.
2. Move to `start = 2`:
   - `num = 2`, `next = 3`: `num = 23`, which is less than `low`, so we continue.
   - `num = 23`, `next = 4`: `num = 234`, which is within the range, so we add it to the result list.
   - We continue this process until `next` exceeds 9.
3. We repeat this process for all starting digits from 1 to 9.

After generating all possible sequential digits, we sort the result list in ascending order.

### **Complexity Analysis**
* **Time Complexity:** O(1)
  The time complexity is constant because we are generating a fixed number of sequential digits (at most 9 starting digits, each with at most 9 possible next digits). The sorting operation at the end also has a constant time complexity because the size of the input list is bounded by a constant.
* **Space Complexity:** O(1)
  The space complexity is constant because we are storing a fixed number of sequential digits in the result list. The maximum size of the result list is bounded by a constant, making the space complexity constant.