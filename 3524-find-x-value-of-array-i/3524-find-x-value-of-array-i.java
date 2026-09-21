class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        int[] dp = new int[k]; // dp[r] = # subarrays ending at current index with product % k == r

        for (int num : nums) {
            int a = num % k;
            int[] newDp = new int[k];
            newDp[a] += 1; // subarray consisting of just this element

            for (int r = 0; r < k; r++) {
                if (dp[r] != 0) {
                    newDp[(r * a) % k] += dp[r];
                }
            }

            dp = newDp;
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
        
    }
}