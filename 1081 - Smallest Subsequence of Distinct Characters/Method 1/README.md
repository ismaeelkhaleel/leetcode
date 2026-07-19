## LeetCode Solution: Smallest Subsequence
The given LeetCode problem requires finding the lexicographically smallest subsequence of a given string. This can be achieved by utilizing a stack-based approach.

### **Intuition & Approach**
To find the smallest subsequence, we can use a stack to keep track of characters that have been visited. The key idea is to maintain a stack where the top of the stack always contains the smallest character that has not been visited yet. We also keep track of the last occurrence of each character in the string using an array `last`. 

We iterate through the string, and for each character, we check if it has been visited before. If it has, we skip it. If not, we try to remove characters from the top of the stack that are larger than the current character and have a later occurrence in the string. This ensures that the stack always contains the smallest possible characters.

### **Dry Run**
Let's consider the example string "bcabc". 

1. We first initialize the `last` array to store the last occurrence of each character:
   - `last['a' - 'a'] = 3` (last occurrence of 'a' is at index 3)
   - `last['b' - 'a'] = 4` (last occurrence of 'b' is at index 4)
   - `last['c' - 'a'] = 2` (last occurrence of 'c' is at index 2)

2. We start iterating through the string:
   - At index 0, `ch = 'b'`. The stack is empty, so we append 'b' to the stack and mark it as visited.
     - `stack = "b"`
     - `visited['b' - 'a'] = true`
   - At index 1, `ch = 'c'`. The top of the stack 'b' is smaller than 'c', so we append 'c' to the stack and mark it as visited.
     - `stack = "bc"`
     - `visited['c' - 'a'] = true`
   - At index 2, `ch = 'a'`. The top of the stack 'c' is larger than 'a' and has a later occurrence, so we remove 'c' from the stack and mark it as unvisited.
     - `stack = "b"`
     - `visited['c' - 'a'] = false`
   - We then append 'a' to the stack and mark it as visited.
     - `stack = "ba"`
     - `visited['a' - 'a'] = true`
   - At index 3, `ch = 'b'`. We have already visited 'b', so we skip it.
   - At index 4, `ch = 'c'`. We have already visited 'c', so we skip it.

The final stack contains the lexicographically smallest subsequence "bac".

### **Complexity Analysis**
- **Time Complexity:** O(n), where n is the length of the string. This is because we are iterating through the string twice: once to find the last occurrence of each character and once to build the stack.
- **Space Complexity:** O(n), where n is the length of the string. This is because in the worst case, we might need to store all characters in the stack. The `last` and `visited` arrays have a fixed size of 26, which is negligible compared to the size of the input string.