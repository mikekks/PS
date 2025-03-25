import java.io.*;

class Main {
	static int N, Q;
	static long[] tree;
	static long[] arr;

	static long update(int idx, long updateValue, int node, int start, int end) {

		if (idx < start || idx > end)
			return 0;

		if (start == end) {
			tree[node] += updateValue;
			return updateValue;
		}

		int mid = (start + end) / 2;
		long lValue = update(idx, updateValue, 2 * node, start, mid);
		long rValue = update(idx, updateValue, 2 * node + 1, mid + 1, end);

		tree[node] += (lValue + rValue);
		return (lValue + rValue);
	}

	static long query(int node, int qLeft, int qRight, int curLeft, int curRight) {
		if (qRight < curLeft || qLeft > curRight)
			return 0;

		if (qLeft <= curLeft && curRight <= qRight)
			return tree[node];

		int mid = (curLeft + curRight) / 2;
		long lValue = query(2 * node, qLeft, qRight, curLeft, mid);
		long rValue = query(2 * node + 1, qLeft, qRight, mid + 1, curRight);

		return lValue + rValue;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		Q = Integer.parseInt(split[1]);

		tree = new long[4 * N + 5];
		arr = new long[N + 5];

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < Q; i++) {
			s = br.readLine();
			split = s.split(" ");

			int command = Integer.parseInt(split[0]);
			int p = Integer.parseInt(split[1]);
			int q = Integer.parseInt(split[2]);

			if (command == 1) {
				long updatedValue = arr[p] + q;
				update(p, updatedValue, 1, 1, N );
				//System.out.println("T" + query(1,1,N,1,N));
			} else {
				long query = query(1, p, q, 1, N );
				sb.append(query).append("\n");
			}
		}

		System.out.println(sb);

	}
}
