class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = nums[0];
        for(int i = 1; i<nums.length; i++) {
            xor = xor ^ nums[i];
        }
        if(xor!=0) return nums.length;
        for(int i = 0; i<nums.length; i++) {
            int temp = xor;
            temp = temp ^ nums[i];
            if(temp!=0) return nums.length-1;
        }
        return 0;
    }
}