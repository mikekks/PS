import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1];

		for(int i=1; i<=N; i++){
			arr[i] = Integer.parseInt(br.readLine());
			sum[i] = sum[i-1] + arr[i];
		}

		int[][] dp = new int[N + 1][M + 1];

		for(int i=0; i<=N; i++){
			for(int j=1; j<=M; j++){
				dp[i][j] = Integer.MIN_VALUE / 2;
			}
		}

		dp[1][1] = arr[1];
		for(int n=2; n<=N; n++){
			for(int m=1; m<=M; m++){

				dp[n][m] = dp[n-1][m];

				int min = 0;
				if(m == 1) min = -1;

				for(int k=n-2; k>=min; k--){
					if(k < 0){
						dp[n][m] = Math.max(dp[n][m], sum[n]);
						continue;
					}

					dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + sum[n] - sum[k + 1]);
				}

			}
		}

		System.out.println(dp[N][M]);
	}

}