/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var largestInteger = function(nums, k) {
    let map = new Map();
    let maxNum = nums[0];
    for(let num of nums) {
        map.set(num,(map.get(num) || 0)+1);
        maxNum = Math.max(maxNum, num);
    }
    if(k==1) {
        let ans = -1;
        for(const [key, val] of map) {
            if(val==1) {
                ans = Math.max(ans, key);
            }
        }
        return ans;
    }
    if(k==nums.length) return maxNum;
    let firstUnique = map.get(nums[0])===1;
    let lastUnique = map.get(nums[nums.length-1])===1;
    if(firstUnique && lastUnique) {
        return Math.max(nums[0], nums[nums.length-1]);
    }
    if(firstUnique) {
        return nums[0];
    }
    if(lastUnique) {
        return nums[nums.length-1];
    }
    return -1;
};