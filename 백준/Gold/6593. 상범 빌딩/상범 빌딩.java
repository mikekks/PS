import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node{
	int y;
	int x;
	int h;
	int cost;

	public Node(int y, int x, int h, int cost) {
		this.y = y;
		this.x = x;
		this.h = h;
		this.cost = cost;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true){
			String s = br.readLine();
			String[] split = s.split(" ");

			int L = Integer.parseInt(split[0]);
			int R = Integer.parseInt(split[1]);
			int C = Integer.parseInt(split[2]);

			if(L == 0 && R == 0 && C == 0) break;

			char[][][] map = new char[L + 1][R + 1][C + 1];

			int sy = 0, sx = 0, sh = 0;
			int ey = 0, ex = 0, eh = 0;

			for(int i=1; i<=L; i++){
				for(int j=1; j<=R; j++){
					s = br.readLine();
					for(int k=1; k<=C; k++){
						map[i][j][k] = s.charAt(k - 1);

						if(map[i][j][k] == 'S'){
							sh = i;
							sy = j;
							sx = k;
						}
						else if(map[i][j][k] == 'E'){
							eh = i;
							ey = j;
							ex = k;
						}
					}
				}
				s = br.readLine();
			}

			PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.cost - o2.cost;
				}
			});

			int[][][] dp = new int[L + 1][R + 1][C + 1];
			int[] hh = {-1, 1, 0, 0, 0, 0};
			int[] ud = {0, 0, -1, 1, 0, 0};
			int[] lr = {0, 0, 0, 0, -1, 1};
			int INF = 987654321;

			for(int i=1; i<=L; i++) {
				for (int j = 1; j <= R; j++) {
					for (int k = 1; k <= C; k++) {
						dp[i][j][k] = INF;
					}
				}
			}
			dp[sh][sy][sx] = 0;
			q.add(new Node(sy, sx, sh, 0));

			int ans = -1;

			while(!q.isEmpty()){
				Node cur = q.poll();
				int cy = cur.y;
				int cx = cur.x;
				int ch = cur.h;

				if(cy == ey && cx == ex && ch == eh){
					ans = cur.cost;
					break;
				}

				if(cur.cost > dp[ch][cy][cx]) continue;

				for(int i=0; i<6; i++){
					int nh = ch + hh[i];
					int ny = cy + ud[i];
					int nx = cx + lr[i];

					if(nh <1 || ny < 1 || nx < 1 || nh > L || ny > R || nx > C) continue;
					if(map[nh][ny][nx] == '#') continue;
					if(cur.cost + 1 >= dp[nh][ny][nx]) continue;

					q.add(new Node(ny, nx, nh, cur.cost + 1));
					dp[nh][ny][nx] = cur.cost + 1;
				}
			}

			if(ans == -1){
				System.out.println("Trapped!");
			}
			else{
				System.out.println("Escaped in " + ans + " minute(s).");
			}

		}
	}
}