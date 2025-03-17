import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {
	static int N;
	static int[][] map;
	static boolean[] visit;

	static int ans = Integer.MAX_VALUE / 2;

	static void dfs(int idx){

		if(idx == N+1){

			List<Integer> link = new ArrayList<>();
			List<Integer> start = new ArrayList<>();

			for(int i=1; i<=N; i++){
				if(visit[i]){
					link.add(i);
				}
				else{
					start.add(i);
				}
			}

			if(link.isEmpty() || start.isEmpty()) return;

			int linkTotal = 0;
			int startTotal = 0;

			for(int i=0; i<link.size(); i++){
				for(int j=i+1; j<link.size(); j++){
					linkTotal += map[link.get(i)][link.get(j)];
					linkTotal += map[link.get(j)][link.get(i)];
				}
			}

			for(int i=0; i<start.size(); i++){
				for(int j=i+1; j<start.size(); j++){
					startTotal += map[start.get(i)][start.get(j)];
					startTotal += map[start.get(j)][start.get(i)];
				}
			}

			ans = Math.min(ans, Math.abs(linkTotal - startTotal));
			return;
		}

		for(int i=idx; i<=N; i++){
			if(visit[i]) continue;

			visit[i] = true;
			dfs(i+1);
			visit[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		N = Integer.parseInt(st);
		visit = new boolean[N + 1];
		map = new int[N + 1][N + 1];

		for(int i=1; i<=N; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			for(int j=1; j<=N; j++){
				int num = Integer.parseInt(split[j - 1]);
				map[i][j] = num;
			}
		}

		dfs(1);

		System.out.println(ans);
	}
}
