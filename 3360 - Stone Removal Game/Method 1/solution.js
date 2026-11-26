/**
 * @param {number} n
 * @return {boolean}
 */
var canAliceWin = function(n) {
    let temp = 10;
    let flag = false;
    while(n>=temp) {
        n = n - temp;
        flag = !flag;
        temp--;
    }
    return flag;
};