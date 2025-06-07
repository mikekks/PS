import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
	static int[] ud = {-1, 1, 0, 0};
	static int[] lr = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		String[] split = st.split(" ");
		int M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);

		int[][] map = new int[N+1][M+1];

		Queue<int[]> q = new LinkedList<>();

		int total = N * M;
		int curCnt = 0;
		int ans = 0;

		for(int i=0; i<N; i++){
			st = br.readLine();
			split = st.split(" ");

			for(int j=0; j<split.length; j++){
				int t = Integer.parseInt(split[j]);
				map[i + 1][j + 1] = t;

				if(t == 1){
					q.add(new int[] {i + 1, j + 1, 0});
				}
				if(t == -1){
					total--;
				}
			}
		}

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int cy = poll[0];
			int cx = poll[1];
			int day = poll[2];

			ans = Math.max(ans, day);
			curCnt++;

			for(int i=0; i<4; i++){
				int ny = cy + ud[i];
				int nx = cx + lr[i];

				if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
				if(map[ny][nx] == -1 || map[ny][nx] == 1) continue;

				map[ny][nx] = 1;
				q.add(new int[] {ny, nx, day + 1});
			}
		}

		if(curCnt != total){
			System.out.println(-1);
			return;
		}

		System.out.println(ans);

	}

}
