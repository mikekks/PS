import java.io.*;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		int ans = 0;

		for(int i=27; i>0; i--){
			int pow = (int) Math.pow(2, i);

			if(N / pow == 1){
				if(N % pow == 0){
					N -= pow;
					K--;
					continue;
				}

				if(K == 1){
					ans = pow * 2 - N;
					break;
				}
				else{
					N -= pow;
					K--;
				}
			}
		}

		System.out.println(ans);

	}
}
