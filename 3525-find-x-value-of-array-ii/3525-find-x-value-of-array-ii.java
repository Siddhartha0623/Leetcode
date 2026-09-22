class Solution {
    private int n, k, KK, size;
    private int[] fullProd;
    private int[][] T;
    private int[] nums;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.n = nums.length;
        this.k = k;
        this.KK = k * k;
        this.size = 4 * Math.max(n, 1);

        this.fullProd = new int[size];
        this.T = new int[size][];

        if (n > 0) {
            build(1, 0, n - 1);
        }

        int q = queries.length;
        int[] result = new int[q];

        int startR = 1 % k;

        for (int i = 0; i < q; i++) {

            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            nums[idx] = val;

            update(1, 0, n - 1, idx, val);

            int[] res = query(
                1, 0, n - 1,
                start, n - 1,
                startR, x
            );

            result[i] = res[0];
        }

        return result;
    }

    private void leafVal(int node, int val) {

        int v = val % k;

        fullProd[node] = v;

        int[] arr = new int[KK];

        for (int r = 0; r < k; r++) {

            int x = (r * v) % k;

            arr[r * k + x] = 1;
        }

        T[node] = arr;
    }

    private void combine(int node, int left, int right) {

        int fpl = fullProd[left];

        fullProd[node] = (fpl * fullProd[right]) % k;

        int[] Tl = T[left];
        int[] Tr = T[right];

        int[] arr = new int[KK];

        for (int r = 0; r < k; r++) {

            int rl = r * k;

            int rr = ((r * fpl) % k) * k;

            for (int x = 0; x < k; x++) {

                arr[rl + x] =
                    Tl[rl + x] +
                    Tr[rr + x];
            }
        }

        T[node] = arr;
    }

    private void build(int node, int l, int r) {

        if (l == r) {

            leafVal(node, nums[l]);

            return;
        }

        int mid = (l + r) / 2;

        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);

        combine(node, 2 * node, 2 * node + 1);
    }

    private void update(int node, int l, int r, int idx, int val) {

        if (l == r) {

            leafVal(node, val);

            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {

            update(2 * node, l, mid, idx, val);

        } else {

            update(2 * node + 1, mid + 1, r, idx, val);
        }

        combine(node, 2 * node, 2 * node + 1);
    }

    // returns {count, newR}
    private int[] query(
        int node,
        int l,
        int r,
        int ql,
        int qr,
        int rIn,
        int x
    ) {

        if (qr < l || r < ql) {

            return new int[]{0, rIn};
        }

        if (ql <= l && r <= qr) {

            int[] arr = T[node];

            int cnt = arr[rIn * k + x];

            int newR =
                (rIn * fullProd[node]) % k;

            return new int[]{cnt, newR};
        }

        int mid = (l + r) / 2;

        int[] left =
            query(
                2 * node,
                l,
                mid,
                ql,
                qr,
                rIn,
                x
            );

        int[] right =
            query(
                2 * node + 1,
                mid + 1,
                r,
                ql,
                qr,
                left[1],
                x
            );

        return new int[]{
            left[0] + right[0],
            right[1]
        };
    }
}