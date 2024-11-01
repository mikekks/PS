import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		input = br.readLine();
		int M = Integer.parseInt(input);

		input = br.readLine();
		String[] split = input.split(" ");

		List<Integer> list = new ArrayList<>();

		for(String s : split){
			list.add(Integer.parseInt(s));
		}

		list.sort((a,b) -> a - b);

		List<Integer> diff = new ArrayList<>();
		for(int i=1; i<list.size(); i++){
			diff.add(list.get(i) - list.get(i - 1));
		}

		diff.sort((a, b) -> b - a);

		int ans = 0;
		for(int i=M-1; i<diff.size(); i++){
			ans += diff.get(i);
		}

		System.out.println(ans);
	}

}