import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node{
	int n;
	int cost;

	public Node(int n, int cost) {
		this.n = n;
		this.cost = cost;
	}
}

class Main {

	static int[] p;
	static int d;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int N = Integer.parseInt(s);

		ArrayList<Integer>[] list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		
		while(true){
			s = br.readLine();
			String[] split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			if(a == -1 && b == -1){
				break;
			}

			list[a].add(b);
			list[b].add(a);
		}

		int[] count = new int[100];

		for(int init=1; init<=N; init++){

			Queue<Node> q = new LinkedList<>();
			boolean[] visit = new boolean[N + 1];
			visit[init] = true;
			q.add(new Node(init, 0));

			while(!q.isEmpty()){
				Node node = q.poll();

				if(node.cost > count[init]){
					count[init] = node.cost;
				}

				for(int i=0; i<list[node.n].size(); i++){
					Integer next = list[node.n].get(i);
					if(visit[next]) continue;

					visit[next] = true;
					q.add(new Node(next, node.cost + 1));
				}
			}
		}

		int min = Integer.MAX_VALUE / 2;
		for(int i=1; i<=N; i++){
			min = Math.min(min, count[i]);
		}

		List<Integer> ans = new ArrayList<>();
		for(int i=1; i<=N; i++){
			if (min == count[i]) {
				ans.add(i);
			}
		}

		System.out.println(min + " " + ans.size());

		StringBuilder sb = new StringBuilder();
		for(Integer a : ans){
			sb.append(a + " ");
		}

		System.out.println(sb);
	}
}