import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	static int[] p;
	static int mergeCount = 0;
	static int ans = 0;

	static int findP(int n){
		if(p[n] == n) return n;

		return p[n] = findP(p[n]);
	}

	static void merge(int a, int b, int cost){
		a = findP(a);
		b = findP(b);

		if(a != b){
			int parent = Math.min(a, b);
			int child = Math.max(a, b);
			p[child] = parent;
			mergeCount++;
			ans += cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		p = new int[N + 1];
		int[] univ = new int[N + 1];
		int[][] map = new int[N + 1][N + 1];

		st = br.readLine();
		split = st.split(" ");

		for(int i=1; i<=N; i++){
			p[i] = i;
			if(split[i-1].charAt(0) == 'M'){
				univ[i] = 1;
			}
			else{
				univ[i] = 0;
			}
		}

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		for(int i=0; i<M; i++){
			st = br.readLine();
			split = st.split(" ");

			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			int d = Integer.parseInt(split[2]);

			map[u][v] = d;
			map[v][u] = d;

			if(univ[u] == univ[v]) continue;

			q.add(new int[] {u, v, d});
		}

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int a = poll[0];
			int b = poll[1];
			int cost = poll[2];

			merge(a, b, cost);
		}

		if(mergeCount != N-1){
			System.out.println(-1);
			return;
		}

		System.out.println(ans);

	}
}
