## Shortest Beautiful Substring – LeetCode Solution  
**Problem recap** – Given a binary string `s` and an integer `k`, a *beautiful* substring is defined as a contiguous segment that contains **exactly** `k` occurrences of `'1'` and **no leading zeros** (the first character of the substring must be `'1'`).  
The task is to return the **shortest** beautiful substring. If several substrings share the minimum length, return the **lexicographically smallest** one. If no such substring exists, return an empty string.

The Java implementation below solves the problem with a **sliding‑window / two‑pointer** technique.

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int[] freq = new int[2];          // freq[0] = #0, freq[1] = #1 in current window
        int n = s.length();
        int low = 0, high = 0;            // window boundaries [low, high]
        int minLen = Integer.MAX_VALUE;   // length of best window found so far
        String str = "";                  // best substring

        while (high < n) {
            int idx = s.charAt(high) - '0';
            freq[idx]++;                  // expand window to the right

            // 1️⃣ keep #1 ≤ k  (remove excess '1's)
            while (freq[1] > k) {
                int lowIdx = s.charAt(low) - '0';
                freq[lowIdx]--;
                low++;
            }

            // 2️⃣ discard leading zeros – the window must start with '1'
            while (s.charAt(low) == '0' && low < high) {
                freq[0]--;
                low++;
            }

            int len = high - low + 1;      // current window length

            // 3️⃣ a candidate window: exactly k ones and starts with '1'
            if (freq[1] == k) {
                if (len < minLen) {
                    minLen = len;
                    str = s.substring(low, high + 1);
                } else if (len == minLen) {
                    // tie‑break by lexicographic order
                    String cand = s.substring(low, high + 1);
                    if (str.isEmpty() || str.compareTo(cand) > 0) {
                        str = cand;
                    }
                }
            }
            high++;                         // move right pointer forward
        }
        return str;
    }
}
```

---

## 1️⃣ Intuition & Approach  

### Core idea  
The problem asks for a **minimum‑length** window that satisfies two constraints:

1. **Exactly `k` ones** – the count of `'1'` characters inside the window must be `k`.
2. **No leading zeros** – the leftmost character of the window must be `'1'`.

Both constraints are *monotonic* with respect to window expansion:

* Adding characters on the right can only increase the number of `'1'`s.
* Removing characters from the left can only decrease the number of `'1'`s or drop leading zeros.

These properties make the classic **sliding window** (two‑pointer) technique a perfect fit:

* **`high`** – expands the window to the right, one character at a time.
* **`low`** – contracts the window from the left whenever a constraint is violated.

### Step‑by‑step workflow  

| Phase | Action | Reason |
|------|--------|--------|
| **Expand** | `high` moves right, `freq[char]++` | Bring new characters into the window. |
| **Shrink excess `1`s** | While `freq[1] > k`, move `low` right, decrement the corresponding frequency. | Guarantees `#1 ≤ k`. |
| **Drop leading zeros** | While the character at `low` is `'0'` **and** `low < high`, move `low` right, decrement `freq[0]`. | Enforces the “no leading zero” rule. |
| **Check candidate** | If `freq[1] == k`, the window is beautiful. Compare its length (and lexicographic order) with the best answer so far. | Updates the optimal substring. |
| **Iterate** | Increment `high` and repeat until the end of the string. | Guarantees every possible window is examined exactly once. |

Because each index is moved **at most once** by either pointer, the algorithm runs in linear time.

### Why the tie‑break works  

When two windows have the same minimal length, the problem requires the lexicographically smallest substring.  
`String.compareTo` returns a negative value if the left operand is smaller.  
The code therefore keeps the smaller of the current best (`str`) and the new candidate (`s.substring(low, high+1)`).  

---

## 2️⃣ Dry Run  

### Example  
*Input*: `s = "0011010"`, `k = 2`  

| step | high | low | char added (`s[high]`) | freq[0] | freq[1] | window (`s[low..high]`) | action taken | candidate? | best (`str`) |
|------|------|-----|-----------------------|--------|--------|------------------------|--------------|------------|--------------|
| 0 (init) | 0 | 0 | – | 0 | 0 | – | – | – | `""` |
| 1 | 0 | 0 | `'0'` → idx = 0 | 1 | 0 | `"0"` | No shrink (freq[1]≤k). Leading‑zero loop **not** executed because `low == high`. | `freq[1]≠k` | `""` |
| 2 | 1 | 0 | `'0'` → idx = 0 | 2 | 0 | `"00"` | Same as above. | `freq[1]≠k` | `""` |
| 3 | 2 | 0 | `'1'` → idx = 1 | 2 | 1 | `"001"` | `freq[1]≤k`. Leading‑zero loop: `s[low]=='0'` → drop → `low=1`, `freq[0]=1`. Still `'0'` at `low` → drop → `low=2`, `freq[0]=0`. Window becomes `"1"`. | `freq[1]≠k` | `""` |
| 4 | 3 | 2 | `'1'` → idx = 1 | 0 | 2 | `"11"` | `freq[1]==k`. No leading zero (first char `'1'`). Length = 2 → **new best**. | Yes | `str = "11"` |
| 5 | 4 | 2 | `'0'` → idx = 0 | 1 | 2 | `"110"` | `freq[1]==k`. Leading‑zero loop not triggered (`s[low]=='1'`). Length = 3 > `minLen` (2) → ignore. | No | `str = "11"` |
| 6 | 5 | 2 | `'1'` → idx = 1 | 1 | 3 | `"1101"` | `freq[1] > k` → shrink: `s[low]='1'` → `low=3`, `freq[1]=2`. Now window `"101"`. Leading‑zero loop not needed (`s[low]=='1'`). Length = 3 > `minLen`. | No | `str = "11"` |
| 7 | 6 | 3 | `'0'` → idx = 0 | 2 | 2 | `"010"` | `freq[1]==k`. Leading‑zero loop: `s[low]=='0'` and `low<high` → drop → `low=4`, `freq[0]=1`. Window becomes `"10"`. Length = 2 = `minLen`. Compare lexicographically: `"11"` vs `"10"` → `"10"` is smaller, so **update**. | Yes | `str = "10"` |
| end | – | – | – | – | – | – | Return `str` | – | **Result = "10"`** |

The algorithm correctly returns `"10"` – the shortest beautiful substring of length 2, and among length‑2 candidates it is lexicographically smallest.

---

## 3️⃣ Complexity Analysis  

| Metric | Reason |
|--------|--------|
| **Time** | Each character is visited at most twice: once by `high` (expansion) and once by `low` (contraction). All inner `while` loops together move `low` monotonically forward, never backward. Hence **O(n)** where `n = s.length()`. |
| **Space** | Only a constant‑size frequency array (`int[2]`) and a few primitive variables are used, plus the output string (which is required by the problem). Extra auxiliary space is **O(1)**. |

---

## 4️⃣ Key Takeaways  

* The **sliding window** pattern efficiently handles “exact count” constraints when the count can be maintained incrementally.
* Removing **leading zeros** after the count constraint is satisfied guarantees the substring starts with `'1'` without extra scans.
* Maintaining the **lexicographically smallest** answer during the scan avoids a second pass.
* The solution runs in linear time and constant extra space, making it optimal for the given constraints.

**SEO keywords**: shortest beautiful substring, sliding window, two pointers, Java solution, LeetCode, O(n) time, O(1) space, binary string, exact k ones, lexicographically smallest substring.