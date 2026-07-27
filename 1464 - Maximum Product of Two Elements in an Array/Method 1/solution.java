class Solution {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int p1 = (nums[0]-1)*(nums[1]-1);
        int p2 = (nums[n-1]-1)*(nums[n-2]-1);
        return Math.max(p1,p2);
    }
}