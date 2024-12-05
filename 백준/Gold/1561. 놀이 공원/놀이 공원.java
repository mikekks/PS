import java.io.*;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		long N = Long.parseLong(split[0]);
		int M = Integer.parseInt(split[1]);

		int[] arr = new int[M];

		s = br.readLine();
		split = s.split(" ");
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(split[i]);
		}

		if (M >= N) {
			System.out.println(N);
			return;
		}

		long low = 0;
		long high = N * 30L;

		while (low <= high) {
			long time = (low + high) / 2;
			long people = M;

			for (int i = 0; i < M; i++) {
				people += time / arr[i];
			}

			if (people < N) {
				low = time + 1;
			} else {
				high = time - 1;
			}
		}

		long prev = M;
		for (int i = 0; i < M; i++) {
			prev += (low - 1) / arr[i];
		}

		for (int i = 0; i < M; i++) {
			if (low % arr[i] == 0) {
				prev++;
			}

			if (prev == N) {
				System.out.println(i + 1);
				break;
			}
		}
	}
}