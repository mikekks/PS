import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String[] split = input.split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int gcd = gcd(N, M);
		int answer = M - gcd;

		System.out.println(answer);

	}

	public static int gcd(int a, int b){
		if(b == 0) return a;

		return gcd(b, a%b);
	}
}