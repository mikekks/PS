import java.io.*;


class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[][] map = new int[N + 1][N + 1];
		int[][] route = new int[N + 1][N + 1];

		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				map[i][j] = Integer.MAX_VALUE / 3;
			}
		}

		for(int i=0; i<M; i++){
			st = br.readLine();
			split = st.split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int cost = Integer.parseInt(split[2]);

			map[a][b] = cost;
			map[b][a] = cost;
			route[a][b] = b;
			route[b][a] = a;
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
			for(int j=1; j<=N; j++){
				if(i == j){
					sb.append("- ");
					continue;
				}

				sb.append(route[i][j] + " ");
			}

			sb.append("\n");
		}

		System.out.println(sb);

	}
}
