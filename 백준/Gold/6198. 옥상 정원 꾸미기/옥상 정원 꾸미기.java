import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		int N = Integer.parseInt(input);
		long ans = 0;
		Stack<Integer> st = new Stack<>();

		for(int i=0; i<N; i++){
			int h = Integer.parseInt(br.readLine());

			while(!st.isEmpty()){
				if(st.peek() <= h){
					st.pop();
				}
				else break;
			}

			ans += st.size();
			st.push(h);
		}

		System.out.println(ans);

	}

}