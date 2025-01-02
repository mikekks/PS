import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node{
	int y;
	int x;
	boolean[] arrow;

	public Node(int y, int x,boolean u, boolean d, boolean l, boolean r) {
		this.y = y;
		this.x = x;
		arrow = new boolean[]{u, d, l, r};
	}

	public Node(int y, int x, boolean[] arrow) {
		this.y = y;
		this.x = x;
		this.arrow = arrow;
	}
}

class Main {
	static int N, M;
	static Node[][] map;
	static int[][] p;
	static boolean[][] visit;

	static int roomIdx = 0;
	static int[] roomCnt;

	static int[] ud = {-1, 1, 0, 0};
	static int[] lr = {0, 0, -1, 1};

	static void bfs(int y, int x){

		roomIdx++;

		int roomSize = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(map[y][x]);
		visit[y][x] = true;

		while (!q.isEmpty()){
			Node node = q.poll();

			roomSize++;
			p[node.y][node.x] = roomIdx;

			for(int i=0; i<4; i++){
				if(!node.arrow[i]) continue;

				int ny = node.y + ud[i];
				int nx = node.x + lr[i];

				if(visit[ny][nx]) continue;

				q.add(new Node(ny, nx, map[ny][nx].arrow));
				visit[ny][nx] = true;

			}
		}

		roomCnt[roomIdx] = roomSize;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);

		map = new Node[N + 2][M + 2];
		p = new int[N + 2][M + 2];
		visit = new boolean[N + 2][M + 2];
		roomCnt = new int[30000];

		for(int i=1; i<=N; i++){
			s = br.readLine();
			split = s.split(" ");
			for(int j=1; j<=M; j++){
				Integer cur = Integer.parseInt(split[j - 1]);
				boolean l = (cur & 1) == 1;
				boolean u = (cur & 2) == 2;
				boolean r = (cur & 4) == 4;
				boolean d = (cur & 8) == 8;

				map[i][j] = new Node(i, j, !u, !d, !l, !r);
			}
		}

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(visit[i][j]) continue;

				bfs(i, j);
			}
		}


		int maxRoomSize = 0;
		int removeMaxSize = 0;

		for(int i=0; i<30000; i++){
			maxRoomSize = Math.max(maxRoomSize, roomCnt[i]);
		}

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				for(int k=0; k<4; k++){
					if(!map[i][j].arrow[k]){
						if(p[i][j] == p[i+ud[k]][j+lr[k]]) continue;

						if(roomCnt[p[i][j]] + roomCnt[p[i+ud[k]][j+lr[k]]] > removeMaxSize){
							removeMaxSize = roomCnt[p[i][j]] + roomCnt[p[i+ud[k]][j+lr[k]]];
						}
					}
				}
			}
		}

		System.out.println(roomIdx);
		System.out.println(maxRoomSize);
		System.out.println(removeMaxSize);

	}
}