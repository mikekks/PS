import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int T = Integer.parseInt(st);

		StringBuilder sb = new StringBuilder();

		while (T > 0) {
			T--;

			st = br.readLine();
			int N = Integer.parseInt(st);

			int ans = N / 2 + 1;
			sb.append(ans + "\n");

			long mid = 0;
			PriorityQueue<Long> min_q = new PriorityQueue<>(Comparator.reverseOrder()); 
			PriorityQueue<Long> max_q = new PriorityQueue<>(); 
			
			long[] arr = new long[N];

			for (int i = 0; i < (N + 10) / 10; i++) {
				st = br.readLine();
				String[] split = st.split(" ");

				for (int j = 0; j < split.length; j++) {
					long num = Long.parseLong(split[j]);
					arr[i * 10 + j] = num;
				}
			}

			int newLineCnt = 18;
			for (int i = 0; i < N; i++) {
				if(i == 0){
					mid = arr[i];
					sb.append(mid + " ");
					continue;
				}

				if(arr[i] >= mid){
					max_q.add(arr[i]);
				}
				else if(arr[i] < mid){
					min_q.add(arr[i]);
				}

				if(max_q.size() - min_q.size() >= 2){
					min_q.add(mid);
					mid = max_q.poll();
				}
				else if(min_q.size() - max_q.size() >= 2){
					max_q.add(mid);
					mid = min_q.poll();
				}

				if(i % 2 == 0){
					sb.append(mid + " ");
				}
				if(i == newLineCnt){
					sb.append("\n");
					newLineCnt += 20;
				}
			}

			sb.append("\n");
		}

		System.out.println(sb);

	}
}
