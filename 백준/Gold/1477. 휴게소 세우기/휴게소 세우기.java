import java.io.*;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int L = Integer.parseInt(split[2]);

		int[] arr = new int[N + 2];  // N == 0 고려


		if(N != 0) {
			s = br.readLine();
			split = s.split(" ");
			for (int i = 0; i < N; i++) {
				arr[i + 1] = Integer.parseInt(split[i]);
			}
		}

		arr[N+1] = L;

		int ans = 0;
		Arrays.sort(arr);

		for(int k=1; k<=L; k++){
			int mid = k;

			int cnt = 0;
			for (int i = 1; i <= N + 1; i++) {
				int diff = arr[i] - arr[i - 1];
				cnt += (diff / mid);
				if(diff % mid == 0) cnt--;
			}

			if(cnt <= M){
				ans = mid;
				break;
			}
		}

		System.out.println(ans);

	}
}