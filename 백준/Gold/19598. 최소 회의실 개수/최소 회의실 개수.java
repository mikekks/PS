import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int N = Integer.parseInt(st);

		int ans = 0;

		PriorityQueue<long[]> before_q = new PriorityQueue<>(new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return (int) (o2[1] - o1[1]);
			}
		});

		PriorityQueue<long[]> after_q = new PriorityQueue<>(new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return (int) (o2[0] - o1[0]);
			}
		});

		for(int i=0; i<N; i++){
			st = br.readLine();
			String[] split = st.split(" ");

			long s = Long.parseLong(split[0]);
			long e = Long.parseLong(split[1]);
			before_q.add(new long[] {s, e});
		}

		int roomCount = 0;
		while(!before_q.isEmpty()){
			long[] curNode = before_q.poll();
			roomCount++;
			after_q.add(curNode);

			while(!before_q.isEmpty() && before_q.peek()[1] == curNode[1]){
				long[] node = before_q.poll();
				roomCount++;
				after_q.add(node);
			}

			while(!after_q.isEmpty() && after_q.peek()[0] >= curNode[1]){
				after_q.poll();
				roomCount--;
			}

			ans = Math.max(roomCount, ans);
		}

		System.out.println(ans);

	}
}
