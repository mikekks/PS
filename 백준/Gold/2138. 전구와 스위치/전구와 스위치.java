import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int[] arr;
	static int[] target;
	static int[][] dp;
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		target = new int[N];
		dp = new int[N][2];

		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			arr[i] = s.charAt(i) - '0';
		}

		s = br.readLine();
		for (int i = 0; i < N; i++) {
			target[i] = s.charAt(i) - '0';
		}

		int[] new_arr = Arrays.copyOf(arr, N);
		new_arr[0] = 1 - new_arr[0];
		new_arr[1] = 1 - new_arr[1];

		int cnt1 = solve(N, arr, target);
		int cnt2 = solve(N, new_arr, target);
		if(cnt2 != -1) cnt2++;

		if(cnt1 == -1){
			System.out.println(cnt2);
			return;
		}

		if(cnt2 == -1){
			System.out.println(cnt1);
			return;
		}

		System.out.println(Math.min(cnt1, cnt2));
	}

	private static int solve(int n, int[] a, int[] b) {
		int cnt = 0;

		for (int i = 0; i < n-1; i++) {
			if (a[i]!=b[i]) {
				cnt++;
				a[i] = 1-a[i];
				a[i+1] = 1-a[i+1];
				if (i != n-2)
					a[i+2] = 1-a[i+2];
			}
		}

		return a[n-1]!=b[n-1] ? -1 : cnt;

	}
}