## 1. Intuition & Approach  

The problem asks for **all indices** at which a given `target` appears **after the array is sorted in non‑decreasing order**.  
The most straightforward way to satisfy the requirement is:

1. **Sort** the input array `nums`.  
2. **Traverse** the sorted array once, collecting every index `i` where `nums[i] == target`.  

Because sorting guarantees that equal values are placed consecutively, a single linear scan is sufficient to locate every occurrence of `target`. The collected indices are naturally in ascending order, which matches the expected output format.

### Why this works  

* Sorting rearranges the elements but does not change their values, so the set of positions where `target` occurs in the sorted array can be discovered by a simple equality check.  
* After sorting, all `target` values occupy a contiguous block. Scanning from left to right and adding the current index whenever the equality holds yields exactly the required list.  

The algorithm uses only standard library calls (`Arrays.sort`) and a single `for` loop, keeping the implementation concise and easy to reason about.

---

## 2. Dry Run  

Consider the input  

```java
int[] nums   = {1, 2, 5, 2, 3};
int   target = 2;
```

### Step‑by‑step execution  

| Step | Operation                              | `nums` (current state) | `i` | Condition `nums[i] == target` | Action taken                     | `list` (collected indices) |
|------|----------------------------------------|------------------------|-----|------------------------------|----------------------------------|----------------------------|
| 1    | `Arrays.sort(nums)`                    | `{1, 2, 2, 3, 5}`      | —   | —                            | – (array is sorted)              | `[]`                       |
| 2    | Enter `for` loop (`i = 0`)             | `{1, 2, 2, 3, 5}`      | 0   | `1 == 2` → **false**         | No addition                      | `[]`                       |
| 3    | `i = 1`                                | `{1, 2, 2, 3, 5}`      | 1   | `2 == 2` → **true**          | `list.add(1)`                    | `[1]`                      |
| 4    | `i = 2`                                | `{1, 2, 2, 3, 5}`      | 2   | `2 == 2` → **true**          | `list.add(2)`                    | `[1, 2]`                   |
| 5    | `i = 3`                                | `{1, 2, 2, 3, 5}`      | 3   | `3 == 2` → **false**         | No addition                      | `[1, 2]`                   |
| 6    | `i = 4`                                | `{1, 2, 2, 3, 5}`      | 4   | `5 == 2` → **false**         | No addition                      | `[1, 2]`                   |
| 7    | Loop ends (`i == nums.length`)         | —                      | —   | —                            | Return `list`                    | `[1, 2]`                   |

**Result:** The method returns `[1, 2]`, which are exactly the indices of `target = 2` in the sorted array.

---

## 3. Complexity Analysis  

| Metric          | Explanation                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| **Time**        | `Arrays.sort(nums)` costs `O(n log n)` for `n = nums.length`. The subsequent linear scan adds `O(n)`. Overall time complexity: **`O(n log n)`**. |
| **Space**       | Sorting is performed in‑place for primitive arrays, using only a small recursion stack (`O(log n)` for quicksort/merge sort implementations). The result list stores at most `n` integers, i.e., `O(k)` where `k` is the number of occurrences of `target` (worst‑case `k = n`). Hence auxiliary space (excluding output) is **`O(1)`**, and total space including output is **`O(k)`**. |

The solution meets the constraints of the LeetCode problem while remaining easy to understand and maintain.