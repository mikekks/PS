import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	static int[] p;
	static int mergeCount = 0;
	static int ans = 0;

	static int findP(int n) {
		if (p[n] == n)
			return n;

		return p[n] = findP(p[n]);
	}

	static void merge(int a, int b, int cost) {
		a = findP(a);
		b = findP(b);

		if (a != b) {
			int parent = Math.min(a, b);
			int child = Math.max(a, b);
			p[child] = parent;
			mergeCount++;
			ans += cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);

		st = br.readLine();
		String[] split = st.split(" ");

		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1];
		for (int i = 1; i <= split.length; i++) {
			arr[i] = Integer.parseInt(split[i - 1]);
			sum[i] = sum[i - 1] + arr[i];
		}

		st = br.readLine();
		int K = Integer.parseInt(st);

		int[] stack = new int[N + 1];

		for (int i = 1; i <= N - K + 1; i++) {
			stack[i] = sum[i + K - 1] - sum[i - 1];
		}
		for (int i = N - K + 2; i <= N; i++) {
			stack[i] = sum[N] - sum[i - 1];
		}

		int[][] dp = new int[N + 1][4];

		for (int i = 1; i <= K; i++) {
			dp[i][0] = 0;
			dp[i][1] = stack[i];
		}

		for (int i = K + 1; i <= N; i++) {
			dp[i][0] = dp[i - 1][0];

			dp[i][1] = Math.max(dp[i - K][0] + stack[i], dp[i - 1][1]);
			dp[i][2] = Math.max(dp[i - K][1] + stack[i], dp[i - 1][2]);
			dp[i][3] = Math.max(dp[i - K][2] + stack[i], dp[i - 1][3]);
		}

		System.out.println(dp[N][3]);

	}
}
