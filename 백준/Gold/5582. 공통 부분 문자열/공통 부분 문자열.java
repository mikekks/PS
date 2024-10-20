import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();

		int answer = 0;

		int[][] dp = new int[s1.length()+1][s2.length()+1];

		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
					answer = dp[i+1][j+1] > answer ? dp[i+1][j+1] : answer;
				}

			}
		}

		System.out.println(answer);
	}
}
