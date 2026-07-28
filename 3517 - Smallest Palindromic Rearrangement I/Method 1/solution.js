/**
 * @param {string} s
 * @return {string}
 */
var smallestPalindrome = function(s) {
    let n = s.length;
    if(n<=3) return s;
    let str = s.substring(0,n/2);
    let sorted = str.split("").sort().join("");
    let reversed = sorted.split("").reverse().join("");
    let newStr;
    if(n%2==1) {
        newStr = sorted + s[Math.floor(n/2)] + reversed;
    } else {
        newStr = sorted + reversed;
    }
    return newStr;
};