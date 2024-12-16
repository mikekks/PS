import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Road{
	int x;
	int y;
	int z;

	public Road(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}


class Main {

	static int[] p;
	static long ans;

	public static int findParent(int n){
		if(p[n] == n) return n;

		return p[n] = findParent(p[n]);
	}

	public static void merge(int a, int b, int cost){
		int pa = findParent(a);
		int pb = findParent(b);

		if(pa != pb){
			p[pb] = pa;
			ans += cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 최소 스패닝 트리

		while (true){
			ans = 0;
			long total = 0;

			String s = br.readLine();
			String[] split = s.split(" ");
			int M = Integer.parseInt(split[0]);
			int N = Integer.parseInt(split[1]);

			if(M == 0 && N == 0) break;

			PriorityQueue<Road> q = new PriorityQueue<>(new Comparator<Road>() {
				@Override
				public int compare(Road o1, Road o2) {
					return o1.z - o2.z;
				}
			});

			for(int i=0; i<N; i++){
				s = br.readLine();
				split = s.split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int z = Integer.parseInt(split[2]);

				total += z;
				q.add(new Road(x, y, z));
			}

			p = new int[M + 1];

			for(int i=0; i<=M; i++) p[i] = i;

			while(!q.isEmpty()){
				Road road = q.poll();

				merge(road.x, road.y, road.z);
			}

			System.out.println(total - ans);
		}

	}

}