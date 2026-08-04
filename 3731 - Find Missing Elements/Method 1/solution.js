/**
 * @param {number[]} nums
 * @return {number[]}
 */
var findMissingElements = function(nums) {
    let list = [];
    let minNum = Number.MAX_SAFE_INTEGER;
let maxNum = Number.MIN_SAFE_INTEGER;
    for(const num of nums) {
        minNum = Math.min(minNum, num);
        maxNum = Math.max(maxNum, num);
    }
    for(let i = minNum; i<=maxNum; i++) {
        let found = false;
        for(let k = 0; k<nums.length; k++) {
            if(i==nums[k]) {
                found = true;
                break;
            }
        }
        if(!found) list.push(i);
    }
    return list;
};