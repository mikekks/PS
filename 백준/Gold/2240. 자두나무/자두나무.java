import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int T = Integer.parseInt(split[0]);
		int W = Integer.parseInt(split[1]);
		int[] award = new int[T + 1];
		award[0] = 1;

		for (int i = 1; i <= T; i++) {
			input = br.readLine();
			award[i] = Integer.parseInt(input);
		}

		int[][][] dp = new int[T + 1][W + 1][3];

		if (award[1] == 1) {
			dp[1][0][1] = 1;
			dp[1][1][2] = 0;
		} else {
			dp[1][0][1] = 0;
			dp[1][1][2] = 1;
		}

		for (int i = 2; i <= T; i++) {

			if (award[i] == 1) { // 가만히 있으면 보상 받는 경우
				dp[i][0][1] = dp[i - 1][0][1] + 1;
				dp[i][0][2] = dp[i - 1][0][2];

				for (int j = 1; j <= W; j++) {
					dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]) + 1;
					dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]);
				}

			} else if (award[i] == 2) {  // 움직여야 보상 받는 경우
				dp[i][0][1] = dp[i - 1][0][1];
				dp[i][0][2] = dp[i - 1][0][2] + 1;

				for (int j = 1; j <= W; j++) {
					dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
					dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]) + 1;
				}
			}
		}

		int ans = 0;

		for (int j = 0; j <= W; j++) {
			int tmp = Math.max(dp[T][j][1], dp[T][j][2]);
			ans = Math.max(tmp, ans);
		}

		System.out.println(ans);

	}

}