import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pool {
	int s;
	int e;

	public Pool(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

class Main {
	static int N, L;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		L = Integer.parseInt(split[1]);

		List<Pool> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			split = s.split(" ");
			int start = Integer.parseInt(split[0]);
			int end = Integer.parseInt(split[1]);
			Pool pool = new Pool(start, end);
			list.add(pool);
		}

		list.sort((a, b) -> a.s - b.s);

		int cur = 0;

		for (int i = 0; i < list.size(); i++) {
			Pool pool = list.get(i);

			if (pool.e <= cur)
				continue;

			int curStart = cur;

			if (cur < pool.s) {
				curStart = pool.s;
			}

			int d = pool.e - curStart;

			int cnt = d / L;
			if (d % L != 0) {
				cnt++;
			}

			cur = curStart + (cnt * L);
			ans += cnt;
		}

		System.out.println(ans);

	}
}