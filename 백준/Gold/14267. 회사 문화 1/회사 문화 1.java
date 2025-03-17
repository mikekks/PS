import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {

	static boolean suc = false;
	static String target;
	static List<Integer>[] child;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		child = new List[N + 1];

		for(int i=1; i<=N; i++){
			child[i] = new ArrayList<>();
		}

		st = br.readLine();
		split = st.split(" ");

		int root = 0;

		for(int i=0; i<split.length; i++){
			int parent = Integer.parseInt(split[i]);

			if(parent == -1) {
				root = i + 1;
				continue;
			}
			child[parent].add(i + 1);
		}

		Queue<long[]> q = new LinkedList<>();

		long[] award = new long[N + 1];
		long[] ans = new long[N + 1];

		for(int i=0; i<M; i++){
			st = br.readLine();
			split = st.split(" ");

			int person = Integer.parseInt(split[0]);
			int w = Integer.parseInt(split[1]);
			award[person] += w;
		}

		q.add(new long[] {root, award[root]});

		while (!q.isEmpty()){
			long[] poll = q.poll();
			int cur = (int) poll[0];
			long w = poll[1];

			ans[cur] = w + award[cur];

			for (Integer ch : child[cur]) {
				q.add(new long[] {ch, w + award[cur]});
			}
		}

		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++){
			sb.append(ans[i] + " ");
		}

		System.out.println(sb);

	}
}
