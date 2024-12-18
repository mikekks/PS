import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		boolean[][] isLight = new boolean[N + 1][N + 1];
		isLight[1][1] = true;

		ArrayList<Point>[][] map = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			s = br.readLine();
			split = s.split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			int a = Integer.parseInt(split[2]);
			int b = Integer.parseInt(split[3]);

			map[y][x].add(new Point(b, a));
		}

		Queue<Point> q = new LinkedList<>();

		q.add(new Point(1, 1));
		boolean[][] visit = new boolean[N + 1][N + 1];
		visit[1][1] = true;

		int ans = 1;
		int next_ans = 1;

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		do {
			ans = next_ans;
			Queue<Point> next_q = new LinkedList<>();

			while (!q.isEmpty()) {
				Point p = q.poll();

				if (isLight[p.y][p.x] == false) {
					next_q.add(new Point(p.y, p.x));
					visit[p.y][p.x] = true;
					continue;
				}

				for (int k = 0; k < map[p.y][p.x].size(); k++) {
					Point light = map[p.y][p.x].get(k);

					if(isLight[light.y][light.x]) continue;

					isLight[light.y][light.x] = true;
					next_ans++;
				}

				for (int i = 0; i < 4; i++) {
					int ny = p.y + ud[i];
					int nx = p.x + lr[i];

					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;
					if (visit[ny][nx])
						continue;
					if (isLight[ny][nx] == false) {
						next_q.add(new Point(ny, nx));
						visit[ny][nx] = true;
						continue;
					}

					q.add(new Point(ny, nx));
					visit[ny][nx] = true;
				}
			}

			q = next_q;

		} while ((next_ans > ans));



		System.out.println(ans);

	}
}