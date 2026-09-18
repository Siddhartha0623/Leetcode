class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        
         int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] != i) continue; // only start at first occurrence

            int end = last[c];
            int j = i;
            boolean valid = true;

            while (j <= end) {
                int cj = s.charAt(j) - 'a';
                if (first[cj] < i) {
                    valid = false;
                    break;
                }
                if (last[cj] > end) {
                    end = last[cj];
                }
                j++;
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        // sort by end position (ascending)
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> res = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (start > prevEnd) {
                res.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return res;
    }
}