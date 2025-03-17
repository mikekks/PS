import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

	static boolean suc = false;
	static String target;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		int T = Integer.parseInt(st);

		while(T > 0){
			T--;

			PriorityQueue<Long> q = new PriorityQueue<>(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return (int) (o1 - o2);
				}
			});

			st = br.readLine();
			int N = Integer.parseInt(st);
			st = br.readLine();
			String[] split = st.split(" ");

			for(int i=0; i<split.length; i++){
				q.add(Long.parseLong(split[i]));
			}

			long ans = 0;

			while (!q.isEmpty() && q.size() > 1) {
				Long c1 = q.poll();
				Long c2 = q.poll();

				ans += (c1 + c2);
				q.add(c1 + c2);
			}

			System.out.println(ans);
		}
	}
}
