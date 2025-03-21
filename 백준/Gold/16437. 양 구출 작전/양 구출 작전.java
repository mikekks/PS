import java.io.*;
import java.util.*;

class Main {
	static int N, M, P;
	static List<Integer>[] map;
	static boolean[] isWolf;
	static int[] aCount;
	static boolean[] visit;

	static long dfs(int cur){

		long comeSheepCount = 0;

		if(!isWolf[cur]){
			comeSheepCount += aCount[cur];
		}

		for(Integer next : map[cur]){
			if(visit[next]) continue;

			visit[next] = true;
			comeSheepCount += dfs(next);
		}

		if(isWolf[cur]){
			comeSheepCount = Math.max(comeSheepCount - aCount[cur], 0);
		}

		return comeSheepCount;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split;

		N = Integer.parseInt(st);
		map = new List[N + 1];
		aCount = new int[N + 1];
		isWolf = new boolean[N + 1];
		visit = new boolean[N + 1];

		for(int i=1; i<=N; i++){
			map[i] = new ArrayList<>();
		}

		for(int i=2; i<=N; i++){
			st = br.readLine();
			split = st.split(" ");

			int count = Integer.parseInt(split[1]);
			int connectNode = Integer.parseInt(split[2]);

			if(split[0].charAt(0) == 'W'){
				isWolf[i] = true;
			}

			map[i].add(connectNode);
			map[connectNode].add(i);
			aCount[i] = count;
		}

		visit[1] = true;

		System.out.println(dfs(1));

	}
}
