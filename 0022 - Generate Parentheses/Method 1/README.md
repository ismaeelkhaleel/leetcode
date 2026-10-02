## 1. Intuition & Approach  

The problem asks for **all** well‑formed strings that contain `n` pairs of parentheses.  
A string is valid when, while scanning from left to right, the number of closing brackets never exceeds the number of opening brackets, and the total count of each type equals `n`.

The natural way to enumerate every valid combination is **backtracking**:

1. Build the string character by character.  
2. Keep two counters:  
   * `open` – how many `'('` have been placed so far.  
   * `close` – how many `')'` have been placed so far.  
3. At any recursion step we have two safe moves:  
   * Add `'('` if we still have opening brackets left (`open < n`).  
   * Add `')'` if it would not break the balance (`close < open`).  
4. When the current string reaches length `2 × n`, a complete, balanced combination is obtained – push it into the result list.

The algorithm explores a binary decision tree whose depth is `2n`. Because the two constraints (`open < n` and `close < open`) prune every branch that would lead to an invalid sequence, only the Catalan number `Cₙ` leaves are produced – exactly the number of distinct well‑formed parentheses strings.

The provided JavaScript implementation follows this scheme:

```javascript
var generateParenthesis = function(n) {
    let res = [];
    generateAll(n, res, "", 0, 0);
    return res;
};

let generateAll = function(n, res, curr, open, close) {
    if (curr.length == 2 * n) {          // 1️⃣ termination condition
        res.push(curr);
        return;
    }
    if (open < n)                         // 2️⃣ try to add '('
        generateAll(n, res, curr + "(", open + 1, close);
    if (close < open)                     // 3️⃣ try to add ')'
        generateAll(n, res, curr + ")", open, close + 1);
}
```

* `curr` – the partial string built so far.  
* `open` / `close` – counters described above.  
* The two recursive calls correspond exactly to the two permissible moves.

---

## 2. Dry Run  

### Example: `n = 3`

We trace the execution of `generateParenthesis(3)`.  
The recursion tree is shown step‑by‑step; each node lists the tuple  
`(curr, open, close)`.

| Step | Action | `curr` | `open` | `close` | Comment |
|------|--------|--------|--------|----------|---------|
| 0 | start | `""` | 0 | 0 | root |
| 1 | add '(' (open < 3) | `"("` | 1 | 0 | first opening |
| 2 | add '(' (open < 3) | `"(("` | 2 | 0 | second opening |
| 3 | add '(' (open < 3) | `"((("` | 3 | 0 | third opening – no more '(' allowed |
| 4 | add ')' (close < open) | `"((()"` | 3 | 1 | start closing |
| 5 | add ')' (close < open) | `"((())"` | 3 | 2 | |
| 6 | add ')' (close < open) | `"((()))"` | 3 | 3 | length = 6 → push to `res` |
| 7 | backtrack to step 4, try alternative after `"((("` | – | – | – | |
| 8 | backtrack to step 2, now add ')' (close < open) | `"(()"` | 2 | 1 | |
| 9 | add '(' (open < 3) | `"(()("` | 3 | 1 | |
|10 | add ')' (close < open) | `"(()()"` | 3 | 2 | |
|11 | add ')' (close < open) | `"(()())"` | 3 | 3 | push |
|12 | backtrack to step 8, add ')' again | `"(() )"` → `"(() )"` actually `"(() )"` becomes `"(() )"`? Let's continue correctly: after step 8 we could also add ')' again: |
|12 | add ')' (close < open) | `"(())"` | 2 | 2 | |
|13 | add '(' (open < 3) | `"(())("` | 3 | 2 | |
|14 | add ')' (close < open) | `"(())()"` | 3 | 3 | push |
|15 | backtrack to step 1, now add ')' (close < open) | `"()"` | 1 | 1 | |
|16 | add '(' (open < 3) | `"()("` | 2 | 1 | |
|17 | add '(' (open < 3) | `"()(("` | 3 | 1 | |
|18 | add ')' (close < open) | `"()(()"` | 3 | 2 | |
|19 | add ')' (close < open) | `"()(())"` | 3 | 3 | push |
|20 | backtrack to step 16, add ')' instead of '(' | `"()()"` | 2 | 2 | |
|21 | add '(' (open < 3) | `"()()("` | 3 | 2 | |
|22 | add ')' (close < open) | `"()()()"` | 3 | 3 | push |

The final `res` array contains the six Catalan strings for `n = 3`:

```
["((()))","(()())","(())()","()(())","()()()"]
```

The table demonstrates how the two counters guide the recursion, guaranteeing that every generated string is balanced and that no invalid prefix is ever explored.

---

## 3. Complexity Analysis  

### Time Complexity  

The algorithm visits each node of the recursion tree exactly once.  
The number of leaf nodes equals the *n‑th Catalan number*  

\[
C_n = \frac{1}{n+1}\binom{2n}{n} = O\!\left(\frac{4^n}{n^{3/2}}\right)
\]

Each leaf corresponds to a valid parentheses string of length `2n`, and constructing that string costs `O(n)` time (concatenation of characters).  

Therefore, the overall time complexity is  

\[
\boxed{O\!\left(C_n \cdot n\right)} = O\!\left(\frac{4^n}{\sqrt{n}}\right)
\]

In practice, this is the optimal bound for generating **all** well‑formed combinations, because the output size itself is Θ\(C_n\).

### Space Complexity  

* **Result storage** – `res` holds `C_n` strings, each of length `2n`, giving `O(C_n·n)` space.  
* **Recursion stack** – the maximum depth equals `2n`, so the auxiliary stack uses `O(n)` extra space.  

Overall auxiliary (excluding the output) space is `O(n)`, while total memory consumption is dominated by the output list:  

\[
\boxed{O(C_n \cdot n)} \text{ (output) } + O(n) \text{ (call stack)}
\]

---

### TL;DR  

* Use backtracking with two counters (`open`, `close`).  
* Add `'('` while `open < n`; add `')'` while `close < open`.  
* When the built string reaches length `2n`, store it.  
* Time: `O(C_n·n)` (Catalan growth).  
* Space: `O(C_n·n)` for results + `O(n)` recursion depth.  

The solution is concise, leverages the natural constraints of balanced parentheses, and scales proportionally to the combinatorial size of the answer set—exactly what LeetCode expects for the **Generate Parentheses** problem.