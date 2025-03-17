import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.w3c.dom.Node;

class Main {
	static int N, M;
	static int[][] map;
	static int[][] route;

	static String getPath(Integer start, Integer end){
		StringBuilder ret = new StringBuilder(start);

		while(!start.equals(end)){
			ret.append(start + " ");
			start = route[start][end];
		}

		return ret.append(end).toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		N = Integer.parseInt(st);
		st = br.readLine();
		M = Integer.parseInt(st);

		map = new int[N + 1][N + 1];
		route = new int[N + 1][N + 1];

		for(int i=1; i<=N; i++){

			for(int j=1; j<=N; j++){
				if(i == j) map[i][j] = 0;
				else map[i][j] = Integer.MAX_VALUE / 3;
			}
		}

		for(int i=0; i<M; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int c = Integer.parseInt(split[2]);

			map[a][b] = Math.min(c, map[a][b]);
			route[a][b] = b;
		}

		for(int k=1; k<=N; k++){
			for(int i=1; i<=N; i++){
				for(int j=1; j<=N; j++){
					if(map[i][k] + map[k][j] < map[i][j]){
						map[i][j] = map[i][k] + map[k][j];
						route[i][j] = route[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++) {
				int num = map[i][j] == Integer.MAX_VALUE / 3 ? 0 : map[i][j];
				sb.append(num + " ");
			}
			sb.append("\n");
		}

		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++) {
				if(map[i][j] == 0 || map[i][j] == Integer.MAX_VALUE / 3){
					sb.append(0 + "\n");
					continue;
				}

				String path = getPath(i, j);
				String[] split = path.split(" ");
				sb.append(split.length + " " + path + "\n");
			}
		}


		System.out.println(sb);
	}
}
