/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var missingMultiple = function(nums, k) {
    const set = new Set(nums);
    let temp = k;
    while(set.has(temp)) {
        temp += k;
    }
    return temp;
};