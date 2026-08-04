class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        for(int i = min; i<=max; i++) {
            boolean found = false;
            for(int k = 0; k<nums.length; k++) {
                if(i==nums[k]) {
                    found = true;
                    break;
                }
            }
            if(!found) list.add(i);
        }
        return list;
    }
}