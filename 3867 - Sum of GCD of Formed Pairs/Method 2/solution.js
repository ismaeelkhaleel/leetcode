/**
 * @param {number[]} nums
 * @return {number}
 */
var gcdSum = function(nums) {
    let max = nums[0];
    let prefixGcd = [];
    for(let i = 0; i<nums.length; i++) {
        max = Math.max(max, nums[i]);
        prefixGcd[i] = gcd(max, nums[i]);
    }
    prefixGcd.sort((a,b)=>a-b);
    let low = 0;
    let high = prefixGcd.length-1;
    let sum = 0;
    while(low<high) {
        let g = gcd(prefixGcd[low], prefixGcd[high]);
        sum = sum + g;
        low++;
        high--;
    }
    return sum;
};
var gcd = function(a, b) {
    while(b!=0) {
        let temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}