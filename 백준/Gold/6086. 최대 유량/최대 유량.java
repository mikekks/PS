import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
	static int N, H;
	static boolean[] visit;
	static List<Pipe>[] pipes;
	static int[][] capacity;
	static int[][] flow;
	static int ans = 0;

	static class Pipe{
		int pId;
		int next;
		int water;

		public Pipe(int pId, int next, int water) {
			this.pId = pId;
			this.next = next;
			this.water = water;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		capacity = new int[100][100];
		flow = new int[100][100];

		for(int i=1; i<=N; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			int a = split[0].charAt(0) - 'A' + 1;
			int b = split[1].charAt(0) - 'A' + 1;
			int water = Integer.parseInt(split[2]);

			capacity[a][b] += water;
			capacity[b][a] += water;
		}

		// 1 -> 26

		int[] p = new int[100];
		Queue<Integer> q;
		int dst = 26;

		while (true){
			Arrays.fill(p, -1);
			q = new LinkedList<>();

			p[1] = 1;
			q.add(1);

			while (!q.isEmpty() && p[dst] == -1){
				Integer cur = q.poll();
				for(int i=1; i<=95; i++){
					if(capacity[cur][i] - flow[cur][i] > 0 && p[i] == -1){
						q.add(i);
						p[i] = cur;
					}
				}
			}

			if(p[dst] == -1) break;

			int amount = Integer.MAX_VALUE;
			for(int i=dst; i!=1; i=p[i]){
				amount = Math.min(amount, capacity[p[i]][i] - flow[p[i]][i]);
			}

			for(int i=dst; i!=1; i=p[i]){
				flow[p[i]][i] += amount;
				flow[i][p[i]] -= amount;
			}

			ans += amount;
		}

		System.out.println(ans);

	}
}
