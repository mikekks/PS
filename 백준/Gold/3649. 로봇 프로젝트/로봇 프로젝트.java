import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = null;

		while((input = br.readLine()) != null) {
			int x = Integer.parseInt(input);
			x = x * 10_000_000;

			input = br.readLine();
			int n = Integer.parseInt(input);
			boolean isSuc = false;
			int[] arr = new int[n];

			for (int i = 0; i < n; i++) {
				input = br.readLine();
				arr[i] = Integer.parseInt(input);
			}

			Arrays.sort(arr);

			int s = 0;
			int e = arr.length - 1;
			int abs_max = 0;

			int answer_a = 0;
			int answer_b = 0;

			while (s < e) {
				int sum = arr[s] + arr[e];

				if (sum == x) {
					isSuc = true;
					answer_a = arr[s];
					answer_b = arr[e];
					break;
				} else if (sum > x) {
					e--;
				} else {
					s++;
				}
			}

			if (!isSuc) {
				System.out.println("danger");
				input = null;
				continue;
			}

			System.out.println("yes " + answer_a + " " + answer_b);
		}
	}

}