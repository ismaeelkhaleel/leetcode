/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxSubarrayLength = function(nums, k) {
    let map = new Map();
    let low = 0;
    let maxLen = 0;
    let high = 0;
    while(high<nums.length) {
        map.set(nums[high], (map.get(nums[high]) || 0)+1);
        while(map.get(nums[high])>k) {
            map.set(nums[low], map.get(nums[low])-1);
            low++;
        }
        let len = high - low + 1;
        maxLen = Math.max(len, maxLen);
        high++
    }
    return maxLen;
};