class Solution {
    public long maxAlternatingSum(int[] nums) {
        int total = nums.length;
        long[] sq = new long[total];
        for (int counter = 0; counter < total; counter++) {
            sq[counter] = (long) nums[counter] * nums[counter];
        }
        Arrays.sort(sq);

        int tertiary = (total + 1) / 2;
        long score = 0;
        for (int counter = 0; counter < total; counter++) {
            if (counter >= total - tertiary) score += sq[counter];
            else score -= sq[counter];
        }
        return score;
    }
}