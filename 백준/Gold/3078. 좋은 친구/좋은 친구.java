import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] sum = new int[N + 1][25];
		String[] people = new String[N];

		String s = br.readLine();
		sum[0][s.length()] += 1;
		people[0] = s;

		for (int i = 1; i < N; i++) {
			s = br.readLine();
			people[i] = s;

			for (int j = 2; j <= 20; j++) {
				sum[i][j] = sum[i - 1][j];

				if (s.length() == j) {
					sum[i][j]++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			int len = people[i].length();

			int max = i + K;
			if (max >= N) {
				max = N-1;
			}

			ans += (sum[max][len] - sum[i][len]);
		}

		System.out.println(ans);

	}

}