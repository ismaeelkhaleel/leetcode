class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int lq = 0, rq = 0, d = 0;
        for(int i = 0; i<n/2; i++) {
            char ch = num.charAt(i);
            if(ch=='?') {
                lq++;
            } else {
                d += ch-'0';
            }
        }
        for(int i = n/2; i<n; i++) {
            char ch = num.charAt(i);
            if(ch=='?') {
                rq++;
            } else {
                d -= ch-'0';
            }
        }
        if(lq==rq) {
            return d!=0;
        }
        if(Math.abs(lq-rq)%2==1) {
            return true;
        }
        int ad = 9*Math.abs(lq-rq)/2;
        if(lq>rq) {
            d += ad;
        } else {
            d -= ad;
        }
        return d!=0;
    }
}