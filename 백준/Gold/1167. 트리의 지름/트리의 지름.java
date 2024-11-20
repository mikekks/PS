import java.io.*;
import java.util.ArrayList;

class Node{
	int n;
	int cost;

	public Node(int n, int cost) {
		this.n = n;
		this.cost = cost;
	}
}

class Main {

	static ArrayList<Node>[] nodes;
	static boolean[] visit;
	static int ans;
	static int x;
	public static void dfs(int cur, int cost){

		if(cost > ans){
			ans = cost;
			x = cur;
		}

		for(int i=0; i<nodes[cur].size(); i++){
			Node node = nodes[cur].get(i);
			if(visit[node.n]) continue;

			visit[node.n] = true;
			dfs(node.n, cost + node.cost);
			visit[node.n] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N + 1];
		visit = new boolean[N + 1];

		for(int i=0; i<=N; i++){
			nodes[i] = new ArrayList<>();
		}

		for(int i=0; i<N; i++){
			String s = br.readLine();
			String[] split = s.split(" ");
			int cur = Integer.parseInt(split[0]);

			for(int j=1; j<split.length - 1; j+=2){
				int next = Integer.parseInt(split[j]);
				int cost = Integer.parseInt(split[j+1]);
				nodes[cur].add(new Node(next, cost));
			}
		}

		visit[1] = true;
		dfs(1, 0);

		visit = new boolean[N + 1];
		visit[x] = true;
		ans = 0;
		dfs(x, 0);

		System.out.println(ans);

	}
}