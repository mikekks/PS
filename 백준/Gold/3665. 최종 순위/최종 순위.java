import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int T = Integer.parseInt(s);

		while (T > 0) {
			T--;
			int N = Integer.parseInt(br.readLine());

			s = br.readLine();
			String[] split = s.split(" ");
			ArrayList<Integer>[] list = new ArrayList[505];
			int[] ingree = new int[505];

			for (int i = 1; i <= N; i++)
				list[i] = new ArrayList<>();

			for (int i = 0; i < N; i++){
				int num = Integer.parseInt(split[i]);
				ingree[num] = i;

				for(int j=i+1; j<N; j++){
					list[num].add(Integer.parseInt(split[j]));
				}
			}
			int M = Integer.parseInt(br.readLine());

			for(int i=0; i<M; i++){
				split = br.readLine().split(" ");
				int a = Integer.parseInt(split[0]);
				int b = Integer.parseInt(split[1]);
				if(list[a].contains(b)){
					ingree[a]++;
					ingree[b]--;
					list[b].add(a);
					list[a].remove((Integer) b);

				}
				else {
					ingree[a]--;
					ingree[b]++;
					list[a].add(b);
					list[b].remove((Integer) a);
				}
			}

			Queue<Integer> q = new LinkedList<>();

			int cnt = 0;
			for(int i=1; i<=N; i++){
				if(ingree[i] == 0){
					q.add(i);
					cnt++;
					break;
				}
			}

			if(cnt>1) {
				System.out.println("?");
				continue;
			}

			List<Integer> ans = new ArrayList<>();

			while(!q.isEmpty()){
				Integer cur = q.poll();
				ans.add(cur);

				for(int k=0; k<list[cur].size(); k++){
					Integer next = list[cur].get(k);
					ingree[next]--;

					if(ingree[next] == 0){
						q.add(next);
					}
				}
			}

			if(ans.size() != N){
				System.out.println("IMPOSSIBLE");
				continue;
			}

			for(int i=0; i<ans.size(); i++){
				System.out.print(ans.get(i) + " ");
			}
			System.out.println();
		}
	}
}