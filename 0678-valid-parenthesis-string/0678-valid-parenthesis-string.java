class Solution {
    public boolean checkValidString(String s) {
        int low = 0;   // minimum possible number of unmatched '('
        int high = 0;  // maximum possible number of unmatched '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { // '*'
                low--;   // treat '*' as ')'
                high++;  // treat '*' as '('
            }

            // too many ')' even if every '*' was '(' -> impossible
            if (high < 0) {
                return false;
            }

            // low can't go below 0 (we can treat some '*' as empty instead)
            if (low < 0) {
                low = 0;
            }
        }

        // valid only if we can end with zero unmatched '('
        return low == 0;
        
    }
}