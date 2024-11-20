import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
	int y;
	int x;
	int cost;
	boolean isGram;

	public Node(int y, int x, int cost, boolean isGram) {
		this.y = y;
		this.x = x;
		this.cost = cost;
		this.isGram = isGram;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int T = Integer.parseInt(split[2]);

		int[][] map = new int[N + 1][M + 1];
		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		for(int i=1; i<=N; i++){
			input = br.readLine();
			split = input.split(" ");
			for(int j=1; j<=M; j++){
				map[i][j] = Integer.parseInt(split[j - 1]);
			}
		}

		int[][][] dp = new int[N + 1][M + 1][2];
		int INF = 987654321;

		for(int i=1; i<=N; i++)
			for(int j=1; j<=M; j++)
				for(int k=0; k<2; k++)
					dp[i][j][k] = INF;

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});

		q.add(new Node(1, 1, 0, false));
		dp[1][1][0] = 0;

		while(!q.isEmpty()){
			Node node = q.poll();
			int gram = node.isGram ? 1 : 0;

			if(node.cost > T) break;

			if(node.y == N && node.x == M){
				System.out.println(node.cost);
				return;
			}

			if(node.cost > dp[node.y][node.x][gram]) continue;

			for(int i=0; i<4; i++){
				int ny = node.y + ud[i];
				int nx = node.x + lr[i];

				if(ny < 1 || nx < 1 || ny > N || nx > M) continue;

				if(map[ny][nx] == 2){
					if(node.cost + 1 >= dp[ny][nx][1]) continue;  // 1 확인 필요

					q.add(new Node(ny, nx, node.cost + 1, true));
					dp[ny][nx][gram] = node.cost + 1;
				}
				else if(map[ny][nx] == 1){
					if(!node.isGram) continue;

					if(node.cost + 1 >= dp[ny][nx][gram]) continue;
					q.add(new Node(ny, nx, node.cost + 1, true));
					dp[ny][nx][gram] = node.cost + 1;
				}
				else if(map[ny][nx] == 0){
					if(node.cost + 1 >= dp[ny][nx][gram]) continue;
					q.add(new Node(ny, nx, node.cost + 1, node.isGram));
					dp[ny][nx][gram] = node.cost + 1;
				}
			}

		}

		System.out.println("Fail");

	}

}