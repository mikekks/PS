import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String T = br.readLine();

		while (S.length() < T.length()) {
			char ch = T.charAt(T.length() - 1);
			String next = "";

			if (ch == 'A') {
				next = T.substring(0, T.length() - 1);
				T = next;
			} else if (ch == 'B') {
				next = T.substring(0, T.length() - 1);
				String reversed = new StringBuilder(next).reverse().toString();
				T = reversed;
			}
		}

		if(S.equals(T)){
			System.out.println(1);
			return;
		}

		System.out.println(0);
	}

}