import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Main {
	static int N;
	static char[][] map;
	static boolean[][] visit;
	static List<int[]> teachers;
	static int[] ud = {-1, 1, 0, 0};
	static int[] lr = {0, 0, -1, 1};
	static boolean pos = false;

	static void dfs(Set<String> choices, int idx){

		if(idx == 3){

			Queue<int[]> q = new LinkedList<>();

			for(int[] t : teachers){
				q.add(new int[] {0, t[0], t[1]});
				q.add(new int[] {1, t[0], t[1]});
				q.add(new int[] {2, t[0], t[1]});
				q.add(new int[] {3, t[0], t[1]});
			}

			boolean check = true;

			while (!q.isEmpty()){
				int[] poll = q.poll();
				int tArr = poll[0];
				int cy = poll[1];
				int cx = poll[2];

				int ny = cy + ud[tArr];
				int nx = cx + lr[tArr];

				if(ny < 1 || nx < 1 || ny > N || nx > N) continue;

				boolean blocked = false;
				if(choices.contains(cy + " " + cx)) continue;

				if(blocked) continue;

				if(map[ny][nx] == 'S'){
					check = false;
					break;
				}

				q.add(new int[]{tArr, ny, nx});

				if(!check) break;
			}

			if(check){
				pos = true;
			}

			return;
		}

		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				if(visit[i][j]) continue;
				if(map[i][j] != 'X') continue;

				visit[i][j] = true;
				choices.add(i + " " + j);
				dfs(choices, idx+1);
				choices.remove(i + " " + j);
				visit[i][j] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		N = Integer.parseInt(st);
		visit = new boolean[N + 1][N + 1];
		teachers = new ArrayList<>();
		map = new char[N + 1][N + 1];

		for(int i=1; i<=N; i++){
			st = br.readLine();
			String[] split = st.split(" ");
			for(int j=1; j<=N; j++){
				map[i][j] = split[j-1].charAt(0);

				if(map[i][j] == 'T'){
					teachers.add(new int[]{i, j});
				}
			}
		}

		Set<String> list = new HashSet<>();
		dfs(list, 0);

		if(pos){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}

	}
}
