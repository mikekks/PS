import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Node {
	int y;
	int x;
	int cost;

	public Node(int y, int x, int cost) {
		this.y = y;
		this.x = x;
		this.cost = cost;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);
		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
			}
		}

		boolean[][] visit = new boolean[N + 1][N + 1];

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		visit[1][1] = true;
		q.add(new Node(1, 1, 0));

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		while (!q.isEmpty()) {
			Node node = q.poll();
			int cy = node.y;
			int cx = node.x;
			int cCost = node.cost;

			if(cy == N && cx == N){
				System.out.println(cCost);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cy + ud[i];
				int nx = cx + lr[i];

				if(ny < 1 || nx < 1 || ny > N || nx > N) continue;
				if(visit[ny][nx]) continue;

				int nCost = map[ny][nx] == 0 ? 1 : 0;

				q.add(new Node(ny, nx, cCost + nCost));
				visit[ny][nx] = true;
			}

		}

	}
}