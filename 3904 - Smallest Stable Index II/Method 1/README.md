## 1. Intuition & Approach  

The task is to locate the **first stable index** `i` in an integer array `nums` such that  

```
max( nums[0 … i] ) – min( nums[i … n‑1] ) ≤ k
```  

In other words, the difference between the **largest value seen so far from the left** and the **smallest value that can be seen from the right** must be at most `k`.  
If no such index exists the answer is `-1`.

The classic way to obtain “max‑to‑left” and “min‑to‑right” information for every position in *O(n)* time is to pre‑compute two auxiliary arrays:

| auxiliary array | definition | how it is built |
|-----------------|------------|-----------------|
| `maxLeft[i]`    | `max( nums[0], …, nums[i] )` | a forward sweep that keeps a running maximum |
| `minRight[i]`   | `min( nums[i], …, nums[n‑1] )` | a backward sweep that keeps a running minimum |

Once both arrays are available, the condition for each index can be checked in a single pass:

```
score(i) = maxLeft[i] – minRight[i]
if score(i) ≤ k → i is the first stable index
```

The algorithm therefore consists of three linear passes:

1. **Forward pass** – build `maxLeft`.
2. **Backward pass** – build `minRight`.
3. **Final pass** – evaluate the condition and return the earliest index that satisfies it.

All operations are elementary integer comparisons, so the overall runtime is linear and the extra memory is linear as well.

---

## 2. Dry Run  

Consider a concrete example:

```java
nums = [3, 1, 4, 2, 5]
k    = 2
```

### Step‑by‑step execution  

| step | variables / arrays | description |
|------|--------------------|-------------|
| **Initial** | `n = 5`<br>`maxLeft = [0,0,0,0,0]`<br>`minRight = [0,0,0,0,0]`<br>`max = nums[0] = 3` | Prepare containers and the running maximum. |
| **Forward sweep (i = 0)** | `max = max(3, nums[0]) = 3`<br>`maxLeft[0] = 3` | First element – nothing changes. |
| **i = 1** | `max = max(3, 1) = 3`<br>`maxLeft[1] = 3` | Running maximum stays 3. |
| **i = 2** | `max = max(3, 4) = 4`<br>`maxLeft[2] = 4` | New maximum encountered. |
| **i = 3** | `max = max(4, 2) = 4`<br>`maxLeft[3] = 4` | Maximum unchanged. |
| **i = 4** | `max = max(4, 5) = 5`<br>`maxLeft[4] = 5` | Final `maxLeft` array: **[3, 3, 4, 4, 5]** |
| **Backward sweep – initialise** | `min = nums[4] = 5` | Prepare the running minimum from the right. |
| **i = 4** | `min = min(5, 5) = 5`<br>`minRight[4] = 5` | Last element – nothing changes. |
| **i = 3** | `min = min(5, 2) = 2`<br>`minRight[3] = 2` | New smaller value found. |
| **i = 2** | `min = min(2, 4) = 2`<br>`minRight[2] = 2` | Minimum stays 2. |
| **i = 1** | `min = min(2, 1) = 1`<br>`minRight[1] = 1` | New smaller value found. |
| **i = 0** | `min = min(1, 3) = 1`<br>`minRight[0] = 1` | Final `minRight` array: **[1, 1, 2, 2, 5]** |
| **Final pass – evaluate condition** | `ans = -1` initially | Scan from left to right. |
| **i = 0** | `score = maxLeft[0] - minRight[0] = 3 - 1 = 2`<br>`score ≤ k` → `ans = 0`, break | The first index already satisfies the requirement. |
| **Return** | `return 0` | The algorithm stops after finding the earliest stable index. |

**Result:** the first stable index is `0`.

If we change `k` to `1`, the algorithm would continue the final pass:

| i | score | condition |
|---|-------|-----------|
|0|2|>1 → continue|
|1|3‑1 = 2|>1|
|2|4‑2 = 2|>1|
|3|4‑2 = 2|>1|
|4|5‑5 = 0|≤1 → `ans = 4`|

The answer would be `4` in that scenario.

---

## 3. Complexity Analysis  

| metric | bound | justification |
|--------|-------|----------------|
| **Time complexity** | **O(n)** | Three separate linear traversals over the array of length `n`. All inner operations are constant‑time (`Math.max`, `Math.min`, assignments). |
| **Space complexity** | **O(n)** | Two auxiliary integer arrays `maxLeft` and `minRight`, each of size `n`. The algorithm uses only O(1) additional variables besides them. |

The solution therefore meets the optimal linear time requirement for this class of problems while remaining easy to understand and maintain.  

*Keywords: LeetCode, first stable index, Java solution, prefix maximum, suffix minimum, O(n) time, O(n) space, array scanning, stable index problem.*