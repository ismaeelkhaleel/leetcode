### Approach
The given code is implementing a `map` function, which applies a given function `fn` to each element of the input array `arr`. The function takes two parameters: the array `arr` and the function `fn`. It iterates over the array using a for loop, applies the function `fn` to each element, and stores the result in a new array `res`. The function `fn` is called with two arguments: the current element and its index.

### Time Complexity
The time complexity of this code is **O(n)**, where n is the length of the input array `arr`. This is because the code iterates over the array once, performing a constant amount of work for each element.

### Space Complexity
The space complexity of this code is **O(n)**, where n is the length of the input array `arr`. This is because the code creates a new array `res` of the same length as the input array, which requires additional space proportional to the size of the input.