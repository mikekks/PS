import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Bucket {
	int a;
	int b;
	int c;

	public Bucket(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int A = Integer.parseInt(split[0]);
		int B = Integer.parseInt(split[1]);
		int C = Integer.parseInt(split[2]);

		boolean[][][] visit = new boolean[C + 1][C + 1][C + 1];

		Queue<Bucket> q = new LinkedList<>();
		q.add(new Bucket(0, 0, C));
		visit[0][0][C] = true;
		Set<Integer> ans = new HashSet<>();

		while (!q.isEmpty()) {
			Bucket cur = q.poll();

			if (cur.a == 0) {
				ans.add(cur.c);
				//continue;
			}

			if (cur.a != 0) {
				// a -> b
				int minB = Math.min(cur.a, B - cur.b);
				if (!visit[cur.a - minB][cur.b + minB][cur.c]) {
					q.add(new Bucket(cur.a - minB, cur.b + minB, cur.c));
					visit[cur.a - minB][cur.b + minB][cur.c] = true;
				}

				// a -> c
				int minC = Math.min(cur.a, C - cur.c);
				if (!visit[cur.a - minC][cur.b][cur.c + minC]) {
					q.add(new Bucket(cur.a - minC, cur.b, cur.c + minC));
					visit[cur.a - minC][cur.b][cur.c + minC] = true;
				}
			}

			if (cur.b != 0) {
				// b -> a
				int minA = Math.min(cur.b, A - cur.a);
				if (!visit[cur.a + minA][cur.b - minA][cur.c]) {
					q.add(new Bucket(cur.a + minA, cur.b - minA, cur.c));
					visit[cur.a + minA][cur.b - minA][cur.c] = true;
				}

				// b -> c
				int minC = Math.min(cur.b, C - cur.c);
				if (!visit[cur.a][cur.b - minC][cur.c + minC]) {
					q.add(new Bucket(cur.a, cur.b - minC, cur.c + minC));
					visit[cur.a][cur.b - minC][cur.c + minC] = true;
				}
			}

			if (cur.c != 0) {
				// c -> a
				int minA = Math.min(cur.c, A - cur.a);
				if (!visit[cur.a + minA][cur.b][cur.c - minA]) {
					q.add(new Bucket(cur.a + minA, cur.b, cur.c - minA));
					visit[cur.a + minA][cur.b][cur.c - minA] = true;
				}

				// c -> b
				int minB = Math.min(cur.c, B - cur.b);
				if (!visit[cur.a][cur.b + minB][cur.c - minB]) {
					q.add(new Bucket(cur.a, cur.b + minB, cur.c - minB));
					visit[cur.a][cur.b + minB][cur.c - minB] = true;
				}
			}

		}

		List<Integer> list = new ArrayList<>(ans);
		list.sort((a,b) -> a-b);

		for(Integer i : list){
			System.out.print(i + " ");
		}

		System.out.println();
	}

}