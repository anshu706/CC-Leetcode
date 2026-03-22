class Solution {
    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] res =new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                res[i][j] = mat[n-1-j][i];
            }
        }
        return res;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        for (int k=0; k<4; k++) {
            if(Arrays.deepEquals(mat, target)) return true;
            mat = rotate(mat);
        }
        return false;
    }
}
