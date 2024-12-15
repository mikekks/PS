import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Point {
	int x;
	int a;

	public Point(int x, int a) {
		this.x = x;
		this.a = a;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int N = Integer.parseInt(s);

		List<Point> list = new ArrayList<>();

		long sum = 0;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			String[] split = s.split(" ");

			int x = Integer.parseInt(split[0]);
			int a = Integer.parseInt(split[1]);
			list.add(new Point(x, a));
			sum += a;
		}

		list.sort((a, b) -> a.x - b.x);

		long total = 0;
		for (Point p : list) {
			total += p.a;

			if((sum+1)/2 <= total){
				System.out.println(p.x);
				return;
			}
		}
	}

}