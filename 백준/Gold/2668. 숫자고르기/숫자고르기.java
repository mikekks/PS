import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {

	static int ans = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);
		int ans = N;

		int[] arr = new int[N + 1];
		List<Integer>[] map = new List[N + 1];

		for (int i = 1; i <= N; i++)
			map[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			arr[i] = Integer.parseInt(st);
			map[arr[i]].add(i);
		}

		boolean[] isDead = new boolean[N + 1];
		boolean continueCheck = true;
		while (continueCheck) {
			continueCheck = false;

			for (int i = 1; i <= N; i++) {
				if (!isDead[i] && map[i].isEmpty()) {

					int finalI = i;
					map[arr[i]].removeIf(a -> a.equals(finalI));
					isDead[i] = true;

					continueCheck = true;
					ans--;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(ans + "\n");

		for (int i = 1; i <= N; i++) {
			if (map[i].isEmpty()) continue;

			sb.append(i + "\n");
		}

		System.out.println(sb);

	}
}
