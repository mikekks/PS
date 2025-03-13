import java.io.*;

class Main {

	static int ans = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);

		st = br.readLine();
		String[] split = st.split(" ");

		int[] scv = new int[3];

		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(split[i]);
		}

		int[][][] dp = new int[140][140][140];  // 1,2,3 / 1,3,2 / 2,1,3 / 2,3,1 / 3,1,2 / 3,2,1

		for (int i = 139; i >= 0; i--)
			for (int j = 139; j >= 0; j--)
				for (int k = 139; k >= 0; k--)
					dp[i][j][k] = Integer.MAX_VALUE / 2;

		dp[scv[0] + 60][scv[1] + 60][scv[2] + 60] = 0;

		for (int i = scv[0]+59; i >= 0; i--) {
			for (int j = scv[1]+59; j >= 0; j--) {
				for (int k = scv[2]+59; k >= 0; k--) {
					int ret = Integer.MAX_VALUE / 2;
					ret = Math.min(dp[i + 9][j + 3][k + 1] + 1, ret);
					ret = Math.min(dp[i + 9][j + 1][k + 3] + 1, ret);

					ret = Math.min(dp[i + 3][j + 9][k + 1] + 1, ret);
					ret = Math.min(dp[i + 1][j + 9][k + 3] + 1, ret);

					ret = Math.min(dp[i + 3][j + 1][k + 9] + 1, ret);
					ret = Math.min(dp[i + 1][j + 3][k + 9] + 1, ret);

					dp[i][j][k] = ret;
				}
			}
		}


		for(int i=0; i<=60; i++){
			for(int j=0; j<=60; j++){
				for(int k=0; k<=60; k++){
					ans = Math.min(ans, dp[i][j][k]);
				}
			}
		}

		System.out.println(ans);

	}
}
