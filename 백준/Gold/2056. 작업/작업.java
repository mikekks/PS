import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		List<Integer>[] prev = new List[10005];
		int[] work = new int[10005];
		int[] dp = new int[N + 1];

		for (int i = 0; i < 10005; i++)
			prev[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			input = br.readLine();
			String[] split = input.split(" ");

			work[i] = Integer.parseInt(split[0]);
			int cnt = Integer.parseInt(split[1]);

			for (int j = 0; j < cnt; j++) {
				prev[i].add(Integer.parseInt(split[j + 2]));
			}
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 0; j < prev[i].size(); j++) {
				Integer idx = prev[i].get(j);
				sum = Math.max(sum, dp[idx]);
			}

			dp[i] = sum + work[i];
		}

		int max = 0;
		for(int i=1; i<=N; i++){
			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
	}

}