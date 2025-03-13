import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Main {

	static class Node {
		int l;
		int r;
		int cost;

		public Node(int l, int r, int cost) {
			this.l = l;
			this.r = r;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		int[] arr = new int[N + 1];
		int ans = 0;

		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node a, Node b) {
				return b.cost - a.cost;
			}
		});

		st = br.readLine();
		split = st.split(" ");

		for (int i = 0; i < split.length; i++) {
			arr[i] = Integer.parseInt(split[i]);

			if (i != 0) {
				q.add(new Node(i-1, i , arr[i] - arr[i-1]));
			}
		}


		// 처음과 끝 엣지 처리 필요.
		Set<String> divide = new HashSet<>();

		while (!q.isEmpty() && K-1 > 0){
			Node node = q.poll();
			K--;
			divide.add(node.l + " " + node.r);
		}
		String lastKey = (N - 1) + " " + N;
		divide.add(lastKey);

		int start = arr[0];

		for(int i=0; i<arr.length; i++){
			int end = arr[i];

			String key = i + " " + (i + 1);
			if (divide.contains(key)) {
				ans += (end - start);
				start = arr[i+1];
			}

		}

		System.out.println(ans);

	}
}
