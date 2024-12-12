import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {

	static List<Integer> lower = new ArrayList<>();
	static List<Integer> higher = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int H = Integer.parseInt(split[1]);

		int lmin = 987654321;
		int hmin = 987654321;

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			int num = Integer.parseInt(s);

			if (i % 2 == 0) {  // 바닥
				lower.add(num);
				lmin = Math.min(lmin, num);
			} else {  // 천장
				higher.add(num);
				hmin = Math.min(hmin, num);
			}
		}

		lower.sort((a, b) -> a - b);
		higher.sort((a, b) -> a - b);

		int len = N / 2;

		int[] ans = new int[N + 1];

		for (int i = 1; i <= H; i++) {
			int low = lower_bound(i, lower);
			int high = lower_bound(H-i+1, higher);

			int sum = (len - low) + (len - high);
			ans[sum]++;
		}

		for(int i=0; i<ans.length; i++){
			if(ans[i] != 0){
				System.out.println(i + " " + ans[i]);
				break;
			}
		}

	}

	public static int lower_bound(int target, List<Integer> arr) {
		int left = 0, right = arr.size();

		while (left < right) {
			int mid = (left + right) / 2;
			if (arr.get(mid) < target) {
				left = mid + 1; // target보다 크지 않으면 오른쪽 탐색
			} else {
				right = mid; // target보다 크면 왼쪽으로 범위 축소
			}
		}
		return left; // upper bound 위치 반환
	}

}