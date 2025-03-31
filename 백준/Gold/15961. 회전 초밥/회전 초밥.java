import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
	static int N, M, Q;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");

		N = Integer.parseInt(split[0]);
		int d = Integer.parseInt(split[1]);
		int k = Integer.parseInt(split[2]);
		int c = Integer.parseInt(split[3]);

		int[] plate = new int[N + 1];
		int[] total = new int[d + 1];

		for (int i = 0; i < N; i++) {
			st = br.readLine();
			int num = Integer.parseInt(st);
			plate[i] = num;
		}

		int cnt = 0;
		int ans = 0;

		for (int i = 0; i < k; i++) {
			total[plate[i]]++;
			if (total[plate[i]] == 1)
				cnt++;
		}
		ans = cnt;

		if(total[c] == 0){
			ans += 1;
		}



		for (int i = k; i < N + k - 1; i++) {
			int idx = i;
			if (idx >= N)
				idx %= N;

			total[plate[idx]]++;
			if (total[plate[idx]] == 1) {
				cnt++;
			}

			total[plate[i - k]]--;
			if (total[plate[i - k]] == 0) {
				cnt--;
			}
			
			if(total[c] == 0){
				ans = Math.max(cnt + 1, ans);
			}
			else{
				ans = Math.max(cnt, ans);
			}

		}

		System.out.println(ans);

	}

}
