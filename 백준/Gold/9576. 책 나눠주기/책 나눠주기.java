import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Point {
	int a;
	int b;

	public Point(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int T = Integer.parseInt(s);

		while (T > 0) {
			T--;

			s = br.readLine();
			String[] split = s.split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);

			int ans = 0;

			List<Point> list = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				s = br.readLine();
				split = s.split(" ");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);

				list.add(new Point(a, b));
			}

			list.sort((o1, o2) ->  o1.b - o2.b);

			PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			int pIdx = list.size() - 1;

			for (int cur = N; cur > 0; cur--) {

				while (pIdx >= 0 && list.get(pIdx).b >= cur) {
					q.add(list.get(pIdx).a);
					pIdx--;
				}

				while (!q.isEmpty() && q.peek() > cur) {
					q.poll();
				}

				if (!q.isEmpty()) {
					q.poll();
					ans++;
				}

			}
			System.out.println(ans);

		}

	}
}