class Solution {
    public long maxAlternatingSum(int[] nums) {
        for(int i = 0; i<nums.length; i++) {
            if(nums[i]<0) {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
        long score = 0;
        for(int i = 0; i<nums.length/2; i++) {
            score -= (long) nums[i]*nums[i];
        }
        for(int i = nums.length/2; i<nums.length; i++) {
            score += (long) nums[i]*nums[i];
        }
        return score;
    }
}