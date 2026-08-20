## 1. Intuition & Approach  

The problem asks for the **maximum number of 4‑person families** that can be seated in a cinema with `n` rows, each row containing seats `1 … 10`.  
A family can occupy any of the three contiguous blocks  

* **left block**   seats `2‑5`  
* **middle block** seats `4‑7`  
* **right block**  seats `6‑9`  

Only rows that contain *reserved* seats need special handling – rows without any reservation can always accommodate **two** families (left + right).  

The algorithm therefore works in two phases:

1. **Group reserved seats by row** – a `Map<Integer, Set<Integer>>` where the key is the row index and the value is the set of occupied seat numbers in that row.  
2. **Count families row by row**  
   * For rows **absent** from the map (`n - map.size()` rows) add `2` families each.  
   * For each row present in the map, examine its reserved seats and determine which of the three blocks are still free:  
     * If a reserved seat lies in `2‑5` → the **left** block becomes unavailable.  
     * If a reserved seat lies in `4‑7` → the **middle** block becomes unavailable.  
     * If a reserved seat lies in `6‑9` → the **right** block becomes unavailable.  
   * After scanning all seats of the row:  
     * If **both** left **and** right are free → add `2` families.  
     * Otherwise, if **any** of the three blocks is free → add `1` family.  
     * If none are free → add `0`.  

The core idea is that only the three overlapping intervals matter; any seat outside `2‑9` never influences the answer.

---

## 2. Dry Run  

Consider a small instance:

```
n = 3
reservedSeats = [[1,2], [1,8], [2,5]]
```

### Step‑by‑step execution

| Step | Action | Variables / State |
|------|--------|-------------------|
| 1 | Create empty `map` | `{}` |
| 2 | Process `[1,2]` → row 1, seat 2 | `map = {1 → {2}}` |
| 3 | Process `[1,8]` → row 1, seat 8 | `map = {1 → {2,8}}` |
| 4 | Process `[2,5]` → row 2, seat 5 | `map = {1 → {2,8}, 2 → {5}}` |
| 5 | Compute families from completely empty rows: `n - map.size() = 3 - 2 = 1` → `count = 1 * 2 = 2` | `count = 2` |
| 6 | Iterate over `map.values()` – first row = `{2,8}` | `left = true, middle = true, right = true` |
| 7 | Seat `2` falls in `2‑5` → `left = false` | `left = false` |
| 8 | Seat `2` does **not** affect middle/right | unchanged |
| 9 | Seat `8` falls in `6‑9` → `right = false` | `right = false` |
|10 | After scanning row 1: `left = false, middle = true, right = false`<br>Only **middle** is free → `count += 1` | `count = 3` |
|11 | Next row = `{5}` (row 2) | reset `left = true, middle = true, right = true` |
|12 | Seat `5` lies in `2‑5` → `left = false` | `left = false` |
|13 | Seat `5` also lies in `4‑7` → `middle = false` | `middle = false` |
|14 | Seat `5` does **not** affect right | unchanged |
|15 | After scanning row 2: `left = false, middle = false, right = true`<br>Only **right** is free → `count += 1` | `count = 4` |
|16 | No more rows in the map. Return `count`. | **Result = 4** |

**Interpretation**  

* Row 3 (no reservations) contributes `2` families.  
* Row 1 can host only the middle block → `1` family.  
* Row 2 can host only the right block → `1` family.  

Total = `2 + 1 + 1 = 4` families, which matches the algorithm’s output.

---

## 3. Complexity Analysis  

| Metric | Evaluation |
|--------|------------|
| **Time Complexity** | `O(R + C)` where `R = reservedSeats.length` and `C` is the total number of reserved seats examined inside the inner loop. The first pass builds the map in `O(R)`. The second pass iterates over each row that has reservations and, for each row, scans its set of seats once – overall `O(R)`. Hence the overall time is linear in the size of the input. |
| **Space Complexity** | `O(R)` for the map that stores at most one entry per row containing reservations, and each entry holds a `HashSet` of the seats in that row. No additional structures proportional to `n` are allocated. |

The solution therefore runs in **linear time** and uses **linear extra space** relative to the number of reserved seats, which satisfies the constraints of the LeetCode problem.