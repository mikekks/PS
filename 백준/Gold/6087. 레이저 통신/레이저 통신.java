import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {
	static int ans = Integer.MAX_VALUE / 2;
	static int W, H;
	static char[][] map;
	static int[] ud = {-1, 0, 1, 0};
	static int[] lr = {0, -1, 0, 1};

	static class Point{
		int y;
		int x;
		int mCnt;
		int arrow;

		public Point(int y, int x, int mCnt, int arrow) {
			this.y = y;
			this.x = x;
			this.mCnt = mCnt;
			this.arrow = arrow;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");

		W = Integer.parseInt(split[0]);
		H = Integer.parseInt(split[1]);

		map = new char[H + 1][W + 1];

		List<int[]> target = new ArrayList<>();

		for(int i=1; i<=H; i++){
			st = br.readLine();

			for(int j=1; j<=W; j++){
				map[i][j] = st.charAt(j - 1);

				if(map[i][j] == 'C'){
					target.add(new int[] {i, j});
				}
			}
		}

		PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.mCnt, o2.mCnt);
			}
		});

		int[][][] dp = new int[H + 1][W + 1][4];

		for(int i=1; i<=H; i++){
			for(int j=1; j<=W; j++){
				for(int k=0; k<4; k++){
					dp[i][j][k] = Integer.MAX_VALUE / 2;
				}
			}
		}

		for(int i=0; i<4; i++){
			q.add(new Point(target.get(0)[0], target.get(0)[1], 0, i));
			dp[target.get(0)[0]][target.get(0)[1]][i] = 0;
		}

		int ey = target.get(1)[0];
		int ex = target.get(1)[1];

		while (!q.isEmpty()){
			Point poll = q.poll();
			int y = poll.y;
			int x = poll.x;
			int arrow = poll.arrow;

			if(y == ey && x == ex){
				System.out.println(poll.mCnt);
				return;
			}

			for(int i=0; i<4; i++){
				if(Math.abs(arrow - i) == 2) continue;

				int ny = y + ud[i];
				int nx = x + lr[i];
				int nMCnt = arrow == i ? poll.mCnt : poll.mCnt + 1;

				if(ny < 1 || nx < 1 || ny > H || nx > W) continue;
				if(map[ny][nx] == '*') continue;
				if(nMCnt >= dp[ny][nx][i]) continue;

				q.add(new Point(ny, nx, nMCnt, i));
				dp[ny][nx][i] = nMCnt;
			}
		}
	}
}
