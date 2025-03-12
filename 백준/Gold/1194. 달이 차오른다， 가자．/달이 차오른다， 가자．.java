import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Main {

	static class Node {
		int y;
		int x;
		int cost;
		int keys;

		public Node(int y, int x, int cost, int keys) {
			this.y = y;
			this.x = x;
			this.cost = cost;
			this.keys = keys;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		char[][] map = new char[N + 5][M + 5];

		int sy = 0, sx = 0;
		Set<String> ends = new HashSet<>();

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = st.charAt(j - 1);

				if (map[i][j] == '0') {
					sy = i;
					sx = j;
				}
				else if(map[i][j] == '1'){
					ends.add(i + " " + j);
				}
			}
		}

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		Queue<Node> q = new LinkedList<>();
		boolean[][][] visit = new boolean[N + 5][M + 5][1025];

		q.add(new Node(sy, sx, 0, 0));
		visit[sy][sx][0] = true;

		int ans = Integer.MAX_VALUE / 2;

		while (!q.isEmpty()) {
			Node node = q.poll();

			int nKeys = node.keys | getKeyIndex(map[node.y][node.x]);

			if(ends.contains(node.y + " " + node.x)){
				ans = Math.min(ans, node.cost);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = node.y + ud[i];
				int nx = node.x + lr[i];

				if (ny < 1 || nx < 1 || ny > N || nx > M || visit[ny][nx][nKeys])
					continue;

				if(map[ny][nx] == '#') continue;

				if(map[ny][nx] - '0' >= 17 && map[ny][nx] - '0' <= 22){
					char key = (char)(map[ny][nx] + 32);
					int keyIndex = getKeyIndex(key);

					if(nKeys != (nKeys | keyIndex)) continue;
				}

				q.add(new Node(ny, nx, node.cost + 1, nKeys));
				visit[ny][nx][nKeys] = true;
			}
		}

		if(ans == Integer.MAX_VALUE / 2){
			System.out.println(-1);
			return;
		}

		System.out.println(ans);
	}

	static int getKeyIndex(char c) {
		if (c == 'a') {
			return 1;
		} else if (c == 'b') {
			return 2;
		} else if (c == 'c') {
			return 4;
		} else if (c == 'd') {
			return 8;
		} else if (c == 'e') {
			return 16;
		} else if (c == 'f') {
			return 32;
		}

		return 0;
	}
}
