import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.*;

import org.w3c.dom.Node;

class Main {
	static int N, C, M;
	static List<Box> boxes;

	static class Box {
		int s;
		int e;
		int cnt;

		public Box(int s, int e, int cnt) {
			this.s = s;
			this.e = e;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");
		N = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		st = br.readLine();
		M = Integer.parseInt(st);

		PriorityQueue<Box> q = new PriorityQueue<>(new Comparator<Box>() {
			@Override
			public int compare(Box o1, Box o2) {
				if(o1.s != o2.s){
					return o2.s - o1.s;
				}

				return o2.cnt - o1.cnt;
			}
		});

		for (int i = 0; i < M; i++) {
			st = br.readLine();
			split = st.split(" ");

			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);
			int cnt = Integer.parseInt(split[2]);

			q.add(new Box(s, e, cnt));
		}

		int cur = N;
		int[] load = new int[N + 1];
		long ans = 0;

		while (cur >= 1) {
			List<Box> list = new ArrayList<>();
			while (!q.isEmpty() && q.peek().s >= cur) {
				list.add(q.poll());
			}

			for(Box box : list){
				int value = Integer.MAX_VALUE / 2;
				for(int i=box.s; i<box.e; i++){
					int canLoad = Math.min(C - load[i], box.cnt);
					value = Math.min(value, canLoad);
				}

				for(int i=box.s; i<box.e; i++){
					load[i] += value;
				}

				ans += value;
			}

			cur--;
		}

		System.out.println(ans);

	}
}
