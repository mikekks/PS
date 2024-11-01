import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int T = Integer.parseInt(input);

		while (T-- > 0) {
			input = br.readLine();
			int N = Integer.parseInt(input);

			input = br.readLine();
			String[] split = input.split(" ");

			input = br.readLine();
			int money = Integer.parseInt(input);
			int[] coin = new int[10005];

			for (int i = 0; i < N; i++) {
				int cur = Integer.parseInt(split[i]);
				coin[cur] += 1;

				for (int j = cur; j <= money; j++) {
					if(j-cur >= 0){
						coin[j] += coin[j-cur];
					}
				}
			}

			System.out.println(coin[money]);
		}
	}

}