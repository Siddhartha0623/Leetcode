class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // score of the current outermost level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int inner = stack.pop();
                int val = Math.max(2 * inner, 1);
                stack.push(stack.pop() + val);
            }
        }

        return stack.pop();
    }
}

class Solution1 {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Previous char was '(' => this is a "()" core pair
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }

        return score;
    }
}