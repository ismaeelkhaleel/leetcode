class Solution {
    public int[] resultArray(int[] nums) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list1.add(nums[0]);
        list2.add(nums[1]);
        for(int i = 2; i<nums.length; i++) {
            int idx1 = list1.size()-1;
            int idx2 = list2.size()-1;
            if(list1.get(idx1)>list2.get(idx2)) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        int idx = 0;
        for(int i = 0; i<list1.size(); i++) {
            nums[idx++] = list1.get(i);
        }
        for(int i = 0; i<list2.size(); i++) {
            nums[idx++] = list2.get(i);
        }
        return nums;
    }
}