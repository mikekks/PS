import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int T = Integer.parseInt(st);

		while(T > 0){
			T--;
			st = br.readLine();
			String[] split = st.split(" ");

			int N = Integer.parseInt(split[0]);
			int D = Integer.parseInt(split[1]);
			int C = Integer.parseInt(split[2]);

			List<int[]>[] depends = new ArrayList[N + 1];

			for(int i=1; i<=N; i++){
				depends[i] = new ArrayList<>();
			}

			for(int i=0; i<D; i++){
				st = br.readLine();
				split = st.split(" ");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);
				int s = Integer.parseInt(split[2]);

				depends[b].add(new int[]{a, s});
			}

			Queue<int[]> q = new LinkedList<>();
			int[] times = new int[N + 1];

			for(int i=1; i<=N; i++) times[i] = Integer.MAX_VALUE / 2;

			q.add(new int[] {C, 0});
			times[C] = 0;
			int count = 0;

			while (!q.isEmpty()){
				int[] poll = q.poll();
				int cur = poll[0];
				int time = poll[1];

				for(int[] next : depends[cur]){
					if(time + next[1] >= times[next[0]]) continue;

					q.add(new int[] {next[0], time + next[1]});
					times[next[0]] = time + next[1];
				}
			}

			int maxTime = 0;
			for(int i=1; i<=N; i++){
				if(times[i] == Integer.MAX_VALUE / 2) continue;

				count++;
				maxTime = Math.max(maxTime, times[i]);
			}

			System.out.println(count + " " + maxTime);

		}

	}
}
