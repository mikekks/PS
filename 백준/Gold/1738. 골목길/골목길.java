import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Edge{
	int s;
	int e;
	int cost;

	public Edge(int s, int e, int cost) {
		this.s = s;
		this.e = e;
		this.cost = cost;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		List<Edge> edges = new ArrayList<>();

		for(int i=0; i<M; i++){
			s = br.readLine();
			split = s.split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);
			edges.add(new Edge(start, end, cost));
		}

		int INF = -987654321;
		int[] d = new int[N + 1];
		int[] prev = new int[N + 1];
		Arrays.fill(d, INF);
		d[1] = 0;


		for(int i = 0; i<N; i++){
			for(int j=0; j<M; j++){
				Edge edge = edges.get(j);

				if(d[edge.s] != INF && d[edge.e] < d[edge.s] + edge.cost){
					d[edge.e] = d[edge.s] + edge.cost;
					prev[edge.e] = edge.s;
				}
			}
		}

		int last = d[N];

		for(int i=0; i<M; i++){
			Edge edge = edges.get(i);

			if(d[edge.s] != INF && d[edge.e] < d[edge.s] + edge.cost){
				d[edge.e] = -INF;
			}
		}

		if(d[N] > last){
			System.out.println(-1);
			return;
		}

		Stack<Integer> st = new Stack<>();
		int cur = N;

		while(cur != 1){
			st.add(cur);

			cur = prev[cur];
		}
		st.add(1);

		while(!st.isEmpty()){
			System.out.print(st.pop() + " ");
		}

		System.out.println();
	}
}