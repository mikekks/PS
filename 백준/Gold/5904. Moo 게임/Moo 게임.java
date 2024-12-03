import java.io.*;

class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		N = Integer.parseInt(s);

		int len = 3;
		int cnt = 1;

		while(len < N){

			len = 2*len + 1 + cnt + 2;
			cnt++;
		}

		moo(len,cnt-1);

	}

	public static void moo(int len, int cnt){
		int fix = (len - 1 - cnt - 2) / 2;
		if (cnt == 0) {
			if (N == 1) {
				System.out.print("m");
				return;
			} else {
				System.out.print("o");
				return;
			}
		}

		int mid = len - 2*fix;

		if(N <= fix){ // 앞에 있는 경우
			moo(fix, cnt-1);
		}
		else if(N <= fix + mid){  // 중간에 있는 경우
			if(N == fix + 1){
				System.out.println("m");
			}
			else{
				System.out.println("o");
			}
			return;
		}
		else{ // 끝에 있는 경우
			N -= (fix + mid);
			moo(fix, cnt-1);
		}
	}

}