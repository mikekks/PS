import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {

	static int[] cost;
	static long ans;
	static List<int[]>[] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");
		int N = Integer.parseInt(split[0]);
		int Q = Integer.parseInt(split[1]);

		map = new ArrayList[N + 1];

		int INF = Integer.MAX_VALUE / 2;

		for(int i=1; i<=N; i++){
			map[i] = new ArrayList<>();
		}

		for(int i=1; i<=N-1; i++){
			st = br.readLine();
			split = st.split(" ");

			int p = Integer.parseInt(split[0]);
			int q = Integer.parseInt(split[1]);
			int r = Integer.parseInt(split[2]);

			map[p].add(new int[] {q, r});
			map[q].add(new int[] {p, r});
		}

		StringBuilder ans = new StringBuilder();

		for(int i=0; i<Q; i++){
			st = br.readLine();
			split = st.split(" ");

			int k = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);

			PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[1], o2[1]);
				}
			});
			boolean[] visit = new boolean[N + 1];

			q.add(new int[] {v, INF});
			visit[v] = true;

			int cnt = 0;

			while (!q.isEmpty()){
				int[] poll = q.poll();
				int cur = poll[0];
				int curCost = poll[1];

				if(cur != v && curCost >= k){
					cnt++;
				}

				for(int[] n : map[cur]){
					int next = n[0];
					int nextCost = Math.min(n[1], curCost);

					if(visit[next]) continue;

					q.add(new int[] {next, nextCost});
					visit[next] = true;
				}
			}

			ans.append(cnt).append("\n");
		}

		System.out.println(ans);

	}
}
