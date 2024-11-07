import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node {
	int y;
	int x;
	int time;

	public Node(int y, int x, int time) {
		this.y = y;
		this.x = x;
		this.time = time;
	}
}

public class Main {

	static int[] ud = {-1, 1, 0, 0};
	static int[] lr = {0, 0, -1, 1};
	static int N, M;
	static char[][] map;
	static boolean[] check;
	static int answer;
	static int dirtyCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String input = br.readLine();
			String[] split = input.split(" ");
			answer = Integer.MAX_VALUE;

			N = Integer.parseInt(split[1]);
			M = Integer.parseInt(split[0]);

			if (N == 0 && M == 0)
				break;

			map = new char[N + 1][M + 1];
			dirtyCnt = 1;
			int[][] dirty_map = new int[11][2];

			for (int i = 1; i <= N; i++) {
				input = br.readLine();

				for (int j = 1; j <= M; j++) {
					map[i][j] = input.charAt(j - 1);

					if (map[i][j] == 'o') {
						dirty_map[0][0] = i;
						dirty_map[0][1] = j;

					} else if (map[i][j] == '*') {
						dirty_map[dirtyCnt][0] = i;
						dirty_map[dirtyCnt][1] = j;
						dirtyCnt++;
					}
				}
			}

			ArrayList<Node>[] adj_list = new ArrayList[dirtyCnt];
			for (int i = 0 ; i < dirtyCnt ; i++) {
				adj_list[i] = new ArrayList<>();
			}

			for(int i=0; i<dirtyCnt; i++){
				for(int j=i+1; j<dirtyCnt; j++){
					int diff = bfs(dirty_map[i][0], dirty_map[i][1], dirty_map[j][0], dirty_map[j][1]);
					if(diff == -1) continue;

					adj_list[i].add(new Node(j, 0, diff));
					adj_list[j].add(new Node(i, 0, diff));
				}
			}

			check = new boolean[dirtyCnt];
			check[0] = true;
			dfs(0, 0, adj_list, 0, dirtyCnt);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}

	}

	static void dfs(int start, int depth, ArrayList<Node>[] adj_list, int sum, int dusts) {
		if (depth == dusts - 1) {
			answer = Math.min(answer, sum);
			return;
		}

		for (Node next : adj_list[start]) {
			if (check[next.y]) continue;
			check[next.y] = true;
			dfs(next.y, depth + 1, adj_list, sum + next.time, dusts);
			check[next.y] = false;
		}
	}

	public static int bfs(int sy, int sx, int ey, int ex){

		boolean[][] visit = new boolean[N + 1][M + 1];

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sy, sx, 0));
		visit[sy][sx] = true;

		while (!q.isEmpty()){
			Node node = q.poll();

			if(node.y == ey && node.x == ex){
				return node.time;
			}

			for(int i=0; i<4; i++){
				int ny = node.y + ud[i];
				int nx = node.x + lr[i];

				if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
				if(visit[ny][nx] || map[ny][nx] == 'x') continue;

				q.add(new Node(ny, nx, node.time + 1));
				visit[ny][nx] = true;
			}
		}

		return -1;
	}

}