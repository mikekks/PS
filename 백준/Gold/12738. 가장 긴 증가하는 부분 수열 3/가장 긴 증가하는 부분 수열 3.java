import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		int[] arr = new int[N];
		list = new ArrayList<>();

		input = br.readLine();
		String[] split = input.split(" ");
		for (int i = 0; i < split.length; i++) {
			arr[i] = Integer.parseInt(split[i]);
		}

		list.add(arr[0]);

		for (int i = 1; i < N; i++) {
			int cur = arr[i];
			if (cur > list.get(list.size() - 1)) {
				list.add(cur);
			} else {
				int idx = lower_bound(cur);
				list.set(idx, cur);
			}
		}

		System.out.println(list.size());
	}

	public static int lower_bound(int target) {
		int l = 0;
		int r = list.size() - 1;

		while (l < r) {
			int midIdx = l + (r - l) / 2;

			if (target <= list.get(midIdx)) {
				r = midIdx;
			} else {
				l = midIdx + 1;
			}
		}

		return l;
	}

}