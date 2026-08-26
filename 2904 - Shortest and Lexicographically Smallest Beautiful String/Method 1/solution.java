class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int[] freq = new int[2];
        int n = s.length();
        int low = 0;
        int high = 0;
        int minLen = Integer.MAX_VALUE;
        String str = "";
        while(high<n) {
            int idx = s.charAt(high)-'0';
            freq[idx]++;
            while(freq[1]>k) {
                int lowIdx = s.charAt(low)-'0';
                freq[lowIdx]--;
                low++;
            }
            while(s.charAt(low)=='0' && low<high) {
                freq[0]--;
                low++;
            }
            int len = high - low + 1;
            if(freq[1]==k) {
                if(len<minLen) {
                    minLen = len;
                    str = s.substring(low, high+1);
                } else if(len==minLen) {
                    if(str.length()!=0) {
                        str = str.compareTo(s.substring(low, high+1)) < 0 ? str : s.substring(low, high+1);
                    } else {
                        str = s.substring(low, high+1);
                    }
                }
            }
            high++;
        }
        return str;
    }
}