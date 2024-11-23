import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


class Main {

	static double[] pos = new double[4];
	static boolean[][] visit = new boolean[100][100];
	static int total = 0;
	static int[] ud = {0, 0, 1, -1};
	static int[] lr = {-1, 1, 0, 0};
	static int N = 0;
	static double ans = 0;

	public static void dfs(int y, int x, int cnt, double cPos, boolean isSimple){
		if(cnt == N){
			if(isSimple) ans += cPos;
			return;
		}


		for(int i=0; i<4; i++){
			int ny = y + ud[i];
			int nx = x + lr[i];
			double nPos = cPos * pos[i];
			boolean nextSimple = isSimple;

			if(visit[ny][nx]) continue;

			visit[ny][nx] = true;
			dfs(ny, nx, cnt+1, nPos, nextSimple);
			visit[ny][nx] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		for(int i=0; i<4; i++){
			double tmp = Double.parseDouble(split[i + 1]);
			pos[i] = tmp / 100;
		}

		visit[50][50] = true;
		dfs(50, 50, 0, 1, true);

		System.out.printf("%.18f\n", ans);

	}
}