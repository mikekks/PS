import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[] pi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);
		pi = new int[N];

		String target = br.readLine();

		makeTable(target);

		System.out.println(N - pi[N-1]);
	}

	public static void makeTable(String target){
		int pLen = target.length();

		int index = 0;
		for(int i=1; i<pLen; i++){

			while(index > 0 && target.charAt(index) != target.charAt(i)){
				index = pi[index - 1];
			}

			if(target.charAt(index) == target.charAt(i)){
				index += 1;
				pi[i] = index;
			}
		}
	}

}