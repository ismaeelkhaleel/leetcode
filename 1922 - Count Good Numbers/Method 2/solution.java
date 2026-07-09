class Solution {
    static final int MOD = 1_000_000_007;
    public int countGoodNumbers(long n) {
        long even = (n+1)/2;
        long odd = n/2;
        long p = (pow(5,even)*pow(4, odd))%MOD;
        return (int) p;
    }
    public long pow(long base, long exp) {

        if (exp == 0)
            return 1;

        long half = pow(base, exp / 2);

        long ans = (half * half) % MOD;

        if (exp % 2 == 1) {
            ans = (ans * base) % MOD;
        }

        return ans;
    }
}