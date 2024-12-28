import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	static long ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);
		s = br.readLine();
		int K = Integer.parseInt(s);

		long[][] dp = new long[1005][1005];
		long MOD = 1_000_000_003;

		for (int i = 1; i <= N; i++) {
			dp[i][1] = i;
			dp[i][0] = 1;
		}

		for (int i = 3; i <= N; i++) {
			for (int j = 2; j <= (i+1)/2; j++) {
				dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
			}
		}

		System.out.println((dp[N-3][K-1] + dp[N-1][K]) % MOD);

	}
}