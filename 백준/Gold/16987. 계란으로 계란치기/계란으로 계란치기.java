import java.io.*;

class Main {

	static int[][] arr;
	static boolean[] isBroken;
	static int ans;
	static int N;

	static void dfs(int idx){

		if(idx == N+1){
			int cnt = 0;

			for(int i=1; i<=N; i++){
				if(isBroken[i]) cnt++;
			}

			ans = Math.max(ans, cnt);
			return;
		}

		if(isBroken[idx]){
			dfs(idx+1);
			return;
		}

		boolean otherLive = false;
		for(int i=1; i<=N; i++){
			if(isBroken[i]) continue;
			if(idx == i) continue;

			otherLive = true;
			boolean curCheck = false, diffCheck = false;

			arr[idx][0] -= arr[i][1];
			if(arr[idx][0] <= 0) {
				isBroken[idx] = true;
				curCheck = true;
			}

			arr[i][0] -= arr[idx][1];
			if(arr[i][0] <= 0){
				isBroken[i] = true;
				diffCheck = true;
			}

			dfs(idx + 1);

			arr[idx][0] += arr[i][1];
			arr[i][0] += arr[idx][1];

			if(curCheck) isBroken[idx] = false;
			if(diffCheck) isBroken[i] = false;
		}

		if(!otherLive){
			dfs(idx+1);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		N = Integer.parseInt(st);

		arr = new int[N + 1][2];  // 내구도, 무게
		isBroken = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			String[] split = st.split(" ");
			arr[i][0] = Integer.parseInt(split[0]);
			arr[i][1] = Integer.parseInt(split[1]);
		}

		dfs(1);

		System.out.println(ans);

	}
}
