import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N = 0;
	static List<String> ans = new ArrayList<>();
	static StringBuilder sb;
	public static void dfs(int cur, int idx, String formula){
		if(idx == N+1){
			if(cur == 0) {
				//ans.add(formula);
				sb.append(formula + "\n");
			}
			return;
		}

		int start = formula.length() - 1;
		while(start >= 0){
			if(formula.charAt(start) == '+' ||  formula.charAt(start) == '-'){
				break;
			}
			start--;
		}

		String tmp = formula.substring(0);
		String prevNumStr = tmp.substring(start + 1).replaceAll(" ", "");
		int prev = Integer.parseInt(prevNumStr);
		int next = prev*10 + idx;

		if(start < 1){
			dfs(cur-prev+next, idx+1, formula + ' ' + idx);
		}
		else if(formula.charAt(start) == '+'){
			dfs(cur-prev+next, idx+1, formula + ' ' + idx);
		}
		else if(formula.charAt(start) == '-'){
			dfs(cur+prev-next, idx+1, formula + ' ' + idx);
		}

		dfs(cur+idx, idx+1, formula + "+" + idx);
		dfs(cur-idx, idx+1, formula + "-" + idx);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int T = Integer.parseInt(input);

		while(T > 0){
			ans.clear();
			input = br.readLine();
			N = Integer.parseInt(input);
			sb = new StringBuilder();

			dfs(1, 2, "1");

			System.out.println(sb.toString());
			T--;
		}
	}

}