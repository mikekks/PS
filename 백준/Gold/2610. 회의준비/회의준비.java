import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	static int[] p;
	static int gCnt = 0;

	public static int find(int n) {
		if (p[n] == n)
			return n;

		return p[n] = find(p[n]);
	}

	public static void merge(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			gCnt--;

			if (pa < pb) {
				p[pb] = pa;
			} else if (pa > pb) {
				p[pa] = pb;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		int INF = 987654321;

		input = br.readLine();
		int M = Integer.parseInt(input);

		int[][] map = new int[N + 1][N + 1];
		p = new int[N+1];

		for (int i = 1; i <= N; i++){
			p[i] = i;
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}

		gCnt = N;

		for (int i = 0; i < M; i++) {
			input = br.readLine();
			String[] split = input.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			merge(a, b);
			map[a][b] = 1;
			map[b][a] = 1;
		}

		for (int i = 1; i <= N; i++){
			find(i);
		}

		for(int k=1; k<=N; k++){
			for(int i=1; i<=N; i++){
				for(int j=1; j<=N; j++){
					if(map[i][k] == INF || map[k][j] == INF) continue;

					if(map[i][k] + map[k][j] < map[i][j]){
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		List<Integer> list = Arrays.stream(p)
			.boxed()
			.collect(Collectors.toList());
		list.remove(0);
		Set<Integer> set = new HashSet<>(list);
		List<Integer> set_list = new ArrayList<>(set);

		int[] answers = new int[set_list.size()];

		for (int k=0; k<set_list.size(); k++) {
			int globalMax = INF;
			int gNum = set_list.get(k);

			for(int i=1; i<=N; i++){
				if(gNum != p[i]) continue;

				int localMax = 0;
				for(int j=1; j<=N; j++){
					if(map[i][j] == INF) continue;
					localMax = Math.max(localMax, map[i][j]);
				}

				if(localMax < globalMax){
					globalMax = localMax;
					answers[k] = i;
				}
			}

		}

		System.out.println(gCnt);

		Arrays.sort(answers);
		for(int k=0; k<answers.length; k++){
			System.out.println(answers[k]);
		}
	}
}