### LeetCode Solution Explanation: Maximum Length Substring
#### **Intuition & Approach**
The problem requires finding the maximum length substring with at most two occurrences of each character. We can solve this by utilizing a sliding window approach along with a `HashMap` to track character frequencies. The sliding window is defined by two pointers, `left` and `right`, representing the start and end of the current substring. As we move the `right` pointer to the right, we add the character at the `right` index to the `HashMap` and increment its frequency. If the frequency of the character exceeds 2, we move the `left` pointer to the right, removing the character at the `left` index from the `HashMap` and decrementing its frequency until the frequency of the character at the `right` index is less than or equal to 2. This ensures that the substring within the sliding window always satisfies the condition.

#### **Dry Run**
Let's consider the example string "aabcbc". We'll perform a step-by-step dry run to illustrate how the variables change at each step.

1. Initialize variables:
   - `map`: an empty `HashMap`
   - `left`: 0
   - `right`: 0
   - `maxLen`: 0
   - String: "aabcbc"

2. `right` = 0, `ch` = 'a'
   - `map`: {'a': 1}
   - `left`: 0
   - `right`: 0
   - `maxLen`: 1

3. `right` = 1, `ch` = 'a'
   - `map`: {'a': 2}
   - `left`: 0
   - `right`: 1
   - `maxLen`: 2

4. `right` = 2, `ch` = 'b'
   - `map`: {'a': 2, 'b': 1}
   - `left`: 0
   - `right`: 2
   - `maxLen`: 3

5. `right` = 3, `ch` = 'c'
   - `map`: {'a': 2, 'b': 1, 'c': 1}
   - `left`: 0
   - `right`: 3
   - `maxLen`: 4

6. `right` = 4, `ch` = 'b'
   - `map`: {'a': 2, 'b': 2, 'c': 1}
   - `left`: 0
   - `right`: 4
   - `maxLen`: 5

7. `right` = 5, `ch` = 'c'
   - `map`: {'a': 2, 'b': 2, 'c': 2}
   - `left`: 0
   - `right`: 5
   - `maxLen`: 6

Now, let's move the `left` pointer to the right when the frequency of a character exceeds 2.

8. `right` = 5, `ch` = 'c', `map.get(ch)` > 2 is false, but we need to check the previous steps where `map.get(ch)` > 2.
   - At `right` = 5, `map.get('c')` = 2, which is not greater than 2. However, we should consider the previous steps where `map.get('a')` or `map.get('b')` > 2.
   - When `right` = 3, `map.get('a')` = 2, which is not greater than 2. When `right` = 4, `map.get('b')` = 2, which is also not greater than 2.
   - However, we should consider the case when `right` = 5 and `left` = 0. In this case, `map.get('a')` = 2, `map.get('b')` = 2, and `map.get('c')` = 2. We need to move the `left` pointer to the right until `map.get('a')` < 2 or `map.get('b')` < 2 or `map.get('c')` < 2.
   - Move `left` to the right until `map.get('a')` < 2.
   - `left` = 2, `map`: {'b': 1, 'c': 2}
   - `maxLen` remains the same.

#### **Complexity Analysis**
- **Time Complexity:** O(n), where n is the length of the string. This is because we are potentially scanning the string once with the `right` pointer and at most once with the `left` pointer.
- **Space Complexity:** O(min(n, m)), where m is the size of the character set. This is because in the worst case, we might store all unique characters in the `HashMap`. However, since we are dealing with characters, the maximum number of unique characters is limited (e.g., 128 for ASCII characters or 256 for extended ASCII characters), making the space complexity effectively O(1) in practice.