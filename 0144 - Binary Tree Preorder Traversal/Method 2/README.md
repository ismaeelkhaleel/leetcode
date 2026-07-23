## LeetCode Solution: Preorder Traversal of a Binary Tree
### Intuition & Approach
To solve this problem, we can utilize a stack-based approach to perform a preorder traversal of the binary tree. The core logic involves using a stack to store nodes that need to be visited. We start by pushing the root node onto the stack. Then, we enter a loop that continues until the stack is empty. In each iteration, we pop the top node from the stack, add its value to the result list, and push its right and left children onto the stack (if they exist). This ensures that we visit the current node before its children, which is the definition of a preorder traversal.

Notice how we push the right child before the left child onto the stack. This is because we use a stack, which is a Last-In-First-Out (LIFO) data structure. By pushing the right child first, we ensure that the left child is visited before the right child when we pop nodes from the stack.

### Dry Run
Let's consider a simple example to illustrate the step-by-step process. Suppose we have the following binary tree:
```
    1
   / \
  2   3
 / \
4   5
```
Here's how the variables change at each step:

1. Initially, the stack `st` is empty, and the result list `list` is empty. We push the root node (1) onto the stack.
   - `st`: [1]
   - `list`: []

2. We enter the loop and pop the top node (1) from the stack. We add its value to the result list and push its right and left children onto the stack.
   - `st`: [3, 2]
   - `list`: [1]

3. We pop the top node (2) from the stack. We add its value to the result list and push its right and left children onto the stack.
   - `st`: [3, 5, 4]
   - `list`: [1, 2]

4. We pop the top node (4) from the stack. We add its value to the result list. Since node 4 has no children, we don't push any nodes onto the stack.
   - `st`: [3, 5]
   - `list`: [1, 2, 4]

5. We pop the top node (5) from the stack. We add its value to the result list. Since node 5 has no children, we don't push any nodes onto the stack.
   - `st`: [3]
   - `list`: [1, 2, 4, 5]

6. We pop the top node (3) from the stack. We add its value to the result list. Since node 3 has no children, we don't push any nodes onto the stack.
   - `st`: []
   - `list`: [1, 2, 4, 5, 3]

The loop ends when the stack is empty, and the result list contains the preorder traversal of the binary tree.

### Complexity Analysis
* **Time Complexity:** O(n), where n is the number of nodes in the binary tree. This is because we visit each node exactly once.
* **Space Complexity:** O(n), where n is the number of nodes in the binary tree. In the worst case, the stack can contain n nodes (when the tree is skewed to one side). However, in the average case, the space complexity is O(h), where h is the height of the tree. For a balanced binary tree, h = log(n), resulting in a space complexity of O(log n).