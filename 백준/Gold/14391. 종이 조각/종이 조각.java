import java.io.*;

class Main {

	static int ans = 0;
	static int N, M;
	static int[][] map;
	static boolean[][] visit;

	static void dfs(int idx, int total) {

		if (idx == N * M + 1) {
			ans = Math.max(ans, total);
			return;
		}

		int y = (idx - 1) / M + 1;
		int x = (idx - 1) % M + 1;

		if (visit[y][x]) {
			dfs(idx + 1, total);
			return;
		}

		for (int i = y; i <= N; i++) {
			if (visit[i][x])
				break;

			int num = 0;
			for (int j = y; j <= i; j++) {

				visit[j][x] = true;
				num = num * 10 + map[j][x];
			}

			dfs(idx + 1, total + num);

			for (int j = y; j <= i; j++) {
				visit[j][x] = false;
			}
		}

		for (int i = x; i <= M; i++) {
			if (visit[y][i])
				break;

			int num = 0;
			for (int j = x; j <= i; j++) {
				visit[y][j] = true;
				num = num * 10 + map[y][j];
			}

			dfs(idx + 1, total + num);

			for (int j = x; j <= i; j++) {
				visit[y][j] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		map = new int[N + 1][M + 1];
		visit = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
			}
		}

		dfs(1, 0);

		System.out.println(ans);
	}
}
