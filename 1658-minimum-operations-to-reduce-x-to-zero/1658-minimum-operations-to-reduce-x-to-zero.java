class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int target = total - x;
        
        if (target == 0) {
            return n;
        }
        
        int maxLen = 0, curSum = 0, left = 0;
        
        for (int right = 0; right < n; right++) {
            curSum += nums[right];
            while (left <= right && curSum > target) {
                curSum -= nums[left];
                left++;
            }
            if (curSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        return maxLen > 0 ? n - maxLen : -1;
        
    }
}