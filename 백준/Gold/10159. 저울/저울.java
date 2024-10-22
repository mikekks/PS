import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		input = br.readLine();
		int M = Integer.parseInt(input);

		int[][] dp = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			input = br.readLine();
			String[] split = input.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			dp[a][b] = 1;
			dp[b][a] = -1;
		}

		for (int i = 1; i <= N; i++) {
			dp[i][i] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (Math.abs(dp[i][k] + dp[k][j]) == 2) {
						dp[i][j] = dp[i][k];
						dp[j][i] = -dp[i][k];
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if(dp[i][j] == 0) cnt++;
			}

			System.out.println(cnt);
		}

	}
}
