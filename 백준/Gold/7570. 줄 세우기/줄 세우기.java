import java.io.*;
import java.util.*;

class Main {
	static int N, M, P;
	static int[] index;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		if(N == 1){
			System.out.println(0);
			return;
		}

		index = new int[N + 1];

		st = br.readLine();
		String[] split = st.split(" ");

		int ans = 0;

		for(int i=1; i<=N; i++){
			int idx = Integer.parseInt(split[i - 1]);
			index[idx] = index[idx - 1] + 1;
			ans = Math.max(ans, index[idx]);
		}
		System.out.println(N - ans);

	}
}
