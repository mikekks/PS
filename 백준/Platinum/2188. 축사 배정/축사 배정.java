import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
	static int N, M, K;
	static List<Integer>[] space;
	static int[] alloc;
	static boolean[] visit;

	public static boolean dfs(int cur){
		for(int here : space[cur]){
			if(visit[here]) continue;

			visit[here] = true;
			if(alloc[here] == 0 || dfs(alloc[here])){
				alloc[here] = cur;
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

		space = new List[N + 1];
		alloc = new int[M + 1];
		visit = new boolean[M + 1];

		for (int i = 1; i <= N; i++)
			space[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			split = st.split(" ");

			for (int j = 1; j < split.length; j++) {
				int num = Integer.parseInt(split[j]);
				space[i].add(num);
			}
		}

		int ans = 0;

		for(int i=1; i<=N; i++){
			Arrays.fill(visit, false);
			if(dfs(i)) ans++;
		}

		System.out.println(ans);

	}

}
