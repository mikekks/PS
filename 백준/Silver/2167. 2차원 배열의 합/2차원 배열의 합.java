import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int[][] board = new int[N+5][M+5];

		for(int i=1; i<=N; i++){
			String[] split = br.readLine().split(" ");
			for(int j=0; j<split.length; j++){
				board[i][j+1] = Integer.parseInt(split[j]);
			}
		}

		for(int i=1; i<=N; i++){
			for(int j=1; j<=M-1; j++){
				board[i][j+1] += board[i][j];
			}
		}

		String s2 = br.readLine();
		int K = Integer.parseInt(s2);

		for(int i=0; i<K; i++){
			String[] split = br.readLine().split(" ");
			int sy = Integer.parseInt(split[0]), sx = Integer.parseInt(split[1]);
			int ey = Integer.parseInt(split[2]), ex = Integer.parseInt(split[3]);

			int total = 0;
			for(int j=sy; j<=ey; j++){
				total += board[j][ex] - board[j][sx-1];
			}
			System.out.println(total);
		}



	}
}
