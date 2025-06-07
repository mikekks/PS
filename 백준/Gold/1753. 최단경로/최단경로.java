import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {
	static int N;
	static List<int[]>[] adj;
	static int[] ans;

	public static void dijk(int init){

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		ans[init] = 0;
		q.add(new int[] {init, 0});

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int cur = poll[0];
			int cost = poll[1];

			if(ans[cur] < cost){
				continue;
			}

			for(int[] next : adj[cur]){
				int nNode = next[0];
				int nCost = next[1] + cost;

				if(nCost < ans[nNode]){
					q.add(new int[] {nNode, nCost});
					ans[nNode] = nCost;
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		String[] split = st.split(" ");
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);

		st = br.readLine();
		int K = Integer.parseInt(st);

		adj = new List[V + 5];
		ans = new int[V + 5];

		for(int i=1; i<=V; i++){
			adj[i] = new ArrayList<>();
			ans[i] = Integer.MAX_VALUE / 2;
		}

		for(int i=0; i<E; i++){
			st = br.readLine();
			split = st.split(" ");

			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			int w = Integer.parseInt(split[2]);

			adj[u].add(new int[] {v, w});
		}

		dijk(K);

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++){
			if(ans[i] == Integer.MAX_VALUE / 2){
				sb.append("INF").append("\n");
				continue;
			}

			sb.append(ans[i]).append("\n");
		}

		System.out.print(sb);
	}

}
