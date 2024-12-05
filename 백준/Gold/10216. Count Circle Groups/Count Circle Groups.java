import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Point{
	int y;
	int x;
	int r;

	public Point(int y, int x, int r) {
		this.y = y;
		this.x = x;
		this.r = r;
	}
}

class Edge{
	int s;
	int e;

	public Edge(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

class Main {

	static int[] p;

	public static int findParent(int n){
		if(p[n] == n) return n;

		return p[n] = findParent(p[n]);
	}

	public static void merge(int a, int b){
		int pa = findParent(a);
		int pb = findParent(b);

		if(pa != pb){
			if(pa < pb){
				p[pb] = pa;
			}
			else{
				p[pa] = pb;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int T = Integer.parseInt(s);

		while(T > 0){
			T--;

			s = br.readLine();
			int N = Integer.parseInt(s);
			List<Point> points = new ArrayList<>();
			List<Edge> edges = new ArrayList<>();

			for(int i=0; i<N; i++){
				s = br.readLine();
				String[] split = s.split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int r = Integer.parseInt(split[2]);
				points.add(new Point(y, x, r));
			}

			for(int i=0; i<N; i++){
				Point point = points.get(i);

				for(int j=i+1; j<N; j++){
					Point compare = points.get(j);

					int diff = (int) (Math.pow(point.x - compare.x, 2) + Math.pow(point.y - compare.y, 2));

					if(Math.pow(point.r + compare.r, 2) >= diff){
						edges.add(new Edge(i, j));
					}
				}
			}

			p = new int[N];

			for(int i=0; i<N; i++){
				p[i] = i;
			}

			for(int i=0; i<edges.size(); i++){
				Edge edge = edges.get(i);
				merge(edge.s, edge.e);
			}

			int ans = 0;

			for(int i=0; i<N; i++){
				int parent = findParent(i);

				if(p[parent] == i){
					ans++;
				}
			}

			System.out.println(ans);

		}

	}

}