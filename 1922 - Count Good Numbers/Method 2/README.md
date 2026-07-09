# LeetCode Solution: Count Good Numbers
I'm excited to share my solution to this LeetCode problem, which involves counting the number of good numbers in a given range. In this explanation, I'll walk you through my thought process, the approach I took, and provide a step-by-step breakdown of how the code works.

## **Intuition & Approach**
When I first read the problem statement, I realized that a good number is essentially a number that has an equal number of even and odd digits. To tackle this problem, I decided to use a combination of mathematical reasoning and clever use of data structures - specifically, modular arithmetic and exponentiation by squaring. My approach involves calculating the number of ways to arrange even and odd digits in a number, and then using the properties of modular arithmetic to avoid overflow.

The key insight here is that we can calculate the number of good numbers by considering the number of ways to arrange 2s and 5s in the prime factorization of the number. Since we're dealing with large numbers, I used the `long` data type to avoid overflow. I also defined a constant `MOD` to represent the modulo value, which is set to 1,000,000,007.

To calculate the number of good numbers, I first calculate the number of even and odd digits in the number `n`. I then use these values to calculate the number of ways to arrange 2s and 5s in the prime factorization of the number, using the `pow` function to compute the result of exponentiation by squaring.

## **Dry Run**
Let's take a simple example to illustrate how the code works. Suppose we call the `countGoodNumbers` function with `n = 4`. Here's how the variables change:

* `even = (n+1)/2 = (4+1)/2 = 2`
* `odd = n/2 = 4/2 = 2`
* `p = (pow(5,even)*pow(4, odd))%MOD = (pow(5,2)*pow(4, 2))%MOD`
* `pow(5,2)` is calculated using the `pow` function:
	+ `half = pow(5, 2/2) = pow(5, 1) = 5`
	+ `ans = (half * half) % MOD = (5 * 5) % MOD = 25`
* `pow(4,2)` is calculated using the `pow` function:
	+ `half = pow(4, 2/2) = pow(4, 1) = 4`
	+ `ans = (half * half) % MOD = (4 * 4) % MOD = 16`
* `p = (25 * 16) % MOD = 400 % MOD`

## **Complexity Analysis**
The time complexity of my solution is O(log n), where n is the input number. This is because the `pow` function uses exponentiation by squaring, which reduces the number of multiplications required to compute the result.

The space complexity is O(1), which means the space required does not change with the size of the input number. This is because we only use a constant amount of space to store the variables `even`, `odd`, and `p`.

Overall, my solution uses a combination of mathematical reasoning and clever use of data structures to efficiently count the number of good numbers in a given range. By using modular arithmetic and exponentiation by squaring, I'm able to avoid overflow and achieve a efficient time complexity.