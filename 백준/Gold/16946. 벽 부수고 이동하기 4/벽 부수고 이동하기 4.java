import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Main {

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[][] map = new int[N + 5][M + 5];
		int[][] gMap = new int[N + 5][M + 5];

		for (int i = 1; i <= N; i++) {
			st = br.readLine();

			for (int j = 1; j <= M; j++) {
				map[i][j] = st.charAt(j - 1) - '0';
			}
		}

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		boolean[][] visit = new boolean[N + 5][M + 5];
		int gId = 0;
		int[] total = new int[N * M + 5];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(visit[i][j] || map[i][j] == 1) continue;

				Queue<Node> q = new LinkedList<>();
				q.add(new Node(i, j));
				visit[i][j] = true;
				gId++;
				int cnt = 0;

				while (!q.isEmpty()) {
					Node curNode = q.poll();
					int cy = curNode.y;
					int cx = curNode.x;

					gMap[cy][cx] = gId;
					cnt++;

					for(int k=0; k<4; k++){
						int ny = cy + ud[k];
						int nx = cx + lr[k];

						if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
						if(visit[ny][nx] || map[ny][nx] == 1) continue;

						q.add(new Node(ny, nx));
						visit[ny][nx] = true;
					}
				}

				total[gId] = cnt;
			}
		}


		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] != 1){
					sb.append("0");
					continue;
				}

				int cnt = 1;
				Set<Integer> gVisit = new HashSet<>();
				for(int k=0; k<4; k++){
					int ny = i + ud[k];
					int nx = j + lr[k];

					if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
					if(gVisit.contains(gMap[ny][nx])) continue;

					cnt += total[gMap[ny][nx]];
					gVisit.add(gMap[ny][nx]);
				}
				sb.append(cnt % 10);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
