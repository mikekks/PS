import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

class Main {

	static int[] people;
	static int[] cnt;
	static int[][] dp;
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int N = Integer.parseInt(s);
		people = new int[N + 5];
		dp = new int[N + 5][2];
		p = new int[N + 5];
		cnt = new int[N + 5];

		int ans = 0;

		ArrayList<Integer>[] map = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			map[i] = new ArrayList<>();

		s = br.readLine();
		String[] split = s.split(" ");
		for (int i = 0; i < N; i++) {
			people[i + 1] = Integer.parseInt(split[i]);
		}

		for (int i = 0; i < N - 1; i++) {
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			map[a].add(b);
			map[b].add(a);
			cnt[a]++;
			cnt[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		boolean[][] visit = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			dp[i][0] = 0;
			dp[i][1] = people[i];

			if (map[i].size() == 1) {
				q.add(i);
				visit[map[i].get(0)][i] = true;
			}
		}

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			int tmp = Math.max(dp[cur][0], dp[cur][1]);
			ans = Math.max(ans, tmp);

			for(Integer next : map[cur]){
				if(visit[cur][next]) continue;

				cnt[next]--;
				dp[next][0] += Math.max(dp[cur][0], dp[cur][1]);
				dp[next][1] += dp[cur][0];

				if(cnt[next] == 1){
					q.add(next);
					visit[next][cur] = true;
				}
			}
		}

		System.out.println(ans);

	}

}