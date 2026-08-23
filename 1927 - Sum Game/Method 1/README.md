## 1. Intuition & Approach  

The problem is the **LeetCode “Sum Game”** (LC 1927).  
We are given an even‑length string `num` that consists of digits (`'0'`‑`'9'`) and the placeholder character `'?'`.  

* The first half of the string belongs to **Alice** and the second half to **Bob**.  
* In each turn a player replaces one `'?'` with any digit `0…9`.  
* After all `'?'` are replaced, the game is **won** by Alice if the sum of the digits in the left half **differs** from the sum in the right half; otherwise Bob wins.

The task is to decide, **assuming optimal play**, whether Alice can force a win.

### Core observation  

Only the **difference** between the two halves matters, not the individual sums.  
Define  

```
diff = (sum of known digits in left half) – (sum of known digits in right half)
```

*If `diff` is already non‑zero and the number of unknown positions on both sides is equal, Alice can never make the sums equal → she wins.*

When the counts of `'?'` on the two sides differ, the player who controls the side with **more** unknowns can manipulate the final difference more strongly.  
Each `'?'` can contribute at most `9` to the side that owns it (by choosing digit `9`). Therefore the maximal swing that the side with more `'?'` can create is  

```
maxSwing = 9 * (|lq – rq|) / 2
```

(the division by 2 appears because the extra `'?'` are split evenly between the two players; the player who moves first can always allocate the larger half of the swing to his side).

From this we obtain three mutually exclusive cases:

| Condition | Reasoning | Result |
|-----------|-----------|--------|
| `lq == rq` | Both sides have the same number of moves. The only way to change the final difference is the already known digits. If `diff != 0` Alice already wins; otherwise Bob can mirror every move and force equality. | `return diff != 0` |
| `|lq – rq|` is **odd** | The player with the extra `'?'` gets an unpaired move that cannot be mirrored. That move can always be used to break equality, regardless of the current `diff`. | `return true` |
| `|lq – rq|` is **even** and non‑zero | Both sides have the same number of *effective* moves after the extra unpaired move is accounted for. The side with more `'?'` can add (or subtract) the maximal swing `ad = 9 * |lq – rq| / 2` to the current `diff`. If after applying this swing the difference can still be forced to zero, Bob can win; otherwise Alice wins. | `return diff != 0` after adjusting `diff` by `ad` |

The implementation follows exactly this reasoning:

1. Scan the left half → count `lq` (left question marks) and accumulate `diff` with `+digit`.  
2. Scan the right half → count `rq` (right question marks) and accumulate `diff` with `‑digit`.  
3. Apply the three cases described above.

---

## 2. Dry Run  

Take the example  

```
num = "1?2?3?"
```

Length `n = 6` (even).  
Indices: `0 1 2 | 3 4 5` → left half = `"1?2"`, right half = `"?3?"`.

| Step | Variable | Action | New value | Explanation |
|------|----------|--------|-----------|-------------|
| 1 | `lq = 0, rq = 0, d = 0` | initialization | `0,0,0` | start |
| 2 | `i = 0` (left) | `ch = '1'` → digit | `d += 1` | `d = 1` |
| 3 | `i = 1` (left) | `ch = '?'` → unknown | `lq++` | `lq = 1` |
| 4 | `i = 2` (left) | `ch = '2'` → digit | `d += 2` | `d = 3` |
| 5 | `i = 3` (right) | `ch = '?'` → unknown | `rq++` | `rq = 1` |
| 6 | `i = 4` (right) | `ch = '3'` → digit | `d -= 3` | `d = 0` |
| 7 | `i = 5` (right) | `ch = '?'` → unknown | `rq++` | `rq = 2` |
| 8 | End of loops | `lq = 1, rq = 2, d = 0` | – | left side has 1 `'?'`, right side 2 `'?'`, known digit difference is zero |
| 9 | Check `lq == rq`? → `false` | – | – | move to next case |
|10| `|lq‑rq| = 1` → odd? → `true` | – | – | Because the right side has one extra `'?'`, Alice (who moves first) can always pick a digit that makes the sums unequal. |
|11| Return `true` | – | – | Alice can force a win. |

**Result:** `sumGame("1?2?3?")` returns `true`.

### Another example where the swing matters  

```
num = "??00??"
```

*Left half*: `"??0"` → `lq = 2`, known digit `0` → `d = 0`.  
*Right half*: `"0??"` → `rq = 2`, known digit `0` → `d = 0`.

After scanning: `lq = 2, rq = 2, d = 0`.

*Case `lq == rq`* → `return d != 0` → `false`.  
Bob can mirror Alice’s moves and force equality, so Alice cannot win.

If we change one known digit:

```
num = "?5?0??"
```

Scanning yields `lq = 2, rq = 3, d = 5` (left known digit `5`, right known digit `0`).  

`|lq‑rq| = 1` → odd → Alice wins (`true`).  

If the difference in question‑mark counts is even but non‑zero, the algorithm computes the maximal swing:

```
num = "??5?0?"
```

Scanning: `lq = 2, rq = 2, d = 5` (left known `5`, right known `0`).  
`lq == rq` → `return d != 0` → `true`.  

Now a case with even non‑zero difference:

```
num = "??5??0"
```

Scanning: left half `"??5"` → `lq = 2, d = 5`.  
Right half `"??0"` → `rq = 2, d = 5 - 0 = 5`.  

`lq == rq` → `return d != 0` → `true`.  

Finally, a case where `lq` and `rq` differ by **2** (even):

```
num = "?5??0?"
```

Scanning:  
- left half `" ?5?"` → `lq = 2`, `d = 5`.  
- right half `" ?0?"` → `rq = 2`, `d = 5 - 0 = 5`.  

`lq == rq` → `true`.  

To see the swing, use:

```
num = "?5???0"
```

Scanning:  
- left half `" ?5?"` → `lq = 2`, `d = 5`.  
- right half `" ??0"` → `rq = 3`, `d = 5 - 0 = 5`.  

Now `|lq‑rq| = 1` (odd) → Alice wins.  

If we make the difference **2** (even):

```
num = "?5????0"
```

Scanning:  
- left half `" ?5?"` → `lq = 2`, `d = 5`.  
- right half `" ???0"` → `rq = 4`, `d = 5 - 0 = 5`.  

`|lq‑rq| = 2` (even, non‑zero).  

```
ad = 9 * |lq‑rq| / 2 = 9 * 2 / 2 = 9
```

Since `rq > lq`, we subtract the swing:

```
d -= ad   →   d = 5 - 9 = -4
```

`d != 0` → Alice still wins.  
If `d` had become `0`, the algorithm would return `false`, meaning Bob could force equality.

---

## 3. Complexity Analysis  

| Metric | Value | Reason |
|--------|-------|--------|
| **Time Complexity** | **O(n)** | The string is traversed once (two linear passes over the two halves). All other operations are O(1). |
| **Space Complexity** | **O(1)** | Only a few integer counters (`lq`, `rq`, `d`) are stored; no auxiliary data structures proportional to `n`. |

The solution therefore runs in linear time with constant extra memory, satisfying the constraints of the LeetCode “Sum Game” problem.