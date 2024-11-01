import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		long G = Integer.parseInt(input);
		long cnt = 0;

		for (long i = 1; i <= 10000000; i++) {
			long add1 = i * i;
			long add2 = add1 - G;
			
			if (add1 - add2 != G || add2 == 0)
				continue;

			double sqrt2 = Math.sqrt(add2);
			double ceil2 = Math.ceil(sqrt2);

			if (sqrt2 != ceil2)
				continue;

			cnt++;
			System.out.println(i);
		}

		if (cnt == 0)
			System.out.println(-1);
	}

}