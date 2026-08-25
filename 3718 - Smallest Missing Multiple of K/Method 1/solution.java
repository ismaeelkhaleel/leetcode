class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int temp = k;

        while (set.contains(temp)) {
            temp += k;
        }

        return temp;
    }
}