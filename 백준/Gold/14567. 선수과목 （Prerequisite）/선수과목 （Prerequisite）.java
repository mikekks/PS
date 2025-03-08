import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

	static int N, M;
	static class Node{
		int num;
		int time;

		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		List<Integer>[] nextList = new List[N + 1];
		int[] inDegree = new int[N + 1];

		for(int i=1; i<=N; i++){
			nextList[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++){
			s = br.readLine();
			split = s.split(" ");

			int prev = Integer.parseInt(split[0]);
			int next = Integer.parseInt(split[1]);
			nextList[prev].add(next);
			inDegree[next]++;
		}

		Queue<Node> q = new LinkedList<>();

		for(int i=1; i<=N; i++){
			if(inDegree[i] == 0)
				q.add(new Node(i, 1));
		}

		int[] ans = new int[N + 1];

		while (!q.isEmpty()){
			Node cur = q.poll();

			ans[cur.num] = cur.time;

			for(Integer next : nextList[cur.num]){
				inDegree[next]--;

				if(inDegree[next] == 0){
					q.add(new Node(next, cur.time + 1));
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++){
			sb.append(ans[i]).append(" ");
		}

		System.out.println(sb);

	}

}

