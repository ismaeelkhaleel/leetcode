class Solution {
    static final int MOD = 1_000_000_007;
    public int countGoodNumbers(long n) {
        long even = (n+1)/2;
        long odd = n/2;
        long p = (pow(5,even)*pow(4, odd))%MOD;
        return (int) p;
    }
    public long pow(long base, long exp) {
        long ans = 1;
        while(exp>0) {
            if((exp & 1)==1) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return ans;
    }
}