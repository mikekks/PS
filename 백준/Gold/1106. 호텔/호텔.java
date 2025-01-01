import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class City {
	int cost;
	int award;

	public City(int cost, int award) {
		this.cost = cost;
		this.award = award;
	}
}

class Main {
	static int C, N;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");
		C = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);

		List<City> cities = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			split = s.split(" ");
			int cost = Integer.parseInt(split[0]);
			int people = Integer.parseInt(split[1]);

			cities.add(new City(cost, people));
		}

		int[] dp = new int[C + 105];

		for (int i = 1; i < C + 105; i++)
			dp[i] = Integer.MAX_VALUE / 2;

		for (int i = 0; i < cities.size(); i++) {
			for (int c = 1; c < C + 105; c++) {
				City city = cities.get(i);

				if (city.award <= c) {
					dp[c] = Math.min(dp[c], city.cost + dp[c - city.award]);
				}
			}
		}

		int ans = Integer.MAX_VALUE;
		for (int i = C; i < C + 105; i++) {
			ans = Math.min(ans, dp[i]);
		}

		System.out.println(ans);

	}
}