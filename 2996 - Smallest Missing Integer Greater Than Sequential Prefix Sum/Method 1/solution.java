class Solution {
    public int missingInteger(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        int sum = nums[0];
        for(int i = 1; i<nums.length; i++) {
            if(nums[i]==nums[i-1]+1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        while(map.containsKey(sum)) {
            sum++;
        }
        return sum;
    }
}