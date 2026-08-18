class Solution {
    public int largestInteger(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxNum = nums[0];
        for(int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
            maxNum = Math.max(maxNum, num);
        }
         
        if(k==1) {
            int ans = -1;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    ans = Math.max(ans, entry.getKey());
                }
            }

            return ans;
        }
        if(k==nums.length) return maxNum;
        boolean firstUnique = map.get(nums[0])==1;
        boolean lastUnique = map.get(nums[nums.length-1])==1;
        if(firstUnique && lastUnique) {
            return Math.max(nums[0], nums[nums.length-1]);
        }
        if(firstUnique) {
            return nums[0];
        }
        if(lastUnique) {
            return nums[nums.length-1];
        }
        return -1;
    }
}