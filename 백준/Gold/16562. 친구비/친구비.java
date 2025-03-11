import java.io.*;


class Main {

	static int[] p;
	static int[] cost;
	static int[] groupCost;

	static int findParent(int n){
		if(p[n] == n) return n;

		return p[n] = findParent(p[n]);
	}

	static void merge(int a, int b){
		a = findParent(a);
		b = findParent(b);

		if(a != b){
			int ta = Math.min(a, b);
			int tb = Math.max(a, b);
			p[tb] = ta;
			groupCost[ta] = Math.min(groupCost[ta], groupCost[tb]);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int k = Integer.parseInt(split[2]);

		cost = new int[N + 1];
		groupCost = new int[N + 1];

		st = br.readLine();
		split = st.split(" ");

		for (int i = 0; i < split.length; i++) {
			cost[i+1] = Integer.parseInt(split[i]);
		}

		p = new int[N + 1];
		for(int i=1; i<=N; i++){
			p[i] = i;
			groupCost[i] = cost[i];
		}

		for (int i = 0; i < M; i++) {
			st = br.readLine();
			split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			merge(a, b);
		}

		boolean[] visit = new boolean[N + 1];
		int totalCost = 0;

		for(int i=1; i<=N; i++){
			int parent = findParent(i);
			if(visit[parent]) continue;

			totalCost += groupCost[parent];
			visit[parent] = true;
		}

		if(totalCost > k){
			System.out.println("Oh no");
			return;
		}

		System.out.println(totalCost);
	}
}
