/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function(n) {
    let res = [];
    generateAll(n, res, "",0,0);
    return res;
};

let generateAll = function(n, res, curr, open, close) {
    if(curr.length==2*n) {
        res.push(curr);
        return;
    }
    if(open<n) generateAll(n, res, curr+"(", open+1, close);
    if(close<open) generateAll(n, res, curr+")", open, close+1);
}