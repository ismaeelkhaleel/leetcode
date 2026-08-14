/**
 * @param {string} s
 * @return {number}
 */
var maximumLengthSubstring = function(s) {
    let maxLen = 0;

    for (let i = 0; i < s.length; i++) {
        for (let k = i; k < s.length; k++) {
            let str = s.substring(i, k + 1);

            if (isValid(str)) {
                maxLen = Math.max(maxLen, str.length);
            }
        }
    }

    return maxLen;
};

function isValid(str) {
    let hash = new Array(26).fill(0);

    for (let i = 0; i < str.length; i++) {
        let idx = str.charCodeAt(i) - 'a'.charCodeAt(0);
        hash[idx]++;

        if (hash[idx] > 2) {
            return false;
        }
    }

    return true;
}