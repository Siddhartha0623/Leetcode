class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == s.charAt((i + 1) % n)) {
                m++;
            }
        }
        if (k == m) {
            return n - m;
        }
        if (k == m - 1) {
            return m;
        }
        return 0;
    }
}