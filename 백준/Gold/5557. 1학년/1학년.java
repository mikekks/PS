import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
	int n;
	int time;

	public Node(int n, int time) {
		this.n = n;
		this.time = time;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		input = br.readLine();
		String[] split = input.split(" ");

		long[][] dp = new long[N][21];
		int init = Integer.parseInt(split[0]);
		int target = Integer.parseInt(split[split.length-1]);
		dp[0][init] = 1;

		for (int i = 1; i < N-1; i++) {
			int cur = Integer.parseInt(split[i]);

			for (int k = 0; k <= 20; k++) {
				if (k - cur >= 0) {
					dp[i][k - cur] += dp[i - 1][k];
				}

				if (k + cur <= 20) {
					dp[i][k + cur] += dp[i - 1][k];
				}
			}
		}

		System.out.println(dp[N-2][target]);
	}

}