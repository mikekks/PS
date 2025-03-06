import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	static int[] ans;
	static int N;
	static int[][] map;
	static boolean[] isNotBasic;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		s = br.readLine();
		int M = Integer.parseInt(s);

		map = new int[N + 1][N + 1];
		isNotBasic = new boolean[N + 1];

		for(int i=0; i<M; i++){
			s = br.readLine();
			String[] split = s.split(" ");

			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			int z = Integer.parseInt(split[2]);

			isNotBasic[x] = true;
			map[x][y] += z;
		}

		ans = new int[N];

		int[] inDegree = new int[N + 1];
		List<Integer>[] outList = new ArrayList[N+1];

		for(int i=1; i<=N; i++){
			outList[i] = new ArrayList<>();
		}

		Queue<Integer> q = new LinkedList<>();

		for(int i=1; i<=N; i++){
			if(!isNotBasic[i]) continue;

			boolean isOnlyBasic = true;

			for(int j=1; j<=N; j++){
				if(map[i][j] != 0){
					if(isNotBasic[j]){
						isOnlyBasic = false;
						inDegree[i]++;
						outList[j].add(i);
					}
				}
			}

			if(isOnlyBasic)
				q.add(i);
		}

		while(!q.isEmpty()){
			Integer cur = q.poll();

			for(int i=1; i<=N; i++){
				if(map[cur][i] != 0 && isNotBasic[i]){
					for(int j=1; j<=N; j++){
						if(map[i][j] != 0){
							map[cur][j] += map[i][j] * map[cur][i];
						}
					}
					map[cur][i] = 0;
				}
			}

			for(Integer out : outList[cur]){
				inDegree[out]--;

				if(inDegree[out] == 0){
					q.add(out);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N; i++){
			if(isNotBasic[i]) continue;

			sb.append(i + " " + map[N][i] + "\n");
		}

		System.out.println(sb);

	}

}

