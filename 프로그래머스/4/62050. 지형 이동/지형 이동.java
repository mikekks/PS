import java.util.*;

class Node{
	int y;
	int x;
	int cost;

	public Node(int y, int x, int cost){
		this.y = y;
		this.x = x;
		this.cost = cost;
	}
}

class Solution {

	int[] ud = {-1,1,0,0};
	int[] lr = {0,0,-1,1};
	int[][] cost;
	int INF = 10005;

	public int solution(int[][] land, int height) {
		int answer = 0;
		cost = new int[land.length][land[0].length];
		int n = land.length;
		int m = land[0].length;

		for(int i=0; i<n; i++) Arrays.fill(cost[i], INF);

		// 현재 칸과 이동하려는 칸의 높이 차가 height 이하
		// 사다리를 설치하는데 두 격자 칸의 높이차만큼 비용
		// 최대한 적은 비용이 들도록 사다리를 설치해서 모든 칸으로 이동 가능하도록

		// 다익스트라 2m
		PriorityQueue<Node> q = new PriorityQueue<Node>(new Comparator<Node>(){
			@Override
			public int compare(Node a, Node b){
				return a.cost - b.cost;
			}
		});

		//cost[0][0] = 0;
		q.add(new Node(0,0,0));

		while(!q.isEmpty()){
			Node cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			int cur_cost = cur.cost;

			if(cost[cy][cx] != INF) continue;

			cost[cy][cx] = cur_cost;

			for(int i=0; i<4; i++){
				int ny = cy+ud[i];
				int nx = cx+lr[i];
				if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

				int next_cost = Math.abs(land[ny][nx] - land[cy][cx]);

				if(cost[ny][nx] == INF){
					// cost[ny][nx] = next_cost;
					q.add(new Node(ny, nx, next_cost));
				}
			}
		}

		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(cost[i][j] <= height) continue;

				answer += cost[i][j];
			}
		}

		return answer;
	}
}