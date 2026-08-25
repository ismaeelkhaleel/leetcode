## 1. Intuition & Approach  

The task is to find the **smallest positive multiple of `k` that does not appear in the array `nums`**.  
A direct way to answer the query is:

1. **Enable O(1) existence checks** – put every element of `nums` into a hash‑based container (`HashSet`).  
2. **Generate multiples of `k` sequentially** – start with `k`, then `2·k`, `3·k`, …  
3. **Stop at the first multiple that is absent from the set** – that value is the answer.

Because a hash set provides constant‑time `contains` operations, the loop that scans the multiples runs in linear time with respect to the number of present multiples, not with respect to the size of the original array. The overall algorithm therefore consists of a single linear pass to build the set and a second linear pass over the (potentially much smaller) sequence of multiples.

The solution is implemented in Java as:

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {
        // 1️⃣ Build a hash set for O(1) look‑ups
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 2️⃣ Scan multiples of k until one is missing
        int temp = k;
        while (set.contains(temp)) {
            temp += k;
        }

        // 3️⃣ Return the first absent multiple
        return temp;
    }
}
```

Key points that make the approach efficient:

- **HashSet** eliminates the need for sorting or binary search.  
- The loop increments by `k`, guaranteeing that every examined value is a multiple of `k`.  
- As soon as a missing multiple is found, the algorithm terminates, avoiding unnecessary work.

---

## 2. Dry Run  

Consider a simple example:

```text
nums = [2, 4, 8]   // array of integers
k    = 2           // we look for the smallest missing multiple of 2
```

### Step‑by‑step execution

| Step | Action | `set` after action | `temp` (current multiple) | Condition `set.contains(temp)` | Decision |
|------|--------|--------------------|---------------------------|--------------------------------|----------|
| 1    | Build `HashSet` from `nums` | `{2, 4, 8}` | – | – | – |
| 2    | Initialize `temp = k` | `{2, 4, 8}` | `2` | `true` (2 ∈ set) | Enter `while` loop |
| 3    | Inside loop: `temp += k` | `{2, 4, 8}` | `4` | `true` (4 ∈ set) | Continue |
| 4    | Inside loop: `temp += k` | `{2, 4, 8}` | `6` | `false` (6 ∉ set) | Exit loop |
| 5    | Return `temp` | – | `6` | – | Result = **6** |

**Explanation of each iteration**

- **Iteration 1** – `temp = 2`. The set contains `2`, so the algorithm proceeds to the next multiple.
- **Iteration 2** – `temp = 4`. The set contains `4`, continue.
- **Iteration 3** – `temp = 6`. The set does **not** contain `6`; the loop stops and `6` is returned.

Thus, the smallest missing multiple of `2` in the given array is `6`.

### Another example (all early multiples present)

```text
nums = [3, 6, 9, 12]
k    = 3
```

| Step | `temp` | `set.contains(temp)` | Action |
|------|--------|----------------------|--------|
| Init | 3      | true                 | `temp = 6` |
| 1    | 6      | true                 | `temp = 9` |
| 2    | 9      | true                 | `temp = 12` |
| 3    | 12     | true                 | `temp = 15` |
| 4    | 15     | false                | exit loop, return 15 |

Result: **15**.

These dry runs illustrate how the algorithm incrementally checks each multiple of `k` and stops exactly at the first absent one.

---

## 3. Complexity Analysis  

| Metric | Reasoning |
|--------|-----------|
| **Time Complexity** | • Building the `HashSet` scans the input once: **O(n)** where `n = nums.length`. <br>• The `while` loop iterates once per present multiple of `k`. In the worst case the array contains the first `m` multiples of `k`, so the loop runs `m` times. Since `m ≤ n`, the total time remains **O(n)**. |
| **Space Complexity** | The hash set stores every distinct element of `nums`, requiring **O(n)** extra space. No additional structures proportional to the value range are created. |

Overall, the solution runs in linear time and linear auxiliary space, making it optimal for the problem constraints.

---

### SEO Keywords  

LeetCode solution, Java HashSet, missing multiple, smallest missing multiple, time complexity O(n), space complexity O(n), linear scan, constant‑time lookup, algorithm walkthrough, dry run example.