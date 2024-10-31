import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static List<Integer> ans = new ArrayList<>();
	static int idx = 0;
	static ArrayList<Integer>[] map;
	static boolean[] check;
	static boolean isFalse;

	static void dfs(int cur){
		if(isFalse){
			return;
		}

		Set<Integer> set = new HashSet<>();
		for(int i=0; i<map[cur].size(); i++){
			Integer next = map[cur].get(i);
			if(!check[next]){
				check[next] = true;
				set.add(next);
			}
		}

		int size = set.size();
		for(int i=0; i<size; i++){
			if (set.remove(ans.get(idx))) {
				dfs(ans.get(idx++));
			}
			else{
				isFalse = true;
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		check = new boolean[N + 1];

		map = new ArrayList[N + 1];

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
		String[] split = input.split(" ");

		for (int i = 0; i < split.length; i++) {
			int diff = Integer.parseInt(split[i]);
			ans.add(diff);
		}

		check[1] = true;
		idx++;
		dfs(1);

		if(isFalse){
			System.out.println(0);
			return;
		}

		// 입력으로 주어진 DFS 방문 순서가 올바른 순서면 1, 아니면 0을 출력한다.
		System.out.println(1);
	}

}