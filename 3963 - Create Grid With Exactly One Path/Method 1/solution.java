class Solution {
    public String[] createGrid(int m, int n) {
        String[][] res = new String[m][n];
        for(int i = 0; i<n; i++) {
            res[0][i] = ".";
        }
        for(int i = 0; i<m; i++) {
            res[i][n-1] = ".";
        }

        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                if(!(".".equals(res[i][j]))) res[i][j] = "#";
            }
        }
        String[] ans = new String[m];
        int idx = 0;
        for(int i = 0; i<m; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<n; j++) {
                sb.append(res[i][j]);
            }
            ans[idx++] = sb.toString();
        }
        return ans;
    }
}