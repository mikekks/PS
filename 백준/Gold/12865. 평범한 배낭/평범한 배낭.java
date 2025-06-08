import java.io.*;

class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		String[] split = st.split(" ");
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		int[][] dp = new int[N + 5][K + 5];

		int[][] bag = new int[N][2];

		int ans = 0;

		for (int i = 0; i < N; i++) {
			st = br.readLine();
			split = st.split(" ");

			int w = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			bag[i][0] = w;
			bag[i][1] = v;
		}

		for (int i = 1; i <= N; i++) {
			int w = bag[i - 1][0];
			int v = bag[i - 1][1];

			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - w >= 0 && dp[i - 1][j - w] + v > dp[i][j]) {
					dp[i][j] = dp[i - 1][j - w] + v;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}

		System.out.println(ans);
	}

}
