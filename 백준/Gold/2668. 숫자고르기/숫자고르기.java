import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {

	static int[] arr;
	static boolean[] visit;
	static boolean[] isFin;
	static List<Integer> total = new ArrayList<>();

	static void dfs(int cur){
		visit[cur] = true;
		int next = arr[cur];

		if(!visit[next]){
			dfs(next);
		}
		else if(!isFin[next]){
			for(int tmp = next; tmp != cur ; tmp = arr[tmp]){
				total.add(tmp);
			}
			total.add(cur);
		}

		isFin[cur] = true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);

		arr = new int[N + 1];
		visit = new boolean[N + 1];
		isFin = new boolean[N + 1];

		List<Integer>[] map = new List[N + 1];

		for (int i = 1; i <= N; i++)
			map[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			arr[i] = Integer.parseInt(st);
			map[arr[i]].add(i);
		}


		for(int i=1; i<=N; i++){
			if(visit[i]) continue;

			dfs(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(total.size() + "\n");

		total.sort((a,b) -> a-b);
		for (Integer num : total) {
			sb.append(num + "\n");
		}

		System.out.println(sb);

	}
}
