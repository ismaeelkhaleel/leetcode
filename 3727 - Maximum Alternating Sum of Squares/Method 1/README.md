## 1. Intuition & Approach  

The problem asks for the **maximum alternating sum of squares** of an integer array `nums`.  
For an ordering `p₀, p₁, …, pₙ₋₁` the score is  

\[
\text{score}= -p_0^2 - p_1^2 - \dots - p_{k-1}^2 + p_k^2 + p_{k+1}^2 + \dots + p_{n-1}^2,
\]

where the first half of the elements are subtracted and the second half are added.  
Because the sign of each term depends only on its **position** (first half → negative, second half → positive), the only freedom we have is to **re‑order** the numbers.

Two observations simplify the problem dramatically:

1. **Absolute values matter, not the original sign** – squaring eliminates the sign, and a negative number can be turned positive without affecting the final score. Therefore we can safely replace every element by its absolute value.

2. **Larger absolute values should receive the positive sign** – to maximize the sum, we want the biggest squares to be added and the smallest squares to be subtracted. After converting to absolute values, sorting the array in **non‑decreasing** order guarantees that the first `n/2` elements are the smallest and the remaining elements are the largest.

The algorithm follows these steps:

1. Convert each element to its absolute value (`|nums[i]|`).  
2. Sort the transformed array.  
3. Iterate over the first `n/2` elements, subtract their squares from `score`.  
4. Iterate over the remaining elements, add their squares to `score`.  
5. Return `score` as a `long` to avoid overflow.

The solution runs in `O(n log n)` time (the sort) and uses `O(1)` extra space because the transformation and the two linear scans are performed in‑place.

---

## 2. Dry Run  

Consider a small example to illustrate every operation:

```java
int[] nums = { -3, 1, -2, 4 };
```

**Step 0 – Initial state**

| index | value |
|------|-------|
| 0    | -3    |
| 1    | 1     |
| 2    | -2    |
| 3    | 4     |

---

### 2.1 Convert to absolute values  

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] < 0) nums[i] = -nums[i];
}
```

| i | before | after |
|---|--------|-------|
| 0 | -3     | 3     |
| 1 | 1      | 1     |
| 2 | -2     | 2     |
| 3 | 4      | 4     |

Array now: **[3, 1, 2, 4]**

---

### 2.2 Sort the array  

```java
Arrays.sort(nums);
```

Sorted array: **[1, 2, 3, 4]**

---

### 2.3 Compute the score  

`n = 4`, therefore `n/2 = 2`.  
First half (`i = 0, 1`) → subtract squares.  
Second half (`i = 2, 3`) → add squares.

```java
long score = 0;

// subtract part
for (int i = 0; i < nums.length / 2; i++) {
    score -= (long) nums[i] * nums[i];
}

// add part
for (int i = nums.length / 2; i < nums.length; i++) {
    score += (long) nums[i] * nums[i];
}
```

| iteration | i | nums[i] | operation | contribution | score after |
|-----------|---|---------|-----------|--------------|-------------|
| subtract  | 0 | 1       | `-=`      | `-1² = -1`   | `-1`        |
| subtract  | 1 | 2       | `-=`      | `-2² = -4`   | `-5`        |
| add       | 2 | 3       | `+=`      | `+3² = +9`   | `4`         |
| add       | 3 | 4       | `+=`      | `+4² = +16`  | `20`        |

**Final score = 20**.

The algorithm has placed the two largest absolute values (3 and 4) in the positive part and the two smallest (1 and 2) in the negative part, which yields the maximal alternating sum of squares.

---

## 3. Complexity Analysis  

| Aspect          | Analysis |
|-----------------|----------|
| **Time**        | Converting to absolute values – `O(n)`.<br>Sorting – `O(n log n)` (dominant term).<br>Two linear scans – `O(n)`. <br>**Overall:** `O(n log n)`. |
| **Space**       | The transformation and scans modify the input array in place. Apart from a few primitive variables (`score`, loop counters) the algorithm uses constant extra memory. <br>**Overall:** `O(1)` auxiliary space. |

The solution therefore satisfies the typical LeetCode constraints for large inputs, delivering optimal performance while remaining easy to understand and maintain.