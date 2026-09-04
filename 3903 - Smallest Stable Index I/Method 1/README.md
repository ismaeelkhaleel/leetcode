## 1. Intuition & Approach  

The task is to locate the **first stable index** in an integer array `nums`.  
An index `i` is *stable* when the difference between the **maximum value on its left side (including `i`)** and the **minimum value on its right side (including `i`)** does not exceed a given threshold `k`.

Formally  

```
stable(i) ⇔  max(nums[0 … i]) – min(nums[i … n‑1]) ≤ k
```

The naïve way would be to recompute the maximum and minimum for every index, leading to an O(n²) solution.  
The key observation is that both the left‑side maximum and the right‑side minimum can be pre‑computed in linear time:

* **Prefix maximum** – scanning from left to right, keep the running maximum.  
  `maxLeft[i] = max(nums[0], … , nums[i])`

* **Suffix minimum** – scanning from right to left, keep the running minimum.  
  `minRight[i] = min(nums[i], … , nums[n‑1])`

With these two auxiliary arrays, the stability condition for any index `i` can be evaluated in O(1).  
A final linear pass finds the smallest `i` that satisfies the condition; if none exists, `-1` is returned.

The algorithm therefore consists of three linear passes:

1. Build `maxLeft` (prefix maximum).  
2. Build `minRight` (suffix minimum).  
3. Scan from left to right, compute `score = maxLeft[i] - minRight[i]`, and return the first `i` with `score ≤ k`.

All operations are elementary integer comparisons, making the solution optimal for the problem constraints.

---

## 2. Dry Run  

Consider a concrete example:

```java
int[] nums = {3, 1, 4, 2, 5};
int k = 2;
```

We will trace the three phases of the algorithm.

### Phase 1 – Build `maxLeft`

| i | nums[i] | current `max` | maxLeft[i] |
|---|---------|---------------|------------|
| 0 | 3       | max(3, 3) = 3 | 3          |
| 1 | 1       | max(3, 1) = 3 | 3          |
| 2 | 4       | max(3, 4) = 4 | 4          |
| 3 | 2       | max(4, 2) = 4 | 4          |
| 4 | 5       | max(4, 5) = 5 | 5          |

Resulting `maxLeft` array: **[3, 3, 4, 4, 5]**

### Phase 2 – Build `minRight`

| i (reverse) | nums[i] | current `min` | minRight[i] |
|-------------|---------|---------------|-------------|
| 4           | 5       | min(5,5)=5    | 5           |
| 3           | 2       | min(5,2)=2    | 2           |
| 2           | 4       | min(2,4)=2    | 2           |
| 1           | 1       | min(2,1)=1    | 1           |
| 0           | 3       | min(1,3)=1    | 1           |

Resulting `minRight` array: **[1, 1, 2, 2, 5]**

### Phase 3 – Find the first stable index

Iterate `i` from `0` to `n‑1` and compute `score = maxLeft[i] - minRight[i]`.

| i | maxLeft[i] | minRight[i] | score = maxLeft[i] - minRight[i] | score ≤ k? |
|---|------------|-------------|-----------------------------------|------------|
| 0 | 3          | 1           | 2                                 | **yes**    |
| 1 | 3          | 1           | 2                                 | (not reached) |
| 2 | 4          | 2           | 2                                 | (not reached) |
| … | …          | …           | …                                 | …          |

The first index where `score ≤ 2` is `i = 0`.  
The algorithm stores `ans = 0` and breaks out of the loop, returning `0`.

If no index satisfied the condition, `ans` would stay `-1`, which is the required “not found” sentinel.

---

## 3. Complexity Analysis  

| Aspect          | Reasoning |
|-----------------|-----------|
| **Time**        | Three independent linear scans over the array of length `n`. Each scan performs O(1) work per element. Total time = **O(n)**. |
| **Space**       | Two auxiliary integer arrays `maxLeft` and `minRight`, each of size `n`. No additional data structures of larger order are used. Total extra space = **O(n)**. |

The solution meets the optimal linear‑time requirement for this class of problems while keeping the implementation straightforward and easy to maintain.