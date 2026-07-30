class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int d = n / 8;
        int rem = n % 8;
        int sum = 0;
        for(int i = 1; i<=d; i++) {
            sum = sum + i * 8;
        }
        return sum + rem * (d+1);
    }
}