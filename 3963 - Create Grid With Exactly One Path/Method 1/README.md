## 1. Intuition & Approach  

The task is to build an `m × n` grid that satisfies two simple constraints:

1. The **first row** (row 0) must be filled with `'.'`.  
2. The **last column** (column `n‑1`) must be filled with `'.'`.  

All remaining cells should contain `'#'`.  
The final output is an array of `m` strings, each string representing one row of the grid.

The solution follows three logical phases:

| Phase | Goal | How it is achieved |
|------|------|--------------------|
| **Initialisation** | Allocate a 2‑dimensional `String` matrix `res[m][n]`. | `new String[m][n]` creates a matrix whose entries are initially `null`. |
| **Mark the required `'.'` cells** | Fill the first row and the last column with `'.'`. | Two separate loops: one iterates over columns of row 0, the other iterates over rows of column `n‑1`. |
| **Populate the rest with `'#'`** | Replace every `null` (or any non‑dot) entry with `'#'`. | A nested loop checks `if (!".".equals(res[i][j]))` and writes `"#"` when the condition holds. |
| **Convert to the required output format** | Transform each row of the matrix into a single `String`. | For each row, a `StringBuilder` concatenates the `n` characters, and the resulting string is stored in the answer array. |

Because the algorithm works directly on indices, it runs in linear time with respect to the number of cells (`m·n`). No auxiliary data structures beyond the output array are needed, keeping the space usage optimal.

---

## 2. Dry Run  

Consider a small example: `m = 3`, `n = 4`.

```
m = 3 rows   (0, 1, 2)
n = 4 cols   (0, 1, 2, 3)
```

### Step‑by‑step execution

| Step | Code fragment | Effect on `res` (matrix view) |
|------|---------------|-------------------------------|
| **1. Allocation** | `String[][] res = new String[m][n];` | All cells are `null`.<br>`[[null, null, null, null], [null, null, null, null], [null, null, null, null]]` |
| **2. Fill first row** | `for (int i = 0; i < n; i++) res[0][i] = ".";` | Row 0 becomes `[".", ".", ".", "."]`.<br>`[[".", ".", ".", "."], [null, null, null, null], [null, null, null, null]]` |
| **3. Fill last column** | `for (int i = 0; i < m; i++) res[i][n‑1] = ".";` | Column 3 of every row becomes `'.'`.<br>`[[".", ".", ".", "."], [null, null, null, "."], [null, null, null, "."]]` |
| **4. Replace remaining cells** | `if (!".".equals(res[i][j])) res[i][j] = "#";` (nested loops) | - `(i=1, j=0)`: `null` → `"#"`<br>- `(i=1, j=1)`: `null` → `"#"`<br>- `(i=1, j=2)`: `null` → `"#"`<br>- `(i=2, j=0)`: `null` → `"#"`<br>- `(i=2, j=1)`: `null` → `"#"`<br>- `(i=2, j=2)`: `null` → `"#"`<br>Resulting matrix:<br>`[[".", ".", ".", "."], ["#", "#", "#", "."], ["#", "#", "#", "."]]` |
| **5. Build answer strings** | Outer loop over rows, inner `StringBuilder` over columns | - Row 0 → `". . . ."` → `". . . ."` (concatenated) → `"...".`<br>- Row 1 → `"# # # ."` → `"###."`<br>- Row 2 → `"# # # ."` → `"###."` |
| **6. Return** | `return ans;` | `ans = ["....", "###.", "###."]` |

The final output matches the specification: the top row and rightmost column are `'.'`, everything else is `'#'`.

---

## 3. Complexity Analysis  

| Metric | Evaluation |
|--------|------------|
| **Time Complexity** | The algorithm touches each cell of the `m × n` matrix a constant number of times: <br>• First row – `O(n)` <br>• Last column – `O(m)` <br>• Full scan to fill `'#'` – `O(m·n)` <br>• Building the strings – `O(m·n)` <br>Overall: **`O(m·n)`**. |
| **Space Complexity** | The only extra storage beyond the input parameters is the `String[][] res` matrix (size `m·n`) and the output array `String[m]`. Both are required by the problem definition, so the auxiliary space (excluding output) is **`O(1)`**. If the output itself is counted, the total space is **`O(m·n)`**. |

The solution therefore scales linearly with the grid size, making it suitable for the maximum constraints typically found on LeetCode.