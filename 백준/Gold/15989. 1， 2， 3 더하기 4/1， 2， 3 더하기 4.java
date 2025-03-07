import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);
		
		int[][] dp = new int[10005][4];
		dp[1][1] = 1;
		dp[2][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 4; i <= 10000; i++) {
			dp[i][1] = dp[i - 1][1];
			dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
			dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
		}

		while (N > 0) {
			N--;
			s = br.readLine();
			int target = Integer.parseInt(s);

			System.out.println(dp[target][1] + dp[target][2] + dp[target][3]);
		}

	}

}

