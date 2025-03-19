import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	static int[] p;
	static long ans;

	static int findP(int n){
		if(p[n] == n) return n;

		return p[n] = findP(p[n]);
	}

	static void merge(int a, int b, int cost){
		a = findP(a);
		b = findP(b);

		if(a != b){
			int min = Math.min(a, b);
			int max = Math.max(a, b);
			p[max] = min;

			ans += cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);

		int[][] map = new int[N + 1][N + 1];
		p = new int[N + 1];

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[2], b[2]);
			}
		});

		for(int i=1; i<=N; i++){
			p[i] = i;
			st = br.readLine();
			String[] split = st.split(" ");
			for(int j=1; j<=N; j++){
				map[i][j] = Integer.parseInt(split[j - 1]);
				if(i != j){
					q.add(new int[] {i, j, map[i][j]});
				}
			}
		}

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int a = poll[0];
			int b = poll[1];
			int cost = poll[2];

			merge(a, b, cost);
		}

		System.out.println(ans);


	}
}
