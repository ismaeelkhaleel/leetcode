class Solution {
    public int maximumLengthSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while(right<s.length()) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch,0)+1);
            while(map.get(ch)>2) {
                char chl = s.charAt(left);
                map.put(chl, map.get(chl)-1);
                if(map.get(chl)==0) map.remove(chl);
                left++;
            }
            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);
            right++;
        }
        return maxLen;
    }
}