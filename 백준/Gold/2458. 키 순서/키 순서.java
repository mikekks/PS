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
		String s = br.readLine();
		String[] split = s.split(" ");
		N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int ans = 0;

		List<Integer>[] in = new List[N + 1];
		List<Integer>[] out = new List[N + 1];

		for(int i=1; i<=N; i++) {
			in[i] = new ArrayList<>();
			out[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			in[b].add(a);
			out[a].add(b);
		}

		for(int i=1; i<=N; i++){
			int cnt = 0;

			boolean[] visited = new boolean[N + 1];
			Queue<Integer> q = new LinkedList<>();

			q.add(i);
			visited[i] = true;

			while (!q.isEmpty()){
				int cur = q.poll();
				cnt++;

				for(int next : out[cur]){
					if(!visited[next]){
						visited[next] = true;
						q.add(next);
					}
				}
			}

			cnt--;
			q.add(i);
			while (!q.isEmpty()){
				int cur = q.poll();
				cnt++;

				for(int next : in[cur]){
					if(!visited[next]){
						visited[next] = true;
						q.add(next);
					}
				}
			}

			if(cnt == N){
				ans++;
			}
		}

		System.out.println(ans);
	}
}

