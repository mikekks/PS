import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node{
	int n;
	int cost;

	public Node(int n, int cost) {
		this.n = n;
		this.cost = cost;
	}
}

class Main {

	static int[] d;
	public static int[] dijk(int start, ArrayList<Node>[] route, int N){
		int[] d = new int[N + 1];

		for(int i=1; i<=N; i++) d[i] = 987654321;

		d[start] = 0;
		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		q.add(new Node(start, 0));

		while(!q.isEmpty()){
			Node node = q.poll();

			if(node.cost > d[node.n]) continue;

			for(int i=0; i<route[node.n].size(); i++){
				Node next = route[node.n].get(i);
				int nextCost = next.cost + node.cost;
				if(nextCost >= d[next.n]) continue;

				d[next.n] = nextCost;
				q.add(new Node(next.n, nextCost));
			}
		}

		return d;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int X = Integer.parseInt(split[2]);

		ArrayList<Node>[] route = new ArrayList[N + 1];
		ArrayList<Node>[] route_r = new ArrayList[N + 1];
		d = new int[N + 1];
		for (int i = 0; i <= N; i++){
			route[i] = new ArrayList<>();
			route_r[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++){
			input = br.readLine();
			split = input.split(" ");
			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);

			route[s].add(new Node(e, cost));
			route_r[e].add(new Node(s, cost));
		}

		int[] go = dijk(X, route, N);
		int[] back = dijk(X, route_r, N);

		int ans = 0;
		for(int i=0; i<go.length; i++){
			int tmp = go[i] + back[i];
			if(tmp > ans){
				ans = tmp;
			}
		}

		System.out.println(ans);
	}
}