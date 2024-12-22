import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		int M = Integer.parseInt(split[2]);

		List<Integer>[] map = new ArrayList[N + M + 1];
		int[] d = new int[N + M + 1];

		for (int i = 0; i <= N + M; i++) {
			map[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE / 2;
		}

		for (int i = 0; i < M; i++) {
			s = br.readLine();
			split = s.split(" ");

			for (int j = 0; j < split.length; j++) {
				int cur = Integer.parseInt(split[j]);
				map[cur].add(N + i + 1);
				map[N + i + 1].add(cur);
			}
		}


		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		d[1] = 1;

		while(!q.isEmpty()){
			Integer cur = q.poll();

			for(int i=0; i<map[cur].size(); i++){
				Integer next = map[cur].get(i);
				int cost = 0;

				if(next > N) cost += 1;

				if(d[cur] + cost < d[next]){
					q.add(next);
					d[next] = d[cur] + cost;
				}
			}
		}

		if(d[N] == Integer.MAX_VALUE/2){
			System.out.println(-1);
			return;
		}

		System.out.println(d[N]);
	}
}