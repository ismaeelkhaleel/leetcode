## 1. Intuition & Approach  

The problem asks for the **lexicographically smallest permutation of `s` that is strictly greater than `target`**.  
If no such permutation exists, the answer must be an empty string.

The solution works with two classic ideas:

| Idea | Why it helps |
|------|--------------|
| **Frequency array** (`int[26]`) | Gives O(1) access to the remaining count of each character from `s`. It eliminates the need for costly list/heap operations. |
| **Greedy left‑to‑right scan** | While scanning `target`, we try to keep the prefix identical as long as possible. The first position where we cannot match `target[i]` is the place where we must increase the character and then fill the rest with the smallest possible letters. |

The algorithm can be divided into three logical phases:

1. **Build the frequency table** of all characters in `s`.  
2. **Iterate over `target`** from left to right.  
   * If the current character `target[i]` is still available in the frequency table, consume it and continue.  
   * If it is **not** available, we have two possibilities:  
     * **Increase at the current index** – pick the smallest character larger than `target[i]` that is still unused, then dump the remaining characters in ascending order.  
     * **Backtrack** – the current index cannot be increased, so we retreat to the previous positions (i‑1, i‑2, …) and try to increase the first position where a larger character is still available. While backtracking we restore the character that we previously “used” (because it becomes free again).  
3. **All characters matched** – the whole `target` can be formed from `s`. In this case we need the *next* permutation of `target`. The same back‑track logic applied from the rightmost side yields the answer.

If none of the above steps succeed, the function returns `""`.

The core routine that builds the answer after a successful increase is identical in all three branches:

```java
StringBuilder ans = new StringBuilder(prefix);
ans.append(chosenChar);          // the first character that makes the string larger
freq[chosenChar]--;              // consume it
for (int x = 0; x < 26; x++) {  // append the rest in sorted order
    while (freq[x] > 0) {
        ans.append((char)('a' + x));
        freq[x]--;
    }
}
return ans.toString();
```

Because the characters are appended in ascending order, the resulting string is the **lexicographically smallest** string that satisfies the “greater than `target`” condition.

---

## 2. Dry Run  

### Example  
* `s = "abac"`  
* `target = "aacb"`  

Goal: smallest permutation of `"abac"` that is > `"aacb"`.

#### Step‑by‑step execution  

| Step | Variable | Action | Explanation |
|------|----------|--------|-------------|
| 0 | `freq = [0…0]` | Build frequency from `s` | `'a'` appears 2 → `freq[0]=2`; `'b'` appears 1 → `freq[1]=1`; `'c'` appears 1 → `freq[2]=1`. |
| 1 | `i = 0` | `idx = target[0] - 'a' = 0` (`'a'`) | `freq[0] > 0` → consume one `'a'`. `freq[0]` becomes 1. |
| 2 | `i = 1` | `idx = target[1] - 'a' = 0` (`'a'`) | `freq[0] > 0` → consume second `'a'`. `freq[0]` becomes 0. |
| 3 | `i = 2` | `idx = target[2] - 'a' = 2` (`'c'`) | `freq[2] > 0` → consume `'c'`. `freq[2]` becomes 0. |
| 4 | `i = 3` | `idx = target[3] - 'a' = 1` (`'b'`) | `freq[1] > 0` → consume `'b'`. `freq[1]` becomes 0. |
| 5 | End of loop (`i == n`) | All characters of `target` were matched. | We must find the **next permutation** of `target`. |
| 6 | Backtrack loop (`j = 3 → 0`) | Start from the rightmost position. |
| 6‑a | `j = 3` (`target[3] = 'b'`) | Restore `'b'` → `freq[1] = 1`. Try to pick a larger character (`k = 2…25`). `freq[2] = 0`, … no larger char available. |
| 6‑b | `j = 2` (`target[2] = 'c'`) | Restore `'c'` → `freq[2] = 1`. Try larger chars (`k = 3…`). None exist. |
| 6‑c | `j = 1` (`target[1] = 'a'`) | Restore `'a'` → `freq[0] = 1`. Look for `k = 1` (`'b'`) – `freq[1] = 1` → **found**. |
| 7 | Build answer | `prefix = target.substring(0,1) = "a"`<br>`chosenChar = 'b'`<br>Decrease `freq[1]` → `freq[1]=0`<br>Append remaining chars in order: `freq[0]=1` → `'a'`, `freq[2]=1` → `'c'`. | Result string = `"abac"` |
| 8 | Return | `"abac"` is lexicographically greater than `"aacb"` and is the smallest possible. | ✅ |

**Result:** `"abac"`.

---

### Example that triggers the *increase‑at‑current‑index* branch  

* `s = "abc"`  
* `target = "acb"`  

| Step | Variable | Action |
|------|----------|--------|
| Build `freq`: `a:1, b:1, c:1` |
| `i = 0` → `target[0] = 'a'` → consume `'a'` (`freq[a]=0`) |
| `i = 1` → `target[1] = 'c'` → `freq[c]=1` → consume `'c'` (`freq[c]=0`) |
| `i = 2` → `target[2] = 'b'` → `freq[b]=1` → consume `'b'` (`freq[b]=0`) |
| End of loop → all matched → try next permutation (backtrack). |
| Backtrack `j = 2` (`'b'`): restore `'b'` (`freq[b]=1`). Look for a larger char (`k = 2…`). `k = 2` (`'c'`) has `freq[c]=0`. No larger char. |
| `j = 1` (`'c'`): restore `'c'` (`freq[c]=1`). Look for larger (`k = 3…`). None. |
| `j = 0` (`'a'`): restore `'a'` (`freq[a]=1`). Look for larger (`k = 1` → `'b'` has `freq[b]=1`). **Found**. |
| Build answer: prefix `""`, chosen `'b'`, remaining sorted → `'a'` then `'c'`. Result `"bac"`. |

**Result:** `"bac"` (the smallest permutation of `"abc"` that is > `"acb"`).

---

## 3. Complexity Analysis  

| Metric | Value | Reason |
|--------|-------|--------|
| **Time** | **O(n + 26·n)** → **O(n)** | *Building the frequency array* is O(n). The outer scan over `target` is O(n). Inside each iteration we may scan the alphabet (max 26) a constant number of times (once for “increase” and possibly once during backtrack). The final “append remaining characters” loop also iterates over 26 letters, each character is appended exactly once. Hence the total work is linear in the length of the strings. |
| **Space** | **O(26) = O(1)** | Only the frequency array of size 26 and a few auxiliary variables are used, independent of input size. The `StringBuilder` holds the output string, which is required by the problem definition. |

The algorithm therefore satisfies the optimal linear‑time, constant‑extra‑space constraints expected for this class of permutation problems.