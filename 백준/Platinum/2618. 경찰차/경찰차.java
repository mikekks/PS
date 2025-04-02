import java.io.*;


class Main {
	static int N, W, Q;
	static int[][] dp;
	static int[][] event;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		N = Integer.parseInt(st);

		dp = new int[1005][1005];
		event = new int[1005][2];
		event[0][0] = 1;
		event[0][1] = 1;

		st = br.readLine();
		W = Integer.parseInt(st);

		for(int i=1; i<=W; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			event[i][0] = Integer.parseInt(split[0]);
			event[i][1] = Integer.parseInt(split[1]);
		}

		dfs(1, 0,0);

		StringBuilder sb = new StringBuilder();
		sb.append(dp[0][0]).append("\n");

		int one = 0;
		int two = 0;

		for(int i=1; i<=W; i++){
			int dist = getDist(one, i, true);

			if(dp[one][two] - dist == dp[i][two]){
				one = i;
				sb.append(1).append("\n");
			}
			else{
				two = i;
				sb.append(2).append("\n");
			}
		}

		System.out.println(sb);

	}

	public static int dfs(int idx, int one, int two){

		if(idx > W) return 0;

		if(dp[one][two] != 0){
			return dp[one][two];
		}

		int first = dfs(idx + 1, idx, two) + getDist(one, idx, true);
		int second = dfs(idx + 1, one, idx) + getDist(two, idx, false);

		return dp[one][two] = Math.min(first, second);
	}

	public static int getDist(int p1, int p2, boolean isOne){
		int[] p1s = event[p1];
		int[] p2s = event[p2];

		if(p1 == 0 && !isOne){
			p1s = new int[] {N, N};
		}

		return Math.abs(p1s[0] - p2s[0]) + Math.abs(p1s[1] - p2s[1]);
	}
}
