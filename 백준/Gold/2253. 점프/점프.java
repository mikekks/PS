import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int[][] dp = new int[N + 1][205];
		boolean[] isNo = new boolean[N + 1];
		int sqrtLimit = (int) Math.sqrt(2 * N) + 2;

		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(br.readLine());
			isNo[num] = true;
		}

		int INF = 987654321;
		for (int i = 0; i <= N; i++)
			for (int j = 0; j <= 200; j++)
				dp[i][j] = INF;

		dp[1][0] = 0;

		for (int i = 2; i <= N; i++) {
			if(isNo[i]) continue;
			for (int j = 1; j <= (int)Math.sqrt(2*i) + 1; j++) {
				int min = INF;
				if(i-j >= 0) min = Math.min(min, dp[i - j][j - 1]);
				if(i-j >= 0) min = Math.min(min, dp[i - j][j]);
				if(i-j >= 0 && j+1 < sqrtLimit) min = Math.min(min, dp[i - j][j + 1]);

				if(min != INF){
					dp[i][j] = min + 1;
				}
			}
		}

		int ans = INF;
		for (int j = 1; j <= 200; j++) {
			ans = Math.min(ans, dp[N][j]);
		}

		if (ans == INF) {
			ans = -1;
		}

		System.out.println(ans);
	}

}