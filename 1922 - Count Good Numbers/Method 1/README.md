# LeetCode Solution: Count Good Numbers
I'm excited to share my solution to this LeetCode problem, which involves counting the number of "good numbers" in a given range. In this explanation, I'll walk you through my thought process, the approach I took, and provide a step-by-step breakdown of how the code works.

## **Intuition & Approach**
When I first read the problem statement, I realized that a "good number" is defined as a number that has an even number of digits with even values (0, 2, 4, 6, 8) and an odd number of digits with odd values (1, 3, 5, 7, 9). My initial instinct was to iterate through all possible numbers and check each digit to determine if it's a good number. However, this approach would be inefficient and wouldn't scale well for large inputs.

Instead, I decided to use a more mathematical approach. I noticed that the problem can be reduced to finding the number of ways to arrange even and odd digits in a number. Since we're dealing with a binary distinction (even or odd), I realized that I could use the concept of combinatorics and modular arithmetic to solve the problem.

The key insight here is that we can use the properties of modular exponentiation to efficiently calculate the number of good numbers. Specifically, I used the fact that `a^(b*c) = (a^b)^c` to reduce the number of calculations required.

In my code, I define a helper function `pow` that calculates the modular exponentiation of a given base and exponent. This function uses the "exponentiation by squaring" technique, which reduces the number of multiplications required by using the fact that `a^(2*b) = (a^b)^2`.

## **Dry Run**
Let's take a simple example to illustrate how the code works. Suppose we call the `countGoodNumbers` function with the input `n = 4`. Here's a step-by-step breakdown of how the variables change:

* `even = (n+1)/2 = (4+1)/2 = 2`
* `odd = n/2 = 4/2 = 2`
* `p = (pow(5, even) * pow(4, odd)) % MOD`
	+ `pow(5, 2)` is calculated using the `pow` function:
		- `ans = 1`
		- `exp = 2`
		- `base = 5`
		- First iteration: `exp = 2`, `ans = 1`, `base = 5`
		- Second iteration: `exp = 1`, `ans = (1 * 5) % MOD = 5`, `base = (5 * 5) % MOD = 25`
		- Third iteration: `exp = 0`, `ans = 5`, `base = (25 * 25) % MOD = 625`
		- `pow(5, 2)` returns `5`
	+ `pow(4, 2)` is calculated using the `pow` function:
		- `ans = 1`
		- `exp = 2`
		- `base = 4`
		- First iteration: `exp = 2`, `ans = 1`, `base = 4`
		- Second iteration: `exp = 1`, `ans = (1 * 4) % MOD = 4`, `base = (4 * 4) % MOD = 16`
		- Third iteration: `exp = 0`, `ans = 4`, `base = (16 * 16) % MOD = 256`
		- `pow(4, 2)` returns `4`
	+ `p = (5 * 4) % MOD = 20`
* The final result is returned as an integer: `(int) p = 20`

## **Complexity Analysis**
The time complexity of my solution is O(log n), where n is the input number. This is because the `pow` function uses exponentiation by squaring, which reduces the number of multiplications required to calculate the modular exponentiation.

The space complexity is O(1), which means that the space required does not grow with the size of the input. This is because we only use a constant amount of space to store the variables `even`, `odd`, `p`, and `ans`.

Overall, my solution uses a combination of mathematical insights and efficient algorithms to solve the problem in a scalable and efficient way.