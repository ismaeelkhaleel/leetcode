/**
 * @param {string} word
 * @return {number}
 */
var minimumPushes = function(word) {
    let n = word.length;
    let d = Math.floor(n / 8);
    let rem = n % 8;
    let sum = 0;
    for(let i = 1; i<=d; i++) {
        sum = sum + i * 8;
    }
    return sum + rem * (d+1);
};