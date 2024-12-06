import java.io.*;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int N = Integer.parseInt(s);
		int[] arr = new int[N];

		for(int i=0; i<N; i++){
			s = br.readLine();
			arr[i] = Integer.parseInt(s);
		}

		int[] dp = new int[N];

		int max = 0;

		for(int i=0; i<N; i++){
			dp[i] = 1;

			for(int j=0; j<i; j++){
				if(arr[j] < arr[i]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}

			max = Math.max(dp[i], max);
		}

		System.out.println(N - max);
	}
}