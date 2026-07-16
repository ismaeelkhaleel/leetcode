class Solution {
    public long gcdSum(int[] nums) {
        int mxi = nums[0];
        int[] prefixGcd = new int[nums.length];
        for(int i = 0; i<nums.length; i++) {
            mxi = Math.max(mxi, nums[i]);
            prefixGcd[i] = gcd(nums[i], mxi);
        }
        long sum = 0;
        int low = 0, high = prefixGcd.length-1;
        Arrays.sort(prefixGcd);
        while(low<high) {
            int g = gcd(prefixGcd[low],prefixGcd[high]);
            sum += (long) g;
            low++;
            high--;
        }
        return sum;
    }
    public int gcd(int a, int b) {
        while(b!=0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}