import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {
	static int N, M, Q;
	static List<Integer>[] list;
	static int[] task;
	static boolean[] visit;

	public static boolean dfs(int cur){
		for(Integer next : list[cur]){
			if(visit[next]) continue;

			visit[next] = true;

			if(task[next] == 0 || dfs(task[next])){
				task[next] = cur;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		list = new ArrayList[1005];
		task = new int[M + 1];
		visit = new boolean[M + 1];

		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			split = st.split(" ");

			for (int j = 1; j < split.length; j++) {
				int num = Integer.parseInt(split[j]);
				list[i].add(num);
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visit, false);
			boolean suc = dfs(i);
			if(suc) ans++;
		}

		System.out.println(ans);

	}

}
