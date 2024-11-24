import java.io.*;

class Main {

	static int N = 0;
	static boolean[] visit = new boolean[21];
	public static void dfs(int idx, long K, long st, long pos){
		if(idx == 0){
			System.out.println();
			return;
		}

		long nPos = pos / idx;
		int cnt = 0;
		for(int i=1; i<=N; i++){
			if(visit[i]) continue;

			cnt++;
			if(K <= st + nPos * (cnt)){
				System.out.print(i + " ");
				visit[i] = true;
				dfs(idx - 1, K, st + nPos * (cnt - 1), nPos);
				break;
			}
		}
	}

	public static void dfs2(int idx, int aIdx, int[] arr, long order, long pos){
		if(idx == 0){
			System.out.println(order + 1);
			return;
		}

		long nPos = pos / idx;
		int cnt = 0;
		for(int i=1; i<=N; i++){
			if(visit[i]) continue;

			cnt++;
			if(arr[aIdx] == i){
				visit[i] = true;
				dfs2(idx-1, aIdx +1, arr,order + nPos * (cnt - 1), nPos);
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		s = br.readLine();
		String[] split = s.split(" ");
		int P = Integer.parseInt(split[0]);

		long pos = 1;
		for(int i=1; i<=N; i++){
			pos *= i;
		}

		if(P == 1){
			long K = Long.parseLong(split[1]);
			dfs(N, K, 0L, pos);
		}
		else{
			int[] arr = new int[N+1];
			for(int i=0; i<N; i++){
				arr[i+1] = Integer.parseInt(split[i + 1]);
			}
			dfs2(N,1, arr, 0, pos);
		}
	}
}