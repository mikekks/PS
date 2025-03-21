import java.io.*;


class Main {

	static int ans = Integer.MAX_VALUE / 2;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		map = new int[N + 1][N + 1];

		for(int i=1; i<=N; i++){
			st = br.readLine();
			String[] split = st.split(" ");
			for(int j=1; j<=N; j++){
				map[i][j] = Integer.parseInt(split[j - 1]);
			}
		}

		long[][][] dp = new long[N + 1][N + 1][3];
		dp[1][2][0] = 1;

		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				if(i == 1 && j == 1) continue;
				if(i == 1 && j == 2) continue;

				if(map[i][j] == 1) continue;

				// 가로
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

				// 세로
				dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

				// 대각선
				if(map[i][j-1] == 0 && map[i-1][j] == 0){
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);

	}
}
