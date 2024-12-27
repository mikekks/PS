import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int K = Integer.parseInt(split[2]);

		long[][] dp = new long[205][205];

		for (int i = 0; i <= 200; i++) {
			dp[i][1] = i;
			dp[i][0] = 1;
			dp[i][i] = 1;
		}

		for (int i = 1; i <= 200; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];

				if (dp[i][j] > 1000000000) {
					dp[i][j] = 1000000001;
				}
			}
		}

		if (dp[N + M][N] < K) {
			System.out.println(-1);
			return;
		}

		StringBuilder sb = new StringBuilder();

		while (!(N == 0 && M == 0)) {

			if (dp[N + M - 1][M] >= K) {
				sb.append("a");
				N--;
			} else {
				K = K - (int)dp[N + M - 1][M];
				sb.append("z");
				M--;
			}
		}

		System.out.println(sb);

	}
}