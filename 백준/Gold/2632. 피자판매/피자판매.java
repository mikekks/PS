import java.io.*;

class Main {
	static int N, M, Q;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		int Q = Integer.parseInt(st);

		st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[] aList = new int[N + 1];
		int[] bList = new int[M + 1];
		int[] aSum = new int[N + 1];
		int[] bSum = new int[M + 1];

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			int num = Integer.parseInt(st);
			aList[i] = num;
			aSum[i] = aSum[i - 1] + aList[i];
		}

		for (int i = 1; i <= M; i++) {
			st = br.readLine();
			int num = Integer.parseInt(st);
			bList[i] = num;
			bSum[i] = bSum[i - 1] + bList[i];
		}

		// A,B 모두 합친 수보다 큰 요청일 경우
		if (Q > aSum[N] + bSum[M]) {
			System.out.println(0);
			return;
		}

		int[] aDp = makeDp(aSum, Q);
		int[] bDp = makeDp(bSum, Q);

		long ans = 0;

		for (int i = 1; i < Q; i++) {
			int aValue = i;
			int bValue = Q - i;

			ans += aDp[aValue] * bDp[bValue];
		}

		ans += aDp[Q];
		ans += bDp[Q];

		System.out.println(ans);

	}

	public static int[] makeDp(int[] sum, int target) {
		int[] dp = new int[2_000_005];
		int size = sum.length - 1;

		for (int next = 0; next <= size - 2; next++) {
			for (int j = 1; j <= size; j++) {
				long cur = 0;
				if (j + next > size) {
					cur = sum[size] - sum[j-1] + sum[(j + next) % size];
				}else{
					cur = sum[j + next] - sum[j - 1];
				}

				if (cur > target)
					continue;

				dp[(int)cur]++;
			}
		}

		dp[sum[size]]++;

		return dp;
	}
}
