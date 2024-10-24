import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Node {
	int a;
	int b;
	int cost;

	public Node(int a, int b, int cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
}

public class Main {

	public static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});

		int[][] new_map = new int[N + 1][N + 1];
		int INF = 987654321;

		for (int i = 0; i < N; i++) {
			Arrays.fill(new_map[i + 1], INF);
			new_map[i + 1][i + 1] = 0;

			input = br.readLine();
			String[] split = input.split(" ");

			for (int j = i + 1; j < N; j++) {
				q.add(new Node(i+1, j+1, Integer.parseInt(split[j])));
			}
		}

		while (!q.isEmpty()) {
			Node curNode = q.poll();
			int a = curNode.a;
			int b = curNode.b;

			if (new_map[a][b] != INF) {  // 연결 여부 확인
				if (curNode.cost == new_map[a][b])
					continue;

				if(curNode.cost > new_map[a][b]){
					System.out.println(-1);
					return;
				}

				new_map[a][b] = curNode.cost;
				new_map[b][a] = curNode.cost;
				ans += curNode.cost;

			} else {  // 연결 되지 않았으면 해당 cost 로 연결
				new_map[a][b] = curNode.cost;
				new_map[b][a] = curNode.cost;
				ans += curNode.cost;
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						// INF 검사해야하나?

						if(new_map[i][k] + new_map[k][j] < new_map[i][j]){
							new_map[i][j] = new_map[i][k] + new_map[k][j];
						}
					}
				}
			}
		}

		// 첫째 줄에 도로 개수가 최소일 때, 모든 도로의 시간의 합을 출력한다. 불가능한 경우에는 -1을 출력한다.
		System.out.println(ans);
	}

}