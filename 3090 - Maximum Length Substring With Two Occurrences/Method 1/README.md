### LeetCode Solution Explanation: Maximum Length Substring
#### **Intuition & Approach**
The problem requires finding the maximum length of a substring in a given string `s` such that no character appears more than twice. To solve this, we can use a brute force approach by generating all possible substrings of `s` and checking each one for validity. A substring is valid if no character appears more than twice. We can use a helper function `isValid` to check the validity of each substring.

The `isValid` function uses a frequency array `hash` to count the occurrences of each character in the substring. It iterates over each character in the substring, increments the corresponding count in the `hash` array, and returns `false` as soon as it finds a character that appears more than twice.

#### **Dry Run**
Let's consider a simple example to illustrate the step-by-step process. Suppose we have the input string `s = "aab"`.

1. We start by iterating over each character in `s` using the outer loop `i`. For `i = 0`, we generate substrings starting from the first character.
2. For `i = 0`, the inner loop `k` generates substrings of increasing length. For `k = 0`, the substring is `"a"`.
3. We call the `isValid` function to check the validity of the substring `"a"`. The `hash` array is initialized with all zeros, and the character `'a'` is mapped to index `0` (`'a' - 'a' = 0`). The count at index `0` is incremented to `1`, which is less than or equal to `2`, so the substring is valid.
4. We update the `maxLen` variable to `1`, which is the length of the valid substring `"a"`.
5. We continue generating substrings for `i = 0` and `k = 1`, which gives us the substring `"aa"`. The `isValid` function checks this substring and returns `true` because no character appears more than twice.
6. We update the `maxLen` variable to `2`, which is the length of the valid substring `"aa"`.
7. We repeat this process for all possible substrings of `s`.

Here's a summary of the dry run:

| `i` | `k` | Substring | `isValid` | `maxLen` |
| --- | --- | --- | --- | --- |
| 0 | 0 | `"a"` | `true` | 1 |
| 0 | 1 | `"aa"` | `true` | 2 |
| 0 | 2 | `"aab"` | `true` | 3 |
| 1 | 1 | `"a"` | `true` | 3 |
| 1 | 2 | `"ab"` | `true` | 3 |
| 2 | 2 | `"b"` | `true` | 3 |

#### **Complexity Analysis**
* **Time Complexity:** O(n^3), where n is the length of the input string `s`. This is because we have two nested loops that generate all possible substrings of `s`, and for each substring, we call the `isValid` function, which iterates over the characters in the substring.
* **Space Complexity:** O(1), because we use a fixed-size frequency array `hash` to count the occurrences of each character, regardless of the length of the input string `s`. The space used does not grow with the size of the input.