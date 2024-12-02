import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

class Point{
	int a;
	int b;

	public Point(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class Main {
	static long ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		List<Point> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			list.add(new Point(a, b));
		}

		list.sort((p1, p2) -> p1.a - p2.a);

		int cur = list.get(0).a;
		for(int i=0; i<N; i++){
			Point point = list.get(i);

			if(point.b < cur) continue;

			ans += Math.abs(point.b - Math.max(point.a, cur));
			cur = point.b;
		}

		System.out.println(ans);

	}

}