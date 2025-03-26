import java.io.*;

class Main {
	static int N, Q;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		int N = Integer.parseInt(st);

		if(N < 10){
			System.out.println(0);
			return;
		}

		long[][][] dp = new long[N + 1][10][1024];
		long MOD = 1_000_000_000L;

		for(int i=1; i<=9; i++){
			dp[1][i][getVisit(0, i)] = 1;
		}

		for(int i=2; i<=N; i++){
			for(int j=0; j<=9; j++){
				for(int k=0; k<=1023; k++){

					if((k | (1 << j)) != k) continue;

					if(j > 0){
						dp[i][j][k] += dp[i-1][j-1][k];
						dp[i][j][k] %= MOD;
						dp[i][j][k] += dp[i-1][j-1][extractBit(k, j)];
						dp[i][j][k] %= MOD;
					}
					if(j < 9){
						dp[i][j][k] += dp[i-1][j+1][k];
						dp[i][j][k] %= MOD;
						dp[i][j][k] += dp[i-1][j+1][extractBit(k, j)];
						dp[i][j][k] %= MOD;
					}

				}
			}
		}

		long ans = 0;
		for(int i=0; i<=9; i++){
			ans = (ans + dp[N][i][1023]) % MOD;
		}

		System.out.println(ans);
	}

	public static int getVisit(int prev, int target){
		return prev | (1 << target);
	}

	public static int extractBit(int prev, int target){
		return prev & ~(1 << target);
	}
}
