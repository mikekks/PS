import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Node {
	int target;
	int cost;

	public Node(int target, int cost) {
		this.target = target;
		this.cost = cost;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		String[] split = input.split(" ");

		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);

		// 사이클을 이루는 도로의 길이의 합이 최소가 되도록
		int answer = Integer.MAX_VALUE;
		List<Node>[] map = new ArrayList[405];

		for (int i = 1; i <= V; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			input = br.readLine();
			split = input.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);
			map[a].add(new Node(b, cost));
		}

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});

		for (int start = 1; start <= V; start++) {
			int[] costs = new int[405];
			Arrays.fill(costs, Integer.MAX_VALUE);

			q.clear();
			q.add(new Node(start, 0));
			int cnt = 0;

			while (!q.isEmpty()) {
				Node cur = q.poll();

				if (cur.target == start) {
					cnt++;
					if (cnt == 2) {
						answer = Math.min(cur.cost, answer);
						break;
					}
				}
				if(cur.cost > answer) break;
				
				if(cur.cost > costs[cur.target]) continue;

				for (int i = 0; i < map[cur.target].size(); i++) {
					Node nextNode = map[cur.target].get(i);
					if (nextNode.cost + cur.cost < costs[nextNode.target]) {
						q.add(new Node(nextNode.target, nextNode.cost + cur.cost));
						costs[nextNode.target] = nextNode.cost + cur.cost;
					}
				}

			}
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(answer);
	}
}
