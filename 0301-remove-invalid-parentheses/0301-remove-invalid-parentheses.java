class Solution {
    public List<String> removeInvalidParentheses(String s) {
         List<String> ans = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // If valid, add it
                if (isValid(current)) {
                    ans.add(current);
                    found = true;
                }

                // If a valid string is already found,
                // don't remove more characters.
                if (found) {
                    continue;
                }

                // Try removing one character
                for (int j = 0; j < current.length(); j++) {

                    // Only parentheses need to be removed
                    if (current.charAt(j) != '(' &&
                        current.charAt(j) != ')') {
                        continue;
                    }

                    String next = current.substring(0, j)
                                 + current.substring(j + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }

            // We found valid strings at this level,
            // so they require minimum removals.
            if (found) {
                break;
            }
        }

        return ans;
    }

    // Checks whether parentheses are valid
    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {
                balance--;

                // More ')' than '('
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}