class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        // Flatten the grid into a 1D array
        int[] flat = new int[total];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flat[idx++] = grid[i][j];
            }
        }
        // Reduce k to avoid redundant full rotations
        k = k % total;
        // Build the result list, placing each element at its shifted position
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            result.add(row);
        }
        for (int i = 0; i < total; i++) {
            int newIndex = (i + k) % total;
            int newRow = newIndex / n;
            int newCol = newIndex % n;
            result.get(newRow).set(newCol, flat[i]);
        }
        return result;
    }
}