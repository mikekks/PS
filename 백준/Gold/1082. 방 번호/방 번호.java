import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, target;
	static List<Pair> list;

	static class Pair implements Comparable<Pair> {
		int number;
		int cost;

		Pair(int a, int b) {
			number = a;
			cost = b;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.cost == o.cost)
				return o.number - this.number;
			return o.cost - this.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		int[] cost = new int[N];

		list = new ArrayList<>();
		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			int value = stoi(splitedLine[i]);
			cost[i] = value;
			list.add(new Pair(i, value));
		}

		Collections.sort(list);
		target = stoi(in.readLine());

		if (N == 1) {
			System.out.println(0);
			return;
		}

		String base = "";
		int length = 0;
		if (list.get(N - 1).number == 0) {
			int tempCost = target - list.get(N - 2).cost;
			if (tempCost < 0) {
				// 두번째로 저렴한것이 전체 비용보다 비싼경우
				System.out.println(0);
				return;
			}
			base += Integer.toString(list.get(N - 2).number);
			length = tempCost / list.get(N - 1).cost;
			for (int i = 0; i < length; ++i)
				base += Integer.toString(list.get(N - 1).number);
			target = tempCost - list.get(N - 1).cost * length;
		} else {
			length = target / list.get(N - 1).cost;
			for (int i = 0; i < length; ++i)
				base += Integer.toString(list.get(N - 1).number);
			target = target - list.get(N - 1).cost * length;
		}

		char[] arr = base.toCharArray();
		for (int i = 0; i < base.length(); i++) {
			int curNumberCost = cost[arr[i] - '0'];
			for (int j = N - 1; j >= 0; j--) {
				if (target - (cost[j] - curNumberCost) >= 0) {
					target = target - (cost[j] - curNumberCost);
					arr[i] = (char)(j + '0');
					break;
				}
			}
		}
		System.out.println(arr);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}