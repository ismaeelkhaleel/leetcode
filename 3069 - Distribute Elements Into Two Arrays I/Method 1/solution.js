/**
 * @param {number[]} nums
 * @return {number[]}
 */
var resultArray = function(nums) {
    let arr1 = [];
    let arr2 = [];
    arr1.push(nums[0]);
    arr2.push(nums[1]);
    for(let i = 2; i<nums.length; i++) {
        let idx1 = arr1.length-1;
        let idx2 = arr2.length-1;
        if(arr1[idx1]>arr2[idx2]) {
            arr1.push(nums[i]);
        } else {
            arr2.push(nums[i]);
        }
    }
    let idx = 0;
    for(let i = 0; i<arr1.length; i++) {
        nums[idx++] = arr1[i];
    }
    for(let i = 0; i<arr2.length; i++) {
        nums[idx++] = arr2[i];
    }
    return nums;
};