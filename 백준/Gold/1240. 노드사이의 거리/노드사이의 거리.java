import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

	static class Node {
		int id;
		int cost;
		Node(int id, int cost){
			this.id = id;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		// 인접리스트
		Map<Integer, List<Node>> adjMap = new HashMap<>();

		for(int i=1; i<=N; i++){
			adjMap.put(i, new ArrayList<>());
		}

		for(int i=0; i<N-1; i++){
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);

			List<Node> aNodes = adjMap.get(a);
			aNodes.add(new Node(b, cost));
			adjMap.put(a, aNodes);

			List<Node> bNodes = adjMap.get(b);
			bNodes.add(new Node(a, cost));
			adjMap.put(b, bNodes);
		}

		for(int i=0; i<M; i++){
			s = br.readLine();
			split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			boolean[] visited = new boolean[N+1];
			Queue<Node> q = new LinkedList<>();
			q.add(new Node(a, 0));
			visited[a] = true;

			while(!q.isEmpty()){
				Node curNode = q.poll();

				if(curNode.id == b){
					System.out.println(curNode.cost);
					break;
				}

				for(int j=0; j<adjMap.get(curNode.id).size(); j++){
					Node nextNode = adjMap.get(curNode.id).get(j);
					if(visited[nextNode.id]) continue;

					visited[nextNode.id] = true;
					q.add(new Node(nextNode.id, curNode.cost + nextNode.cost));
				}
			}
		}

	}
}