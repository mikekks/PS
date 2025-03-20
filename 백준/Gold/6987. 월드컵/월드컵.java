import java.io.*;

class Main {

	static int[][] result = new int[7][7];
	static boolean ans = false;

	static void dfs(int cur, int next, int[][] worldcup, int matchCount) {

		if (cur == 6) {

			if(matchCount == 15){
				ans = true;
			}

			return;
		}

		if(worldcup[cur][0] > 0 && worldcup[next][2] > 0){
			worldcup[cur][0]--;
			worldcup[next][2]--;
			if (next + 1 == 7) {
				dfs(cur + 1, cur + 2, worldcup, matchCount + 1);
			} else {
				dfs(cur, next + 1, worldcup, matchCount + 1);
			}
			worldcup[cur][0]++;
			worldcup[next][2]++;
		}

		if(worldcup[cur][1] > 0 && worldcup[next][1] > 0){
			worldcup[cur][1]--;
			worldcup[next][1]--;
			if (next + 1 == 7) {
				dfs(cur + 1, cur + 2, worldcup, matchCount + 1);
			} else {
				dfs(cur, next + 1, worldcup, matchCount + 1);
			}
			worldcup[cur][1]++;
			worldcup[next][1]++;
		}

		if(worldcup[cur][2] > 0 && worldcup[next][0] > 0){
			worldcup[cur][2]--;
			worldcup[next][0]--;
			if (next + 1 == 7) {
				dfs(cur + 1, cur + 2, worldcup, matchCount + 1);
			} else {
				dfs(cur, next + 1, worldcup, matchCount + 1);
			}
			worldcup[cur][2]++;
			worldcup[next][0]++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		for (int q = 1; q <= 4; q++) {
			String st = br.readLine();

			String[] split = st.split(" ");
			boolean preCheck = false;
			int[][] worldcup = new int[7][3];
			for (int i = 0; i < 18; i += 3) {
				int win = Integer.parseInt(split[i]);
				int draw = Integer.parseInt(split[i + 1]);
				int lose = Integer.parseInt(split[i + 2]);

				if(win + draw + lose != 5){
					preCheck = true;
				}

				worldcup[i / 3+1][0] = win;
				worldcup[i / 3+1][1] = draw;
				worldcup[i / 3+1][2] = lose;
			}
			
			if(preCheck){
				sb.append(0 + " ");
				continue;
			}

			ans = false;
			dfs(1, 2, worldcup, 0);

			if(ans)
				sb.append(1 + " ");
			else
				sb.append(0 + " ");
		}

		System.out.println(sb);

	}
}
