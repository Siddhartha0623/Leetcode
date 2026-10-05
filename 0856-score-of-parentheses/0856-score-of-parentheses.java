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