import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		long[] dp = new long[5005];
		long MOD = 1000000;

		if (input.charAt(0) >= '1' && input.charAt(0) <= '9') {
			dp[0] = 1;
		}

		if (input.length() >= 2) {
			if (input.charAt(1) >= '1' && input.charAt(1) <= '9') {
				dp[1] = dp[0];
			}

			if (input.charAt(0) == '1') {
				dp[1] += 1;
			}
			else if(input.charAt(0) == '2'){
				if (input.charAt(1) <= '6') {
					dp[1] += 1;
				}
			}
		}

		for (int i = 2; i < input.length(); i++) {
			if(input.charAt(i) != '0'){
				dp[i] = (dp[i] + dp[i - 1]) % MOD;
			}

			if (input.charAt(i - 1) == '1') {
				dp[i] = (dp[i] + dp[i - 2]) % MOD;
			}
			else if(input.charAt(i - 1) == '2'){
				if (input.charAt(i) <= '6') {
					dp[i] = (dp[i] + dp[i - 2]) % MOD;
				}
			}
		}

		System.out.println(dp[input.length() - 1]);

	}

}