import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		s = br.readLine();
		String[] split = s.split(" ");
		int[] arr = new int[N];
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(split[i]);
		}

		int ans = 0;
		for(int i=0; i<N; i++){
			int cnt = 0;
			double tmp = 0;

			for (int j=i-1; j>=0; j--) {
				double slope = (double) (arr[i] - arr[j]) / (i - j);

				if (j == i-1 || tmp > slope) {
					cnt++;
					tmp = slope;
				}
			}

			for (int j=i+1; j<N; j++) {
				double slope = (double) (arr[i] - arr[j]) / (i - j);

				if (j == i+1 || tmp < slope) {
					cnt++;
					tmp = slope;
				}
			}

			ans = Math.max(ans, cnt);
		}

		System.out.println(ans);
	}

}

