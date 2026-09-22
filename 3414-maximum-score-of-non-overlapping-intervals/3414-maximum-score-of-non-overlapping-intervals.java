import java.util.*;

class Solution {

    class State {
        long score;
        List<Integer> list;

        State(long score, List<Integer> list) {
            this.score = score;
            this.list = list;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // start
            arr[i][1] = intervals.get(i).get(1); // end
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by start time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        // Find next non-overlapping interval
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i][1]);
        }

        State[][] dp = new State[n + 1][5];

        // Initialize ALL states at i = n
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        // DP
        for (int i = n - 1; i >= 0; i--) {

            // With 0 intervals allowed
            dp[i][0] = new State(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {

                // Skip current interval
                State skip = dp[i + 1][k];

                // Take current interval
                State nextState = dp[next[i]][k - 1];

                List<Integer> takeList =
                    new ArrayList<>(nextState.list);

                takeList.add(arr[i][3]);

                // Answer must be sorted by original index
                Collections.sort(takeList);

                State take = new State(
                    nextState.score + arr[i][2],
                    takeList
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].list;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score -> lexicographically smaller list
        int size = Math.min(a.list.size(), b.list.size());

        for (int i = 0; i < size; i++) {

            if (!a.list.get(i).equals(b.list.get(i))) {
                return a.list.get(i) < b.list.get(i) ? a : b;
            }
        }

        return a.list.size() <= b.list.size() ? a : b;
    }

    private int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}