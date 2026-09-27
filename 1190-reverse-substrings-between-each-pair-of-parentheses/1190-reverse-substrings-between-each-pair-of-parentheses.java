class Solution {
    public String reverseParentheses(String s) {
         int n = s.length();
    
    Deque<Integer> openBracketIdx = new ArrayDeque<>();
    int[] door = new int[n];
    
    for (int i = 0; i < n; i++) {
        if (s.charAt(i) == '(') {
            openBracketIdx.push(i);
        } else if (s.charAt(i) == ')') {
            int j = openBracketIdx.peek();
            openBracketIdx.pop();
            door[i] = j;
            door[j] = i;
        }
    }
    
    StringBuilder result = new StringBuilder();
    int flag = 1;
    for (int i = 0; i < n; i += flag) {
        if (s.charAt(i) == '(' || s.charAt(i) == ')') {
            i = door[i];
            flag = -flag; // changing the direction
        } else {
            result.append(s.charAt(i));
        }
    }
    
    return result.toString();
        
    }
}