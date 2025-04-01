import java.io.*;
import java.util.Stack;

class Main {
	static int N, M, Q;
	static long ans = Integer.MIN_VALUE;
	static String s;

	public static Long cal(long prev, char command, long cur) {
		if (command == '+') {
			return prev + cur;
		} else if (command == '-') {
			return prev - cur;
		} else {
			return prev * cur;
		}
	}

	public static void dfs(int idx, long result) {
		if (idx > s.length()) {
			ans = Math.max(ans, result);
			return;
		}

		long cur = s.charAt(idx) - '0';
		char command = s.charAt(idx - 1);

		long res1 = cal(result, command, cur);
		dfs(idx + 2, res1);

		if (idx + 1 < s.length()) {
			char nextCommand = s.charAt(idx + 1);
			long next = s.charAt(idx + 2) - '0';
			long res2 = cal(cur, nextCommand, next);

			dfs(idx + 4, cal(result, command, res2));
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);
		s = br.readLine();

		if (s.length() == 1) {
			System.out.println(s.charAt(0) - '0');
			return;
		}

		int first = s.charAt(0) - '0';
		char com = s.charAt(1);
		int second = s.charAt(2) - '0';

		dfs(2, first);
		if (s.length() >= 5)
			dfs(4, cal(first, com, second));

		System.out.println(ans);
	}
}
