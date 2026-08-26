/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
var shortestBeautifulSubstring = function(s, k) {
    let countOne = 0;
    let low = 0;
    let high = 0;
    let n = s.length;
    let str = "";
    let minLen = Number.MAX_SAFE_INTEGER;
    while(high<n) {
        if(s[high]=='1') countOne++;
        while(countOne>k) {
            if(s[low]=='1') countOne--;
            low++;
        }
        while(s[low]=='0' && low<high) {
            low++;
        }
        let len = high - low + 1;
        if(countOne==k) {
            if(len<minLen) {
                minLen = len;
                str = s.substring(low, high+1);
            } else if(len==minLen) {
                if(str.length==0) {
                    str = s.substring(low, high+1);
                } else {
                    str = str < s.substring(low, high+1) ? str : s.substring(low, high+1);
                }
            }
        }
        high++;
    }
    return str;
};