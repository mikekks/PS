import java.io.*;

class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[31]; // 한개, 반개

		dp[0] = 1;

		for (int i = 1; i <= 30; i++) {
			long cnt = 0;
			for (int j = 0; j < i; j++) {
				cnt += dp[j] * dp[i - j - 1];
			}
			dp[i] = cnt;
		}

		StringBuilder sb = new StringBuilder();

		while (true) {
			String st = br.readLine();
			N = Integer.parseInt(st);

			if (N == 0)
				break;

			sb.append(dp[N] + "\n");
		}

		System.out.println(sb);

	}
}
