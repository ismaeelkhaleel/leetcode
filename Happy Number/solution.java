        if(set.contains(n)) return false;
        set.add(n);
        n = square(n);
        return isHappy(n);
    }
    int square(int n) {
        int temp = 0;
        while(n>0) {
            int dig = n % 10;
            temp += dig*dig;
            n = n / 10;
        if(n==1) return true;
    public boolean isHappy(int n) {
    HashSet<Integer> set = new HashSet<>();
class Solution {