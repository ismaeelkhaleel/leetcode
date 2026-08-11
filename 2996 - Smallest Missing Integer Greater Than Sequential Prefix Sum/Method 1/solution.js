/**
 * @param {number[]} nums
 * @return {number}
 */
var missingInteger = function(nums) {
    let map = new Map();
    for(let num of nums) {
         map.set(num, (map.get(num) || 0) + 1);
    }
    let sum = nums[0];
    for(let i = 1; i<nums.length; i++) {
        if(nums[i]==nums[i-1]+1){
            sum = sum + nums[i];
        } else {
            break;
        }
    }
    while(map.has(sum)) {
        sum++;
    }
    return sum;
};