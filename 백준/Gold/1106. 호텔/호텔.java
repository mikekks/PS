import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node {
	int n;
	int cost;

	public Node(int n, int cost) {
		this.n = n;
		this.cost = cost;
	}
}

public class Main {

	static int C;
	static int N;
	static int ans;
	static List<Node> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");

		C = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			split = input.split(" ");
			list.add(new Node(Integer.parseInt(split[1]), Integer.parseInt(split[0])));
		}

		//list.sort((a, b) -> b.n / b.cost - a.n / a.cost);

		int[][] dp = new int[N + 1][C + 1];

		for(int i=0; i<=N; i++){
			for(int j=1; j<=C; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

		for(int i=1; i<=N; i++){
			for(int j=1; j<=C; j++){
				Node node = list.get(i-1);
				dp[i][j] = dp[i-1][j];
				if(j-node.n >= 0){
					dp[i][j] = Math.min(dp[i][j], dp[i][j-node.n] + node.cost);
				}
				else if(j-node.n < 0){
					dp[i][j] = Math.min(dp[i][j], node.cost);
				}
			}
		}

		System.out.println(dp[N][C]);
	}

}