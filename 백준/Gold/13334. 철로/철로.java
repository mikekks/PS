import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Point{
	int s;
	int e;

	public Point(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

class Main {

	static int[] people;
	static int[] cnt;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int N = Integer.parseInt(s);

		List<Point> list = new ArrayList<>();

		for(int i=0; i<N; i++){
			s = br.readLine();
			String[] split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			int start = Math.min(a, b);
			int end = Math.max(a, b);

			list.add(new Point(start, end));
		}

		s = br.readLine();
		int d = Integer.parseInt(s);

		list.sort((a,b) -> a.e - b.e);

		int ans = 0;

		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		for(int i=0; i<list.size(); i++){
			Point cur = list.get(i);

			int end = cur.e;
			int start = end - d;

			q.add(cur.s);

			while(!q.isEmpty() && q.peek() < start){
				q.poll();
			}

			int tmp = q.size();
			ans = Math.max(ans, tmp);

		}

		System.out.println(ans);
	}
}