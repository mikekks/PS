import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Main {

	static int N;
	static int[][] map = new int[25][25];
	static int[] ud = {-1, 1, 0, 0};
	static int[] lr = {0, 0, -1, 1};
	static int[][] blankCount;

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		Node[] students = new Node[N * N + 5];
		blankCount = new int[N + 1][N + 1];

		Set<Integer>[] mates = new Set[N * N + 5];

		int total = 0;

		for (int i = 1; i <= N * N; i++) {
			mates[i] = new HashSet<>();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 0; k < 4; k++) {
					int ny = i + ud[k];
					int nx = j + lr[k];

					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;

					blankCount[i][j]++;
				}
			}
		}

		for (int i = 0; i < N * N; i++) {
			s = br.readLine();
			String[] split = s.split(" ");

			int cur = Integer.parseInt(split[0]);
			Map<String, Integer> seats = new HashMap<>();
			int[][] matePoint = new int[N + 1][N + 1];

			int maxSeatCount = 0;

			for (int j = 1; j < split.length; j++) {
				int mate = Integer.parseInt(split[j]);
				mates[cur].add(mate);

				if (students[mate] == null)
					continue;

				for (int k = 0; k < 4; k++) {
					int ny = students[mate].y + ud[k];
					int nx = students[mate].x + lr[k];

					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;
					if (map[ny][nx] != 0)
						continue;

					matePoint[ny][nx]++;
				}
			}

			for(int y=1; y<=N; y++){
				for(int x=1; x<=N; x++){
					if(map[y][x] != 0) continue;

					seats.put(y + " " + x, matePoint[y][x]);
					maxSeatCount = Math.max(maxSeatCount, matePoint[y][x]);
				}
			}

			int maxCounts = 0;
			int max_y = 0;
			int max_x = 0;

			int maxBlankCount = 0;
			int max_blank_y = 0;
			int max_blank_x = 0;
			int maxBlankCounts = 0;

			int min_y = N + 1;
			int min_x = N + 1;

			for (String key : seats.keySet()) {
				split = key.split(" ");
				int y = Integer.parseInt(split[0]);
				int x = Integer.parseInt(split[1]);

				if (seats.get(key) == maxSeatCount) {
					maxCounts++;
					max_y = y;
					max_x = x;

					if (blankCount[y][x] > maxBlankCount) {
						max_blank_y = y;
						max_blank_x = x;
						min_y = y;
						min_x = x;

						maxBlankCounts = 1;
						maxBlankCount = blankCount[y][x];
					}
					else if(blankCount[y][x] == maxBlankCount){
						maxBlankCounts++;
						if(y < min_y) {
							min_y = y;
							min_x = x;
						}
						else if(y == min_y && x < min_x){
							min_y = y;
							min_x = x;
						}
					}
				}
			}

			if (maxCounts == 1) {  // 1번 조건
				map[max_y][max_x] = cur;
				students[cur] = new Node(max_y, max_x);

				processBlank(max_y, max_x);
				continue;
			}

			if (maxBlankCounts == 1) {  // 2번 조건
				map[max_blank_y][max_blank_x] = cur;
				students[cur] = new Node(max_blank_y, max_blank_x);

				processBlank(max_blank_y, max_blank_x);
				continue;
			}

			// 3번 조건
			map[min_y][min_x] = cur;
			students[cur] = new Node(min_y, min_x);
			processBlank(min_y, min_x);
		}

		for(int y=1; y<=N; y++){
			for(int x=1; x<=N; x++){
				int cnt = 0;
				int cur = map[y][x];

				for(int k=0; k<4; k++){
					int ny = y + ud[k];
					int nx = x + lr[k];

					if (ny < 1 || nx < 1 || ny > N || nx > N)
						continue;

					if(mates[cur].contains(map[ny][nx])){
						cnt++;
					}
				}

				total += getPoint(cnt);
			}
		}

		System.out.println(total);
	}

	static void processBlank(int y, int x) {
		for (int k = 0; k < 4; k++) {
			int ny = y + ud[k];
			int nx = x + lr[k];

			if (ny < 1 || nx < 1 || ny > N || nx > N)
				continue;

			blankCount[ny][nx]--;
		}
	}


	static int getPoint(int num){
		if(num ==0) {
			return 0;
		}
		else if(num == 1){
			return 1;
		}
		else if(num == 2){
			return 10;
		}
		else if(num == 3){
			return 100;
		}
		else{
			return 1000;
		}
	}

}

