import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);

		List<int[]> times = new ArrayList<>();

		for(int i=0; i<N; i++){
			st = br.readLine();
			String[] split = st.split(" ");
			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);
			times.add(new int[] {s, e});
		}

		times.sort((a, b) -> {
			if(a[1] == b[1]){
				return a[0] - b[0];
			}
			return a[1] - b[1];
		});

		int ans = 0;
		int prev = 0;

		for(int i=0; i<N; i++){
			if(times.get(i)[0] >= prev){
				prev = times.get(i)[1];
				ans++;
			}
		}

		System.out.println(ans);

	}

}
