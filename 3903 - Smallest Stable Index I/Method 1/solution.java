class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        int max = nums[0];
        for(int i = 0; i<n; i++) {
            max = Math.max(max, nums[i]);
            maxLeft[i] = max;
        }
        int min = nums[n-1];
        for(int i = n - 1; i>=0; i--) {
            min = Math.min(min, nums[i]);
            minRight[i] = min;
        }
        int ans = -1;
        for(int i = 0; i<n; i++) {
            int score = maxLeft[i] - minRight[i];
            if(score<=k) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}