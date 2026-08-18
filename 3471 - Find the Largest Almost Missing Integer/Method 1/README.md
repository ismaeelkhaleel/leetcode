## LeetCode – `largestInteger`  
**Problem pattern** – “find the largest integer that satisfies a uniqueness condition under a given `k`”.  
The reference implementation uses a **hash‑map** to count frequencies, then applies a handful of case‑by‑case rules. The following sections break down the solution, walk through a concrete example, and analyse its asymptotic behaviour.

---

## 1. Intuition & Approach  

### Core idea  

1. **Frequency table** – The algorithm first scans the whole array once and stores the occurrence count of every value in a `HashMap<Integer,Integer>`.  
2. **Global maximum** – While building the map it also tracks the maximum element (`maxNum`). This value is needed when `k` equals the length of the array.  
3. **Special‑case handling** – The value of `k` determines which rule is applied:  

| `k` value | What the code does | Reasoning |
|-----------|-------------------|-----------|
| `k == 1` | Return the **largest element that appears exactly once** in the *entire* array. | With a single‑element window the only candidates are the globally unique numbers. |
| `k == nums.length` | Return `maxNum` (the overall largest element). | When the window covers the whole array, the answer is simply the global maximum, regardless of uniqueness. |
| otherwise | Examine only the **first** and **last** elements of the array. If either of them is unique (frequency = 1) return the appropriate value; otherwise return `-1`. | The original problem statement (as inferred from the code) restricts the answer to the boundary elements when the window size is neither 1 nor the full length. |

### Step‑by‑step algorithm  

```text
1. Build a frequency map:
   for each num in nums:
       map[num] = map.getOrDefault(num, 0) + 1
       maxNum = max(maxNum, num)

2. If k == 1:
       ans = -1
       for each (value, count) in map:
           if count == 1:
               ans = max(ans, value)
       return ans

3. If k == nums.length:
       return maxNum

4. Determine uniqueness of the two boundary elements:
       firstUnique = (map.get(nums[0]) == 1)
       lastUnique  = (map.get(nums[nums.length‑1]) == 1)

5. Return according to the following priority:
       - both unique → max(nums[0], nums[last])
       - only first unique → nums[0]
       - only last  unique → nums[last]
       - none unique → -1
```

The algorithm never modifies the input array; it only reads it and stores constant‑size auxiliary data (the hash‑map).

---

## 2. Dry Run  

Consider the following test case:

```java
int[] nums = {2, 3, 2, 5, 3};
int k = 1;
```

We will trace the execution line by line.

| Step | Action | `map` (value → count) | `maxNum` | Variables (`firstUnique`, `lastUnique`, `ans`) |
|------|--------|----------------------|----------|-----------------------------------------------|
| 1    | Initialise `map = {}` , `maxNum = nums[0] = 2` | `{}` | `2` | – |
| 2    | Process `num = 2` | `{2:1}` | `max(2,2)=2` | – |
| 3    | Process `num = 3` | `{2:1, 3:1}` | `max(2,3)=3` | – |
| 4    | Process `num = 2` | `{2:2, 3:1}` | `max(3,2)=3` | – |
| 5    | Process `num = 5` | `{2:2, 3:1, 5:1}` | `max(3,5)=5` | – |
| 6    | Process `num = 3` | `{2:2, 3:2, 5:1}` | `max(5,3)=5` | – |
| 7    | End of first loop → `map = {2:2, 3:2, 5:1}`, `maxNum = 5` | — | — | — |
| 8    | `k == 1` → enter the *k‑equals‑1* branch | — | — | `ans = -1` |
| 9    | Iterate over map entries: <br>• (2,2) → count ≠ 1 → ignore <br>• (3,2) → count ≠ 1 → ignore <br>• (5,1) → count == 1 → `ans = max(-1,5) = 5` | — | — | `ans = 5` |
|10    | Return `ans` → **5** | — | — | — |

**Result:** `largestInteger(nums, 1)` returns `5`, the largest globally unique element.

---

### Additional scenario (`k = nums.length`)

```java
int[] nums = {4, 1, 7, 7};
int k = 4;   // equals nums.length
```

* After the first loop the map is `{4:1,1:1,7:2}` and `maxNum = 7`.  
* Because `k == nums.length`, the method returns `maxNum` → **7**.

---

### Scenario where `k` is neither `1` nor `n`

```java
int[] nums = {8, 2, 8, 3};
int k = 2;
```

* Frequency map: `{8:2, 2:1, 3:1}`, `maxNum = 8`.  
* `firstUnique = (map.get(8) == 1) → false`  
* `lastUnique  = (map.get(3) == 1) → true`  

The decision tree yields:

* `firstUnique && lastUnique` → false  
* `firstUnique` → false  
* `lastUnique` → true → return `nums[3] = 3`.

**Result:** `3`.

If both boundary elements were non‑unique, the method would fall through to `return -1`.

---

## 3. Complexity Analysis  

| Metric | Reason |
|--------|--------|
| **Time Complexity** | The algorithm performs a single linear pass to build the frequency map (`O(n)`). All subsequent branches iterate over at most the map entries once (still `O(n)` in the worst case when `k == 1`). Hence overall time is **`O(n)`**, where `n = nums.length`. |
| **Space Complexity** | The hash‑map stores one entry per distinct value. In the worst case every element is unique, requiring `O(n)` extra space. Apart from the map, only a few primitive variables are used (`O(1)`). Thus the total auxiliary space is **`O(n)`**. |

---

## 4. Takeaways  

* A **frequency map** is the go‑to tool when a problem asks about “unique” or “duplicate” elements.  
* Handling **edge cases** (`k == 1` and `k == n`) up front simplifies the main logic and avoids unnecessary condition checks later.  
* When the specification limits the answer to *boundary* elements, a direct lookup (`map.get(value)`) is sufficient—no need for sliding‑window or two‑pointer techniques.  

The presented solution is concise, leverages Java’s `HashMap` for constant‑time look‑ups, and runs in linear time with linear extra memory—characteristics that align well with typical LeetCode performance expectations.