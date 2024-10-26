import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		int[] arr = new int[N];
		int[] index = new int[N];
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
				index[i] = list.size() - 1;
			} else {
				int idx = lowerBound(cur);
				list.set(idx, cur);
				index[i] = idx;
			}
		}

		System.out.println(list.size());

		int last = list.size() - 1;
		List<Integer> ans_list = new ArrayList<>();
		for (int i = N - 1; i >= 0; i--) {
			if (last == index[i]) {
				last--;
				ans_list.add(arr[i]);
			}

		}

		for (int i = ans_list.size() - 1; i >= 0; i--) {
			System.out.print(ans_list.get(i) + " ");
		}
		System.out.println();

	}

	public static int lowerBound(int target) {
		int start = 0;
		int end = list.size();
		int answer = 0;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (list.get(mid) < target) {
				start = mid + 1;
			} else {
				answer = mid;
				end = mid - 1;
			}
		}
		return answer;
	}

}