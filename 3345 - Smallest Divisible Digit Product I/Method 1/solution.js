/**
 * @param {number} n
 * @param {number} t
 * @return {number}
 */
var smallestNumber = function(n, t) {
    let ans = 0;
    for(let i = n; i<=n+10; i++) {
        let prod = product(i);
        if(prod%t==0) {
            ans = i;
            break;
        }
    }
    return ans;
};
var product = function(m) {
    let prod = 1;
    while(m>0) {
        let rem = m % 10;
        prod = prod * rem;
        m = Math.floor(m/10);
    }
    return prod;
}