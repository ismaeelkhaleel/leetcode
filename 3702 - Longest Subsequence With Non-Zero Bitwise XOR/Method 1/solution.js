/**
 * @param {number[]} nums
 * @return {number}
 */
var longestSubsequence = function(nums) {
    let xor = nums[0];
    for(let i = 1; i<nums.length; i++) {
        xor = xor ^ nums[i];
    }
    if(xor!=0) return nums.length;
    for(let i = 0; i<nums.length; i++) {
        let temp = xor;
        temp = temp ^ nums[i];
        if(temp!=0) return nums.length-1;
    }
    return 0;
};