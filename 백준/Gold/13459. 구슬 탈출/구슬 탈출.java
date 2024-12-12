import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Turn {
	int ry;
	int rx;
	int by;
	int bx;
	int cost;

	public Turn(int ry, int rx, int by, int bx, int cost) {
		this.ry = ry;
		this.rx = rx;
		this.by = by;
		this.bx = bx;
		this.cost = cost;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		char[][] map = new char[N + 1][M + 1];

		int ry = 0, rx = 0, by = 0, bx = 0;
		int ey = 0, ex = 0;

		for (int i = 1; i <= N; i++) {
			s = br.readLine();

			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1);

				if (map[i][j] == 'B') {
					by = i;
					bx = j;
				}
				else if (map[i][j] == 'R') {
					ry = i;
					rx = j;
				}
				else if(map[i][j] == 'O'){
					ey = i;
					ex = j;
				}
			}
		}

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		int[][][][] dp = new int[N + 1][M + 1][N + 1][M + 1];

		for(int i=1; i<=N; i++){
			for(int j=1; j<=M; j++){
				for(int i2=1; i2<=N; i2++){
					for(int j2=1; j2<=M; j2++){
						dp[i][j][i2][j2] = 987654321;
					}
				}
			}
		}


		Queue<Turn> q = new LinkedList<>();
		dp[ry][rx][by][bx] = 0;

		q.add(new Turn(ry, rx, by, bx, 0));

		while (!q.isEmpty()) {
			Turn t = q.poll();

			if(t.cost > dp[t.ry][t.rx][t.by][t.bx]) continue;

			if(t.ry == ey && t.rx == ex){
				if(t.by == ey && t.bx == ex){
					continue;
				}

				System.out.println(1);
				return;
			}

			for(int i=0; i<4; i++){
				int nRy = t.ry;
				int nRx = t.rx;
				int nBy = t.by;
				int nBx = t.bx;

				if(t.cost + 1 > 10) break;

				while(true){
					int tRy = nRy + ud[i];
					int tRx = nRx + lr[i];

					if(map[tRy][tRx] == '#'){
						break;
					}
					if(map[tRy][tRx] == 'O'){
						nRy = tRy;
						nRx = tRx;
						break;
					}

					nRy = tRy;
					nRx = tRx;
				}

				while(true){
					int tBy = nBy + ud[i];
					int tBx = nBx + lr[i];

					if(map[tBy][tBx] == '#'){
						break;
					}
					if(map[tBy][tBx] == 'O'){
						nBy = tBy;
						nBx = tBx;
						break;
					}

					nBy = tBy;
					nBx = tBx;
				}

				if(nBy == ey && nBx == ex){
					continue;
				}

				if(nRy == ey && nRx == ex){
					System.out.println(1);
					return;
				}

				if(nRy == nBy && nRx == nBx){
					if(map[nRy][nRx] == 'O') break;

					int rDiff = Math.abs(t.ry - nRy) + Math.abs(t.rx - nRx);
					int bDiff = Math.abs(t.by - nBy) + Math.abs(t.bx - nBx);

					if(rDiff > bDiff){
						nRy -= ud[i];
						nRx -= lr[i];
					}
					else if(rDiff < bDiff){
						nBy -= ud[i];
						nBx -= lr[i];
					}
					// else{
					// 	System.out.println("NNNNNNNNNNNNNN");
					// }
				}

				if(t.cost + 1 < dp[nRy][nRx][nBy][nBx]){
					q.add(new Turn(nRy, nRx, nBy, nBx, t.cost + 1));
					dp[nRy][nRx][nBy][nBx] = t.cost + 1;
				}

			}

		}

		System.out.println(0);
	}

}