import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
	int n;
	int time;

	public Node(int n, int time) {
		this.n = n;
		this.time = time;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");

		int MAX = 100005;
		int[] cost = new int[MAX];
		Arrays.fill(cost, 987654321);

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.time - o2.time;
			}
		});

		q.add(new Node(N, 0));
		cost[N] = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (node.n == K) {
				System.out.println(node.time);
				return;
			}

			if(node.time > cost[node.n]) continue;

			if(node.n-1 >= 0 && node.time + 1 < cost[node.n-1]){
				q.add(new Node(node.n - 1, node.time + 1));
				cost[node.n-1] = node.time + 1;
			}
			if(node.n+1 < MAX && node.time + 1 < cost[node.n+1]){
				q.add(new Node(node.n + 1, node.time + 1));
				cost[node.n+1] = node.time + 1;
			}
			if(node.n*2 < MAX && node.time < cost[node.n*2]){
				q.add(new Node(node.n * 2, node.time));
				cost[node.n*2] = node.time;
			}

		}
	}

}