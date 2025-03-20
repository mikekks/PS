import java.io.*;

class Main {

	static int ans = 0;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		map = new int[N + 1][N + 1];

		for (int i = 1; i <=M; i++) {
			s = br.readLine();
			split = s.split(" ");
			int big = Integer.parseInt(split[0]);
			int small = Integer.parseInt(split[1]);

			map[big][small] = 1;
			map[small][big] = -1;
		}

		for(int k=1; k<=N; k++){
			for(int i=1; i<=N; i++){
				for(int j=1; j<=N; j++){
					if(map[i][k] == 1 && map[k][j] == 1){
						map[i][j] = 1;
					}
					else if(map[i][k] == -1 && map[k][j] == -1){
						map[i][j] = -1;
					}
				}
			}
		}

		int ans = 0;
		int mid = (N + 1) / 2; // 중간 값의 인덱스

		for(int i=1; i<=N; i++){
			int largerCnt = 0;
			int smallerCnt = 0;

			for(int j=1; j<=N; j++){
				if(i != j){
					if(map[i][j] == 1){
						smallerCnt++;
					}
					else if(map[i][j] == -1){
						largerCnt++;
					}
				}
			}

			if (largerCnt >= mid || smallerCnt >= mid) {
				ans++;
			}
		}

		System.out.println(ans);

	}
}
