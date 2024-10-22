import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Set<String> set = new HashSet<>();
	static Map<Integer, Integer> match = new HashMap<>();

	public static void dfs(int cur, int cnt, int max, String str) {

		if(cur >= str.length()){
			return;
		}

		if (cnt == max) {
			set.add(str);
			return;
		}

		for (int i = cur + 1; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				Integer postIdx = match.get(i);
				StringBuilder sb = new StringBuilder(str);
				sb.setCharAt(i, 'X');
				sb.setCharAt(postIdx, 'X');
				dfs(i, cnt+1, max, sb.toString());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		Stack<Integer> st = new Stack<>();

		int cnt = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				st.add(i);
				cnt++;
			} else if (input.charAt(i) == ')') {
				Integer preIdx = st.pop();
				match.put(preIdx, i);
			}
		}

		for (int k = 1; k <= cnt; k++) {
			dfs(-1, 0, k, input);
		}

		Set<String> results = new HashSet<>();
		for(String s : set){
			results.add(s.replace("X", ""));
		}

		List<String> list = new ArrayList<>(results);

		Collections.sort(list);

		for(String s : list){
			System.out.println(s);
		}
	}
}
