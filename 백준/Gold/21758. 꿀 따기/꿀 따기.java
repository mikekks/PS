import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);

		s = br.readLine();
		String[] split = s.split(" ");

		long[] arr = new long[N];
		long[] sum = new long[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(split[i]);
			if (i != 0) {
				sum[i] = sum[i - 1] + arr[i];
			} else {
				sum[0] = arr[0];
			}
		}

		long answer = 0;

		long case1_a = sum[N - 1] - arr[0];
		for (int i = 1; i < N - 1; i++) {
			long case1_b = sum[N - 1] - sum[i];
			answer = Math.max(case1_a + case1_b - arr[i], answer);
		}

		long case2_a = sum[N - 2];
		for (int i = 1; i < N - 1; i++) {
			long case2_b = sum[i - 1];
			answer = Math.max(case2_a + case2_b - arr[i], answer);
		}

		for (int i = 1; i < N - 1; i++) {
			long case3_a = sum[i] - arr[0];
			long case3_b = sum[N - 2] - sum[i - 1];
			answer = Math.max(case3_a + case3_b, answer);
		}

		System.out.println(answer);

	}
}