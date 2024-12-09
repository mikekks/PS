import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Ball {
	int n;
	int color;
	int size;

	public Ball(int n, int color, int size) {
		this.n = n;
		this.color = color;
		this.size = size;
	}
}

class Main {

	static List<Ball> balls = new ArrayList<>();
	static List<Ball>[] pos;
	static List<Integer>[] same_sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int N = Integer.parseInt(s);

		pos = new ArrayList[N + 1];
		same_sum = new ArrayList[N + 1];
		Ball[] pool = new Ball[N + 1];
		int[] sum = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			pos[i] = new ArrayList<>();
			same_sum[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			String[] split = s.split(" ");
			int color = Integer.parseInt(split[0]);
			int size = Integer.parseInt(split[1]);

			balls.add(new Ball(i + 1, color, size));
			pos[color].add(new Ball(i + 1, color, size));
			pool[i] = new Ball(i + 1, color, size);
		}

		for (int i = 0; i <= N; i++) {
			if(pos[i].isEmpty()) continue;

			pos[i].sort((a, b) -> a.size - b.size);

			same_sum[i].add(0, pos[i].get(0).size);

			for(int j=1; j<pos[i].size(); j++){
				same_sum[i].add(j, pos[i].get(j).size + same_sum[i].get(j-1));
			}
		}

		balls.sort((a, b) -> a.size - b.size);

		sum[0] = balls.get(0).size;
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i - 1] + balls.get(i).size;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			Ball ball = pool[i];

			int idx = lower_bound(0, N - 1, ball.size);

			// 같은 color 제외
			int sameIdx = lower_bound_same(ball.color, ball.size);

			sb.append(sum[idx] - same_sum[ball.color].get(sameIdx) + "\n");
		}

		System.out.print(sb);
	}

	public static int lower_bound(int s, int e, int target) {

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (target <= balls.get(mid).size) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return s;
	}

	public static int lower_bound_same(int color, int target) {

		int s = 0;
		int e = pos[color].size() - 1;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (target <= pos[color].get(mid).size) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return s;
	}
}