class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        if(n<=3) return s;
        String str = s.substring(0,n/2);
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<chars.length; i++) {
            sb.append(chars[i]);
        }
        if(n%2==1) {
            str = sb.toString() + s.charAt(n/2) + sb.reverse().toString();
        } else {
            str = sb.toString() + sb.reverse().toString();
        }
        return str;
    }
}