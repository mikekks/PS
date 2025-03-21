import java.io.*;
import java.util.*;

class Main {
	static int N, M, P;
	static int[][] map;
	static int[] maxCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		P = Integer.parseInt(split[2]);

		List<int[]>[] nextList = new List[P + 1];

		for (int i = 1; i <= P; i++) {
			nextList[i] = new ArrayList<>();
		}

		maxCount = new int[P + 1];
		map = new int[N + 1][M + 1];
		st = br.readLine();
		split = st.split(" ");

		for (int i = 1; i <= P; i++) {
			maxCount[i] = Integer.parseInt(split[i - 1]);
		}

		for (int i = 1; i <= N; i++) {
			st = br.readLine();

			for (int j = 1; j <= M; j++) {
				char ch = st.charAt(j - 1);

				if (ch == '.') {
					map[i][j] = 0;
				} else if (ch == '#') {
					map[i][j] = -1;
				} else {
					map[i][j] = ch - '0';
					nextList[map[i][j]].add(new int[] {i, j});
				}
			}
		}

		int[] pAns = new int[P + 1];

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		while (true) {
			boolean isNext = false;

			Queue<int[]> q = new LinkedList<>();
			for (int p = 1; p <= P; p++) {
				for (int[] next : nextList[p]) {
					int ny = next[0];
					int nx = next[1];

					q.add(new int[] {ny, nx, 0});
				}
				nextList[p].clear();

				while (!q.isEmpty()) {
					int[] poll = q.poll();
					int y = poll[0];
					int x = poll[1];
					int cnt = poll[2];

					// cnt 체크
					if(cnt >= maxCount[p]){
						nextList[p].add(new int[] {y, x});
						continue;
					}

					pAns[p]++;
					isNext = true;

					for (int i = 0; i < 4; i++) {
						int ny = y + ud[i];
						int nx = x + lr[i];

						if (ny < 1 || nx < 1 || ny > N || nx > M)
							continue;
						if (map[ny][nx] != 0)
							continue;

						q.add(new int[] {ny, nx, cnt + 1});
						map[ny][nx] = p;
					}
				}

			}

			if (!isNext) {
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= P; i++) {
			sb.append(pAns[i] + " ");
		}

		System.out.println(sb);

	}
}
