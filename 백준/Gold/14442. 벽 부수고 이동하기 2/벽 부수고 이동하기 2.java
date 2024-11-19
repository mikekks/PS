import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Point {
	int y;
	int x;
	int move;
	int bCnt;

	public Point(int y, int x, int move, int bCnt) {
		this.y = y;
		this.x = x;
		this.move = move;
		this.bCnt = bCnt;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int K = Integer.parseInt(split[2]);
		int[][] map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			input = br.readLine();
			for (int j = 1; j <= input.length(); j++) {
				map[i][j] = input.charAt(j - 1) - '0';
			}
		}

		PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.move - o2.move;
			}
		});

		int[][][] dp = new int[N + 1][M + 1][K + 2];
		int INF = 987654321;
		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				for (int k = 0; k <= K; k++)
					dp[i][j][k] = INF;

		q.add(new Point(1, 1, 1, 0));
		dp[1][1][0] = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int cy = p.y;
			int cx = p.x;
			int cMove = p.move;
			int cBCnt = p.bCnt;

			if (cy == N && cx == M) {
				System.out.println(cMove);
				return;
			}
			if (cMove > dp[cy][cx][cBCnt])
				continue;

			for (int i = 0; i < 4; i++) {
				int ny = cy + ud[i];
				int nx = cx + lr[i];

				if (ny < 1 || nx < 1 || ny > N || nx > M)
					continue;

				if (map[ny][nx] == 1) {
					if (cBCnt + 1 > K || cMove + 1 >= dp[ny][nx][cBCnt + 1]) {
						continue;
					}
					q.add(new Point(ny, nx, cMove + 1, cBCnt + 1));
					dp[ny][nx][cBCnt + 1] = cMove + 1;
				} else {
					if(cMove + 1 >= dp[ny][nx][cBCnt]){
						continue;
					}
					q.add(new Point(ny, nx, cMove + 1, cBCnt));
					dp[ny][nx][cBCnt] = cMove + 1;
				}

			}
		}

		System.out.println(-1);
	}

}