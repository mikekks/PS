import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public static int findClosest(List<Integer> list, int target) {
		// List가 정렬되어 있다고 가정합니다.
		int low = 0;
		int high = list.size() - 1;

		// 이분 탐색을 시작합니다.
		while (low <= high) {
			int mid = low + (high - low) / 2;

			// target과 일치하는 값을 찾으면 바로 반환합니다.
			if (list.get(mid) == target) {
				return list.get(mid);
			} else if (list.get(mid) < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		// 이분 탐색 종료 후, target의 예상 위치는 low에 있습니다.
		// low와 high 값이 가리키는 인덱스를 기반으로 가까운 값을 결정합니다.
		if (low >= list.size()) {
			return list.get(list.size() - 1);
		} else if (low == 0) {
			return list.get(0);
		} else {
			int left = list.get(low - 1);
			int right = list.get(low);
			return Math.abs(left - target) <= Math.abs(right - target) ? left : right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ans = 0;
		String input = br.readLine();
		String[] split = input.split(" ");
		int M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);
		int L = Integer.parseInt(split[2]);

		input = br.readLine();
		split = input.split(" ");

		List<Integer> gun = new ArrayList<>();

		for (int i = 0; i < split.length; i++) {
			gun.add(Integer.parseInt(split[i]));
		}

		gun.sort((a, b) -> a - b);

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			split = input.split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);

			int s = 0;
			int e = gun.size() - 1;
			int idx = 0;

			int closest = findClosest(gun, x);

			if(y + Math.abs(closest - x)  <= L){
				ans++;
			}

		}

		System.out.println(ans);
	}

}