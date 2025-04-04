import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {
	static int N, M, K;
	static List<int[]>[] map;
	static PriorityQueue<Integer>[] dist;


	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		K = Integer.parseInt(split[2]);

		map = new List[N + 5];
		dist = new PriorityQueue[N + 5];

		for(int i=1; i<=N; i++){
			map[i] = new ArrayList<>();
			dist[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);
			//dist[i].add(Integer.MAX_VALUE / 2);
		}

		for(int i=0; i<M; i++){
			st = br.readLine();
			split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int c = Integer.parseInt(split[2]);

			map[a].add(new int[] {b, c});
		}

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		q.add(new int[] {1, 0});
		dist[1].add(0);

		while (!q.isEmpty()){
			int[] poll = q.poll();
			int cur = poll[0];
			int cost = poll[1];

			for(int[] nn : map[cur]){
				int nNode = nn[0];
				int nCost = nn[1];

				if(dist[nNode].isEmpty() || dist[nNode].size() < K || dist[nNode].peek() >= cost + nCost){

					q.add(new int[] {nNode, nCost + cost});
					dist[nNode].add(cost + nCost);
				}

				if(dist[nNode].size() > K){
					dist[nNode].poll();
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++){
			if(dist[i].size() == K){
				sb.append(dist[i].peek()).append("\n");
			}
			else{
				sb.append(-1).append("\n");
			}
		}

		System.out.println(sb);

	}

}
