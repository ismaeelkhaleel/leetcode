class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int low = 0;
        int high = 0;
        int maxLen = 0;
        int sum = 0;
        while(high<nums.length) {
            map.put(nums[high], map.getOrDefault(nums[high],0)+1);     
            while(map.get(nums[high])>k) {
                map.put(nums[low], map.get(nums[low])-1);
                if(map.get(nums[low])==0) map.remove(nums[low]);
                low++;
            }
            int len = high-low+1;
            maxLen = Math.max(maxLen, len);
            high++;
        }
        return maxLen;
    }
}