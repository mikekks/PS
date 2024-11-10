import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		N = Integer.parseInt(input);
		input = br.readLine();

		int[] arr = new int[N];
		int[] cnt = new int[1_000_005];
		int[] ans = new int[N];

		String[] split = input.split(" ");

		for (int i = 0; i < split.length; i++) {
			arr[i] = Integer.parseInt(split[i]);
			cnt[arr[i]]++;
		}

		Stack<Integer> st = new Stack<>();  // x 값을 넣자.
		for (int i = N - 1; i >= 0; i--) {
			int curCnt = cnt[arr[i]];

			while (!st.isEmpty() && curCnt >= cnt[st.peek()]) {
				st.pop();
			}

			if (st.isEmpty()) {
				ans[i] = -1;
			} else {
				ans[i] = st.peek();
			}

			st.push(arr[i]);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb);
	}

}