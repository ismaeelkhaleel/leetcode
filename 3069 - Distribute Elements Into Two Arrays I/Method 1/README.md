## 1. Intuition & Approach  

The problem (LeetCode 2363 – *Merge Similar Elements Into Result Array*) asks for a **result array** built from the original `nums` by repeatedly comparing the last elements of two growing sub‑arrays.  

The core idea is straightforward:

1. **Initialize two containers** – `list1` starts with `nums[0]`, `list2` starts with `nums[1]`.  
2. **Iterate from the third element onward** (`i = 2 … n‑1`).  
   * Let `last1` be the last value in `list1` and `last2` be the last value in `list2`.  
   * If `last1 > last2`, the current number belongs to `list1`; otherwise it belongs to `list2`.  
3. **Concatenate** `list1` followed by `list2` back into the original `nums` array and return it.

Why does this work?  
At any step the decision only depends on the *most recent* elements of the two partial results. The rule “append to the list whose last element is larger” guarantees that the relative order inside each list respects the original order, while the two lists together form the required result. The algorithm does **not** need any additional data structures beyond the two `ArrayList`s, making the implementation concise.

---

## 2. Dry Run  

Consider a small example to see the algorithm in action:

```text
nums = [5, 2, 4, 3, 1]
```

| Step | Action | list1 (values) | list2 (values) | idx (next write position) |
|------|--------|----------------|----------------|---------------------------|
| 0    | Initialise | `[5]` (add nums[0]) | `[2]` (add nums[1]) | – |
| 1    | i = 2, nums[i] = 4 | last1 = 5, last2 = 2 → 5 > 2 → add to list1 | `[5, 4]` | `[2]` |
| 2    | i = 3, nums[i] = 3 | last1 = 4, last2 = 2 → 4 > 2 → add to list1 | `[5, 4, 3]` | `[2]` |
| 3    | i = 4, nums[i] = 1 | last1 = 3, last2 = 2 → 3 > 2 → add to list1 | `[5, 4, 3, 1]` | `[2]` |
| 4    | Merge back to `nums` | copy list1 → `nums[0..3] = 5,4,3,1` | copy list2 → `nums[4] = 2` | – |

Final `nums` after the merge:

```text
[5, 4, 3, 1, 2]
```

**Explanation of each variable change**

* `list1` and `list2` are `ArrayList<Integer>` objects, so `add` appends at the end.  
* `idx1 = list1.size() - 1` and `idx2 = list2.size() - 1` always point to the **last** element of each list.  
* The comparison `list1.get(idx1) > list2.get(idx2)` decides the destination list for the current `nums[i]`.  
* After the loop, a simple two‑phase copy writes the contents of `list1` followed by `list2` back into the original array, satisfying the required output format.

---

## 3. Complexity Analysis  

| Metric | Evaluation | Reason |
|--------|------------|--------|
| **Time Complexity** | **O(n)** | The algorithm traverses `nums` once (`for i = 2 … n‑1`) and performs O(1) work per iteration (list size lookup, a single comparison, and an `add`). The final two linear copies also together touch each element exactly once. |
| **Space Complexity** | **O(n)** (auxiliary) | Two `ArrayList`s together store all `n` elements (the original array is reused for the output). No additional structures beyond these lists are required. |

The solution therefore runs in linear time with linear extra space, which is optimal for this class of problems where the entire input must be examined.  

---  

### TL;DR  

* Start two lists with the first two numbers.  
* For each subsequent number, compare the last elements of the two lists; append the number to the list with the larger last element.  
* Concatenate the two lists back into the original array.  

This **Java** implementation follows the described steps, achieving **O(n)** time and **O(n)** auxiliary space—perfect for the LeetCode “Result Array” challenge.