class Solution {
    static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] cnt = new int[n+1];
        long[] sum = new long[n+1];
        long[] num = new long[n+1];
        long[] pow = new long[n+1];
        pow[0] = 1;
        for(int i = 1; i<=n; i++) {
            pow[i] = (pow[i-1]*10)%MOD;
            cnt[i] = cnt[i-1];
            sum[i] = sum[i-1];
            num[i] = num[i-1];

            int d = s.charAt(i-1)-'0';
            if(d!=0) {
                cnt[i]++;
                sum[i] +=d;
                num[i] = (num[i]*10+d)%MOD;
            }
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i<queries.length; i++) {
            int l = queries[i][0]+1;
            int r = queries[i][1]+1;

            int nz = cnt[r] - cnt[l-1];
            long digSum = sum[r] - sum[l-1];
            long number = (num[r] - num[l-1]*pow[nz]%MOD+MOD)%MOD;
            ans[i] = (int) ((number * (digSum % MOD)) % MOD);
        }
        return ans;
    }
}