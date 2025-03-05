import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {

	static int[] arr;
	static int C;
	static List<Integer> left = new ArrayList<>();
	static List<Integer> right = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		s = br.readLine();
		split = s.split(" ");

		arr = new int[N];

		for(int i=0; i<N; i++){
			int w = Integer.parseInt(split[i]);
			arr[i] = w;
		}

		comb(left, 0, N/2, 0);
		comb(right, N/2, N, 0);

		right.sort((a, b) -> (a - b));

		int cnt = 0;
		int idx = 0;
		for (int i = 0; i < left.size(); i++) {
			idx = upperbound(0, right.size() - 1, left.get(i));
			cnt += idx + 1;
		}

		System.out.println(cnt);

	}

	static int upperbound(int s, int e, int val) {
		while (s <= e) {
			int m = (s + e) / 2;
			if (right.get(m) <= C - val) s = m + 1;
			else e = m - 1;

		}

		return e;
	}

	static void comb(List<Integer> list, int start, int end, int sum){
		if(sum > C){
			return;
		}

		if(start == end){
			list.add(sum);
			return;
		}

		comb(list, start + 1, end, sum);
		comb(list, start + 1, end, sum + arr[start]);
	}
}