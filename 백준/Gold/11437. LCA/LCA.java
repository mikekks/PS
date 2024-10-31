import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<Integer>[] map;
	static int[] depth;
	static int[] p;

	static int lca(int a, int b){
		int da = depth[a];
		int db = depth[b];

		while(da > db){
			a = p[a];
			da--;
		}

		while(da < db){
			b = p[b];
			db--;
		}

		while(a != b){
			a = p[a];
			b = p[b];
		}

		return a;
	}

	static void init(int cur, int pa, int d){
		p[cur] = pa;
		depth[cur] = d;

		for(int i=0; i<map[cur].size(); i++){
			Integer next = map[cur].get(i);
			if(next != pa){
				init(next, cur, d+1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		map = new ArrayList[N + 1];
		p = new int[N+1];
		depth = new int[N+1];

		for (int i = 0; i <= N; i++)
			map[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			input = br.readLine();
			String[] split = input.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			map[a].add(b);
			map[b].add(a);
		}

		input = br.readLine();
		int M = Integer.parseInt(input);

		init(1, 1, 0);

		for (int i = 0; i < M; i++) {
			input = br.readLine();
			String[] split = input.split(" ");

			int ans = lca(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			System.out.println(ans);
		}
	}

}