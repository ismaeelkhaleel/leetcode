class Solution {
    public static boolean isPalindrome(int x) {
        int rev =0;
        int originalNum = x;
        while(x>0) {
            int rem = x%10;
            rev = rev*10 + rem;
            x = x/10;
        }
        if(originalNum==rev) {
            return true;
        } else {
            return false;
        }