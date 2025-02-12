import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node {
	int n;
	int dist;
	boolean isPass;

	public Node(int n, int dist) {
		this.n = n;
		this.dist = dist;
	}

	public Node(int n, int dist, boolean isPass) {
		this.n = n;
		this.dist = dist;
		this.isPass = isPass;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean pass) {
		isPass = pass;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int T = Integer.parseInt(s);

		while (T > 0) {
			T--;

			List<Node>[] adj = new ArrayList[2001];

			for (int i = 0; i < 2001; i++) {
				adj[i] = new ArrayList<>();
			}

			s = br.readLine();
			String[] split = s.split(" ");
			int n = Integer.parseInt(split[0]);
			int m = Integer.parseInt(split[1]);
			int t = Integer.parseInt(split[2]);

			s = br.readLine();
			split = s.split(" ");
			int start = Integer.parseInt(split[0]);
			int gNode = Integer.parseInt(split[1]);
			int hNode = Integer.parseInt(split[2]);

			for (int i = 0; i < m; i++) {
				s = br.readLine();
				split = s.split(" ");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);
				int d = Integer.parseInt(split[2]);

				if(a == gNode && b == hNode){
					adj[a].add(new Node(b, d*2-1, true));
					adj[b].add(new Node(a, d*2-1, true));
				}
				else if(a == hNode && b == gNode){
					adj[a].add(new Node(b, d*2-1, true));
					adj[b].add(new Node(a, d*2-1, true));
				}

				adj[a].add(new Node(b, d*2));
				adj[b].add(new Node(a, d*2));
			}

			List<Integer> dest = new ArrayList<>();

			for (int i = 0; i < t; i++) {
				s = br.readLine();
				int x = Integer.parseInt(s);
				dest.add(x);
			}

			int[] dist = new int[2001];

			int INF = Integer.MAX_VALUE / 3;
			for (int i = 0; i < 2001; i++) {
				dist[i] = INF;
			}

			dist[start] = 0;
			PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
			q.add(new Node(start, 0));

			while (!q.isEmpty()) {
				Node curNode = q.poll();
				int cur = curNode.n;
				int curDist = curNode.dist;

				if (dist[cur] < curDist)
					continue;

				for (Node nextNode : adj[cur]) {
					int next = nextNode.n;
					int nextDist = nextNode.dist;

					if (dist[next] > curDist + nextDist) {
						dist[next] = curDist + nextDist;
						Node node = new Node(next, dist[next], curNode.isPass);

						q.add(node);
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			List<Integer> result = new ArrayList<>();

			for (int i = 0; i < dest.size(); i++) {
				int d = dest.get(i);
				if (dist[d] % 2 == 1) {
					result.add(d);
				}
			}

			result.sort((a, b) -> a - b);

			for (int i = 0; i < result.size(); i++) {
				sb.append(result.get(i)).append(" ");
			}

			System.out.println(sb);

		}

	}
}