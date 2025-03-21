import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {

	static int ans = Integer.MAX_VALUE / 2;
	static int N, M;
	static int[][] map;
	static int mp = 0, mf = 0, ms = 0, mv = 0;
	static Set<String> resultSet = new HashSet<>();
	static boolean[] visit;

	static void dfs(int idx) {
		if (idx == N + 1) {

			// 오름차순 정렬
			int l_mp=0, l_mf=0, l_ms=0, l_mv=0;
			int cost = 0;
			StringBuilder sb = new StringBuilder();

			for(int i=1; i<=N; i++){
				if(visit[i]){
					l_mp += map[i][0];
					l_mf += map[i][1];
					l_ms += map[i][2];
					l_mv += map[i][3];
					cost += map[i][4];
					sb.append(i + " ");
				}
			}

			if(l_mp >= mp && l_mf >= mf && l_ms >= ms && l_mv >= mv){
				if(cost < ans){
					resultSet.clear();
					ans = cost;
					resultSet.add(sb.toString());
				}
				else if(cost == ans){
					resultSet.add(sb.toString());
				}
			}

			return;
		}


		visit[idx] = true;
		dfs(idx + 1);
		visit[idx] = false;
		dfs(idx + 1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		map = new int[N + 1][5];
		visit = new boolean[N + 1];

		st = br.readLine();
		String[] split = st.split(" ");
		mp = Integer.parseInt(split[0]);
		mf = Integer.parseInt(split[1]);
		ms = Integer.parseInt(split[2]);
		mv = Integer.parseInt(split[3]);

		for (int i = 0; i < N; i++) {
			st = br.readLine();
			split = st.split(" ");

			map[i+1][0] = Integer.parseInt(split[0]);
			map[i+1][1] = Integer.parseInt(split[1]);
			map[i+1][2]= Integer.parseInt(split[2]);
			map[i+1][3]= Integer.parseInt(split[3]);
			map[i+1][4] = Integer.parseInt(split[4]);
		}

		dfs(1);

		if(ans == Integer.MAX_VALUE / 2){
			System.out.println(-1);
			return;
		}

		System.out.println(ans);

		List<String> list = new ArrayList<>();

		for(String s : resultSet){
			list.add(s);
		}

		list.sort((a,b) -> a.compareTo(b));

		System.out.println(list.get(0));

	}
}
