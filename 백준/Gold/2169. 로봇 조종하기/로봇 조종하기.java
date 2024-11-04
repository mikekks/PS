import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[][] map = new int[1005][1005];
		int[][] dp = new int[1005][1005];

		for (int i = 1; i <= N; i++) {
			input = br.readLine();
			split = input.split(" ");

			for (int j = 0; j < M; j++) {
				map[i][j + 1] = Integer.parseInt(split[j]);
			}
		}

		dp[1][1] = map[1][1];
		for (int i = 2; i <= M; i++) {
			dp[1][i] = dp[1][i - 1] + map[1][i];
		}

		for (int i = 2; i <= N; i++) {

			int[][] tmp = new int[2][M + 1];

			tmp[0][1] = dp[i-1][1] + map[i][1];
			for (int j = 2; j <= M; j++) {
				tmp[0][j] = Math.max(dp[i - 1][j], tmp[0][j-1]) + map[i][j];
			}

			tmp[1][M] = dp[i-1][M] + map[i][M];
			for (int j = M-1; j >= 1; j--) {
				tmp[1][j] = Math.max(dp[i - 1][j], tmp[1][j+1]) + map[i][j];
			}

			for (int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
			}

		}

		System.out.println(dp[N][M]);

	}

}