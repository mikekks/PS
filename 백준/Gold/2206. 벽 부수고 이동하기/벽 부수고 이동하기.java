import java.io.*;
import java.util.*;

class Main {
	static int N, M, P;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = br.readLine();

			for (int j = 1; j <= M; j++) {
				map[i][j] = st.charAt(j - 1) - '0';
			}
		}

		int[][][] dp = new int[N + 1][M + 1][2];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j][0] = Integer.MAX_VALUE / 2;
				dp[i][j][1] = Integer.MAX_VALUE / 2;
			}
		}

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] {1, 1, 1, 0});

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int y = poll[0];
			int x = poll[1];
			int cost = poll[2];
			int broke = poll[3];

			if(y == N && x == M){
				System.out.println(cost);
				return;
			}

			for(int i=0; i<4; i++){
				int ny = y + ud[i];
				int nx = x + lr[i];
				int nBroke = broke;
				if(ny < 1 || nx < 1 || ny > N || nx > M) continue;

				if(map[ny][nx] == 1){
					if(nBroke == 1){
						continue;
					}
					nBroke = 1;
				}

				if(cost + 1 >= dp[ny][nx][nBroke]) continue;

				dp[ny][nx][nBroke] = cost + 1;
				q.add(new int[] {ny, nx, cost + 1, nBroke});
			}
		}

		System.out.println(-1);
	}
}
