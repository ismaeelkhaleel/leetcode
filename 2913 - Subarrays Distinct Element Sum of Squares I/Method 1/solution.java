class Solution {
    public int sumCounts(List<Integer> nums) {
        int score = 0;
        for(int i = 0; i<nums.size(); i++) {
            Set<Integer> st = new HashSet<>();
            for(int k = i; k<nums.size(); k++) {
                 
                st.add(nums.get(k));

                int size = st.size();

                score += size * size;
            }
        }
        return score;
    }
}