import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
	static int N, M, K;
	static int[][] p;
	static List<Integer>[] adj;
	static int[] subTree;
	static boolean[] visit;
	public static int dfs(int cur){

		for(Integer child : adj[cur]){
			if(visit[child]) continue;

			visit[child] = true;
			subTree[cur] += dfs(child);
		}

		return subTree[cur];
	}

	private static long nC2(int n) {
		return (long) n * (n - 1) / 2;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);
		visit = new boolean[N + 1];
		adj = new List[N + 1];
		subTree = new int[N + 1];

		for (int i = 1; i <= N; i++)
			subTree[i] = 1;

		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			st = br.readLine();
			String[] split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			adj[a].add(b);
			adj[b].add(a);
		}

		visit[1] = true;
		dfs(1);

		long ans = 0;

		for(int i=2; i<=N; i++){
			int rest = N - subTree[i];
			ans += nC2(N) - nC2(rest);
		}

		System.out.println(ans);

	}

}
