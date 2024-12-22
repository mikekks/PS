import java.io.*;

class Main {

	static int N;
	static long[] arr;
	static long[] tree;

	public static long init(int s, int e, int idx) {

		if (s == e) {
			return tree[idx] = arr[s];
		}

		int mid = (s + e) / 2;

		long lValue = init(s, mid, 2 * idx);
		long rValue = init(mid + 1, e, 2 * idx + 1);

		return tree[mid] = lValue + rValue;
	}

	public static void modify(int s, int e, int idx, int node, long diff) {

		if (idx < s || idx > e)
			return;

		tree[node] += diff;

		if (s == e) {
			return;
		}

		int mid = (s + e) / 2;
		modify(s, mid, idx, node * 2, diff);
		modify(mid + 1, e, idx, node * 2 + 1, diff);
	}

	public static long sum(int ts, int te, int cs, int ce, int node) {

		if (cs > te || ce < ts) {
			return 0;
		} else if (ts <= cs && ce <= te) {
			return tree[node];
		}

		int mid = (cs + ce) / 2;
		long lValue = sum(ts, te, cs, mid, node * 2);
		long RValue = sum(ts, te, mid + 1, ce, node * 2 + 1);

		return lValue + RValue;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		tree = new long[N * 5];
		arr = new long[N + 1];

		init(0, N - 1, 1);

		for (int k = 0; k < M; k++) {
			s = br.readLine();
			split = s.split(" ");
			int i = Integer.parseInt(split[1]);
			int j = Integer.parseInt(split[2]);

			if (split[0].equals("0")) {  // sum
				long ret = sum(Math.min(i, j)-1, Math.max(i, j)-1, 0, N - 1, 1);
				System.out.println(ret);
			} else {  // modify
				long diff = j - arr[i];
				arr[i] = j;
				modify(0, N - 1, i-1, 1, diff);
			}
		}

	}
}