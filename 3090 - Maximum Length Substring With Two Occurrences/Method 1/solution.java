class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLen = 0;
        for(int i = 0; i<s.length(); i++) {
            for(int k = i; k<s.length(); k++) {
                String str = s.substring(i,k+1);
                if(isValid(str)) {
                    maxLen = Math.max(maxLen, str.length());
                }
            }
        }
        return maxLen;
    }
    private boolean isValid(String str) {
        int[] hash = new int[26];
        for(int i = 0; i<str.length(); i++) {
            int idx = str.charAt(i)-'a';
            hash[idx]++;
            if(hash[idx]>2) return false;
        }
        return true;
    }
}