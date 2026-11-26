class Solution {
    public boolean canAliceWin(int n) {
        int temp = 10;
        boolean flag = false;
        while(n>=temp) {
            n = n - temp;
            flag = !flag;
            temp--;
        }
        return flag;
    }
}