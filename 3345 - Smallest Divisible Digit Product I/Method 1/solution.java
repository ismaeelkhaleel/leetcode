class Solution {
    public int smallestNumber(int n, int t) {
        int ans = 0;
        for(int i = n; i<=n+10; i++) {
            int prod = product(i);
            if(prod%t==0) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    public int product(int m) {
        int prod = 1;
        while(m>0) {
            int rem = m % 10;
            prod = prod * rem;
            m = m / 10;
        }
        return prod;
    }
}