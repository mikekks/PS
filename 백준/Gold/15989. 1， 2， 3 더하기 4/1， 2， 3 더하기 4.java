import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		int[] num = {1, 2, 3};
		int[] dp = new int[10005];
		dp[0] = 1;

		for(Integer cur : num){
			for(int i=cur; i<=10000; i++){
				dp[i] += dp[i - cur];
			}
		}
		
		while (N > 0) {
			N--;
			s = br.readLine();
			int target = Integer.parseInt(s);

			System.out.println(dp[target]);
		}

	}

}

