import java.io.*;
import java.util.*;

class Main {
	static int N, H;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		int[] place = new int[1_000_005];

		for(int i=1; i<=N; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			int num = Integer.parseInt(split[0]);
			int oil = Integer.parseInt(split[1]);
			place[num] = oil;
		}

		st = br.readLine();
		String[] split = st.split(" ");

		int L = Integer.parseInt(split[0]);
		int P = Integer.parseInt(split[1]);

		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});

		int cur = 0;
		int curOil = P;

		while(cur < L){
			if(curOil == 0){
				if(q.isEmpty()){
					System.out.println(-1);
					return;
				}

				Integer tOil = q.poll();
				curOil += tOil;
				ans++;
			}

			curOil--;
			cur++;

			if(place[cur] != 0){
				q.add(place[cur]);
			}
		}

		System.out.println(ans);
	}
}
