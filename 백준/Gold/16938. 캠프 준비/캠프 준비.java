import java.io.*;
import java.util.*;

class Main {
	static int N, L, R, X;
	static int[] arr;
	static int ans = 0;
	static boolean[] visit;

	static void dfs(int idx, int count){
		if(count >= 2){

			int minValue = Integer.MAX_VALUE;
			int maxValue = Integer.MIN_VALUE;
			int total = 0;
			int qCnt = 0;

			for(int i=0; i<N; i++){
				if(visit[i]){
					qCnt++;
					total += arr[i];
					minValue = Math.min(minValue, arr[i]);
					maxValue = Math.max(maxValue, arr[i]);
				}
			}

			if(qCnt >= 2 && total >= L && total <= R && (maxValue - minValue >= X)) {
				ans++;
			}

		}


		for(int i=idx; i<N; i++){
			visit[i] = true;
			dfs(i+1, count + 1);
			visit[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		L = Integer.parseInt(split[1]);
		R = Integer.parseInt(split[2]);
		X = Integer.parseInt(split[3]);

		arr = new int[N];
		visit = new boolean[N];

		s = br.readLine();
		split = s.split(" ");

		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(split[i]);
		}

		dfs(0, 0);
		System.out.println(ans);

	}
}
