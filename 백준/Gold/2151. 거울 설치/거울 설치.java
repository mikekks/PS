import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
	int y;
	int x;
	int d;
	int cnt;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public Node(int y, int x, int d, int cnt) {
		this.y = y;
		this.x = x;
		this.d = d;
		this.cnt = cnt;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		char[][] map = new char[N + 1][N + 1];
		List<Node> doors = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			input = br.readLine();
			for (int j = 1; j <= input.length(); j++) {
				map[i][j] = input.charAt(j - 1);

				if (map[i][j] == '#') {
					doors.add(new Node(i, j));
				}
			}
		}

		int[] ud = {-1, 0, 1, 0};
		int[] lr = {0, -1, 0, 1};
		boolean[][][] visit = new boolean[N + 1][N + 1][4];

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cnt - o2.cnt;
			}
		});

		for (int i = 0; i < 4; i++){
			q.add(new Node(doors.get(0).y, doors.get(0).x, i, 0));
		}


		while (!q.isEmpty()) {
			Node node = q.poll();
			int cy = node.y;
			int cx = node.x;
			int cd = node.d;
			int cnt = node.cnt;

			visit[cy][cx][cd] = true;

			if(cy == doors.get(1).y && cx == doors.get(1).x){
				System.out.println(cnt);
				return;
			}

			int ny = cy + ud[cd];
			int nx = cx + lr[cd];

			if (ny > N || nx > N || ny < 1 || nx < 1 || map[ny][nx] == '*')
				continue;
			if (visit[ny][nx][cd])
				continue;

			if (map[ny][nx] == '!') {
				q.add(new Node(ny, nx, (cd + 1) % 4, cnt + 1));
				q.add(new Node(ny, nx, (cd + 3) % 4, cnt + 1));
			}
			q.add(new Node(ny, nx, cd, cnt));
		}

	}

}