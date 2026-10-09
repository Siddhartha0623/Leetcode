class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;
        int i = 0;
        int n = s.length();

        while (i < n) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // need two consecutive ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    ans++;  // insert a missing ')'
                    i++;
                }
                // match with an open '('
                if (open > 0) {
                    open--;
                } else {
                    ans++;  // insert a missing '('
                }
            }
        }

        return ans + open * 2;
    }
}