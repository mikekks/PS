import java.io.*;
import java.util.Stack;

class Main {
	static int N, P, X, K;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		int ans = 0;
		Stack<int[]> stack = new Stack<>();

		for(int i=0; i<N; i++){
			st = br.readLine();
			String[] split = st.split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);

			if(i == 0){
				if(y == 0) continue;
				
				stack.push(new int[] {x, y});
				continue;
			}

			while(!stack.isEmpty() && stack.peek()[1] > y){
				int[] pop = stack.pop();
				ans++;
			}

			if(stack.isEmpty() || stack.peek()[1] < y){
				if(y == 0) continue;

				stack.push(new int[] {x, y});
			}
		}

		ans += stack.size();

		System.out.println(ans);


	}

}
