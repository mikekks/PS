import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int n = Integer.parseInt(s);

		s = br.readLine();
		String[] split = s.split(" ");

		int[] cost = new int[n];
		int[] reward = new int[n];

		for(int i=0; i<split.length; i++){
			cost[i] = Integer.parseInt(split[i]);
		}

		s = br.readLine();
		split = s.split(" ");

		for(int i=0; i<split.length; i++){
			reward[i] = Integer.parseInt(split[i]);
		}

		int[][] dp = new int[n][101];

		for(int i=0; i<n; i++){

			for(int j=0; j<=99; j++){
				if(i==0){
					if(j>=cost[i]){
						dp[i][j] = reward[i];
					}
				}else{
					if(j>=cost[i]){
						dp[i][j] = Math.max(dp[i-1][j-cost[i]]+reward[i], dp[i-1][j]);
					}else{
						dp[i][j] = dp[i-1][j];
					}
				}
			}
		}

		int ans = 0;
		for(int i=0; i<=99; i++){
			ans = Math.max(ans, dp[n-1][i]);
		}

		System.out.println(ans);
	}
}