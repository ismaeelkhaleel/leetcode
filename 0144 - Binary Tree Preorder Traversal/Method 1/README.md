## LeetCode Solution: Preorder Traversal of a Binary Tree
### **Intuition & Approach**
To solve this problem, we can utilize a recursive approach to perform a preorder traversal of the binary tree. Preorder traversal visits the current node before its child nodes, following the order: root, left subtree, right subtree. We can achieve this by defining a helper function that takes the current node and a list to store the node values as parameters. The base case for the recursion is when the current node is null, at which point we return without adding any value to the list. Otherwise, we add the current node's value to the list and recursively call the helper function on the left and right child nodes.

### **Dry Run**
Let's consider a simple binary tree example to illustrate the step-by-step process of the preorder traversal:
```
    1
   / \
  2   3
 / \
4   5
```
We start with the root node (1) and an empty list `[]`.

1. `helper(root=1, list=[])` is called.
2. Since `root` is not null, we add `root.val` (1) to the list: `[1]`.
3. We recursively call `helper(root.left=2, list=[1])`.
4. Again, since `root` (2) is not null, we add `root.val` (2) to the list: `[1, 2]`.
5. We recursively call `helper(root.left=4, list=[1, 2])`.
6. `root` (4) is not null, so we add `root.val` (4) to the list: `[1, 2, 4]`.
7. Since `root.left` and `root.right` of node 4 are null, we return from this recursive call.
8. Back at node 2, we now call `helper(root.right=5, list=[1, 2, 4])`.
9. `root` (5) is not null, so we add `root.val` (5) to the list: `[1, 2, 4, 5]`.
10. Since `root.left` and `root.right` of node 5 are null, we return from this recursive call.
11. Back at node 1, we now call `helper(root.right=3, list=[1, 2, 4, 5])`.
12. `root` (3) is not null, so we add `root.val` (3) to the list: `[1, 2, 4, 5, 3]`.
13. Since `root.left` and `root.right` of node 3 are null, we return from this recursive call, completing the traversal.

The final list contains the preorder traversal of the binary tree: `[1, 2, 4, 5, 3]`.

### **Complexity Analysis**
- **Time Complexity:** O(N), where N is the number of nodes in the binary tree. This is because we visit each node exactly once during the traversal.
- **Space Complexity:** O(N), due to the recursive call stack in the worst case (when the tree is skewed to one side, essentially becoming a linked list). Additionally, we store the node values in the list, which requires O(N) space.