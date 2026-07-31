class Solution {
    public int minimumPushes(String word) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<word.length(); i++) {
            char ch = word.charAt(i);
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        int count = 0;
        int push = 1;
        int sum = 0;
        for (Map.Entry<Character, Integer> entry : list) {
            int val = entry.getValue();
            sum = sum + push*val;
            count++;
            if(count%8==0) push++;
        }
        return sum;
    }
}