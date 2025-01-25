import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		K = Integer.parseInt(split[1]);

		boolean[][] dp = new boolean[N + 1][N + 1];
		// int INF = Integer.MAX_VALUE / 2;
		//
		// for(int i=1; i<=N; i++){
		// 	for(int j=1; j<=N; j++){
		// 		dp[i][j] = INF;
		// 	}
		// }

		for(int i=0; i<K; i++){
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			dp[a][b] = true;
		}
		for(int k=1; k<=N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dp[i][k] && dp[k][j]) {
						dp[i][j] = true;
					}
				}
			}
		}

		s = br.readLine();
		int S = Integer.parseInt(s);

		StringBuilder sb = new StringBuilder();

		for(int i=0; i<S; i++){
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			if(dp[a][b] || dp[b][a]){
				if(dp[a][b]){
					sb.append(-1 + "\n");
				}
				else{
					sb.append(1 + "\n");
				}
			}
			else{
				sb.append(0 + "\n");
			}
		}

		System.out.println(sb);

	}
}