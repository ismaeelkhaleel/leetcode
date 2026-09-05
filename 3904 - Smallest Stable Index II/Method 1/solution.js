/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var firstStableIndex = function(nums, k) {
    let n = nums.length;
    let maxL = [];
    let minR = [];
    let max = nums[0];
    let min = nums[n-1];
    for(let i = 0; i<n; i++) {
        max = Math.max(max, nums[i]);
        maxL[i] = max;
    }
    for(let i = n - 1; i>=0; i--) {
        min = Math.min(min, nums[i]);
        minR[i] = min;
    }
    let ans = -1;
    for(let i = 0; i<n; i++) {
        let score = maxL[i] - minR[i];
        if(score<=k) {
            ans = i;
            break;
        }
    }
    return ans;
};