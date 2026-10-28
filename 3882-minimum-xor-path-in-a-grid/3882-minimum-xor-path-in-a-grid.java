class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // XOR of values below 2^b always stays below 2^b,
        // so find the smallest power of two greater than the max value
        int max = 0;
        for (int[] row : grid) {
            for (int v : row) max = Math.max(max, v);
        }
        int limit = 1;
        while (limit <= max) limit <<= 1;

        // dp[j][x] = true if some path reaches column j of the current row with XOR x
        boolean[][] dp = new boolean[n][limit];
        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                boolean[] cur = new boolean[limit];
                int v = grid[i][j];

                for (int x = 0; x < limit; x++) {
                    boolean fromTop = i > 0 && dp[j][x];
                    boolean fromLeft = j > 0 && dp[j - 1][x];

                    if (fromTop || fromLeft) {
                        cur[x ^ v] = true;
                    }
                }
                dp[j] = cur;
            }
        }

        for (int x = 0; x < limit; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}