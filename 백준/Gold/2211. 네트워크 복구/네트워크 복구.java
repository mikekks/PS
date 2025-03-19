import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {

	static int[] cost;
	static long ans;
	static List<int[]>[] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		cost = new int[N + 1];
		map = new ArrayList[N + 1];

		int INF = Integer.MAX_VALUE / 2;

		for(int i=1; i<=N; i++){
			cost[i] = INF;
			map[i] = new ArrayList<>();
		}

		for(int i=1; i<=M; i++){
			st = br.readLine();
			split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);
			map[a].add(new int[] {b, cost});
			map[b].add(new int[] {a, cost});
		}

		StringBuilder sb = new StringBuilder();

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[2], b[2]);
			}
		});

		cost[1] = 0;
		q.add(new int[] {0, 1, 0});

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int prev = poll[0];
			int cur = poll[1];
			int curCost = poll[2];

			if(curCost > cost[cur]) continue;

			if(cur != 1){
				ans++;
				sb.append(prev + " " + cur + "\n");
			}

			for(int[] n : map[cur]){
				int next = n[0];
				int nextCost = n[1];

				if(curCost + nextCost >= cost[next]) continue;

				cost[next] = curCost + nextCost;
				q.add(new int[] {cur, next, cost[next]});
			}
		}

		System.out.println(ans);
		System.out.print(sb);

	}
}
