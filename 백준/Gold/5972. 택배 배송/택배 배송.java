import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {

	static class Point {
		int next;
		int cost;

		public Point(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}

	static int N, M;
	static List<Point> adjMap[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		adjMap = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			adjMap[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			s = br.readLine();
			split = s.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int c = Integer.parseInt(split[2]);

			adjMap[a].add(new Point(b, c));
			adjMap[b].add(new Point(a, c));
		}

		PriorityQueue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return p1.cost - p2.cost;
			}
		});

		int[] dp = new int[N + 1];

		for(int i=1; i<=N; i++){
			dp[i] = Integer.MAX_VALUE / 2;
		}

		q.add(new Point(1, 0));
		dp[1] = 0;

		while (!q.isEmpty()){
			Point cur = q.poll();

			if(cur.next == N){
				System.out.println(cur.cost);
				break;
			}

			for(int i=0; i<adjMap[cur.next].size(); i++){
				Point next = adjMap[cur.next].get(i);

				if(dp[next.next] <= cur.cost + next.cost) continue;

				q.add(new Point(next.next, cur.cost + next.cost));
				dp[next.next] = cur.cost + next.cost;
			}
		}
	}

}

