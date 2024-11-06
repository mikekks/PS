import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node {
	long h;
	long cnt;

	public Node(long h, long cnt) {
		this.h = h;
		this.cnt = cnt;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int N = Integer.parseInt(input);
		long[] arr = new long[N];

		long ans = 0;
		Stack<Node> st = new Stack<>();

		for (int i = 0; i < N; i++) {
			long h = Integer.parseInt(br.readLine());
			arr[i] = h;
		}

		st.add(new Node(arr[0], 1));

		for (int i = 1; i < N; i++) {
			long h = arr[i];

			long cnt = 0;

			while (!st.isEmpty()) {
				if (st.peek().h < h) {
					ans += st.peek().cnt;
					st.pop();
				} else if (st.peek().h == h) {
					cnt += st.peek().cnt;
					st.pop();
				} else {
					ans++;
					break;
				}
			}

			ans += cnt;
			st.push(new Node(h, 1+cnt));
		}

		System.out.println(ans);

	}

}