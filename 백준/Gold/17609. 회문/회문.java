import java.util.Scanner;

public class Main {
	private static int check(String input, int chance, int s, int e) {

		if(chance >= 2) return 2;

		while (s < e) {
			if (input.charAt(s) != input.charAt(e)) {
				return Math.min(check(input, chance+1, s, e-1), check(input, chance+1, s+1, e));
			}

			s++;
			e--;
		}

		return chance;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			String input = sc.next();
			int s = 0;
			int e = input.length() - 1;

			int ret = check(input, 0, s, e);

			System.out.println(ret);
		}

	}
}