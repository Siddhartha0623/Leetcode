class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        // Case 1: cyclic increasing, nums[i] = (i + k) % n
        int k = nums[0];
        boolean inc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) {
                inc = false;
                break;
            }
        }
        if (inc) {
            if (k == 0) {
                best = 0;
            } else {
                // rotate left (n - k) times, or rotate right k times via R L^k R
                best = Math.min(n - k, k + 2);
            }
        }

        // Case 2: cyclic decreasing, nums[i] = (k - i) mod n
        boolean dec = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != ((k - i) % n + n) % n) {
                dec = false;
                break;
            }
        }
        if (dec) {
            int kp = (k + 1) % n;
            // rotate left kp times then reverse
            int opt1 = kp + 1;
            // reverse then rotate left (n - kp) % n times
            int opt2 = 1 + (n - kp) % n;
            best = Math.min(best, Math.min(opt1, opt2));
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}