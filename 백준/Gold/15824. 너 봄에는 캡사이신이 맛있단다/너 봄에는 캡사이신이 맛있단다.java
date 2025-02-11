import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.w3c.dom.Node;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int N = Integer.parseInt(s);
		long MOD = 1000000007;

		s = br.readLine();
		String[] split = s.split(" ");

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < split.length; i++) {
			int num = Integer.parseInt(split[i]);
			list.add(num);
		}

		list.sort((a, b) -> a - b);

		if (N == 1) {
			System.out.println(0);
			return;
		}

		long total = 0;
		long[] cnt = new long[N];

		cnt[0] = 1;
		for (int i = 0; i < N-1; i++) {
			cnt[i+1] = (cnt[i] * 2) % MOD;
		}

		for(int i=0; i<N; i++){
			total = (total + (cnt[i] - cnt[N-i-1]) * list.get(i)) % MOD;
		}

		System.out.println(total);
	}
}