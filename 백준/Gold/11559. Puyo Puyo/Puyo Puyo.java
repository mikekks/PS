import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[13][7];

		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '.') {
					map[i][j] = 0;
				} else if (s.charAt(j) == 'R') {
					map[i][j] = 1;
				} else if (s.charAt(j) == 'G') {
					map[i][j] = 2;
				} else if (s.charAt(j) == 'B') {
					map[i][j] = 3;
				} else if (s.charAt(j) == 'P') {
					map[i][j] = 4;
				} else if (s.charAt(j) == 'Y') {
					map[i][j] = 5;
				}
			}
		}

		int ans = 0;

		int[] ud = {-1, 1, 0, 0};
		int[] lr = {0, 0, -1, 1};

		while (true) {
			boolean[][] visited = new boolean[13][7];
			boolean flag = false;

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != 0 && !visited[i][j]) {
						Queue<int[]> q = new LinkedList<>();
						List<int[]> list = new ArrayList<>();

						q.add(new int[] {i, j});
						list.add(new int[] {i, j});
						visited[i][j] = true;

						int cnt = 0;

						while (!q.isEmpty()) {
							int[] poll = q.poll();
							int y = poll[0];
							int x = poll[1];

							cnt++;

							for (int k = 0; k < 4; k++) {
								int ny = y + ud[k];
								int nx = x + lr[k];

								if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6)
									continue;
								if (map[ny][nx] != map[i][j] || visited[ny][nx])
									continue;

								q.add(new int[] {ny, nx});
								visited[ny][nx] = true;
								list.add(new int[] {ny, nx});
							}
						}

						if (cnt >= 4) {
							flag = true;
							for (int[] l : list) {
								map[l[0]][l[1]] = 0;
							}
						}
					}
				}
			}

			if (!flag) {
				break;
			} else {
				ans++;
				for (int i = 0; i < 6; i++) {
					for (int j = 11; j >= 0; j--) {
						if (map[j][i] != 0) {
							int y = j;

							while (true) {
								if (y + 1 < 12 && map[y + 1][i] == 0) {
									map[y + 1][i] = map[y][i];
									map[y][i] = 0;
									y++;
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}

		System.out.println(ans);

	}
}
