import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Route {
	Integer cur;
	Integer cnt;
	Set<Integer> list;

	public Route(Integer cur, Integer cnt, Set<Integer> list) {
		this.cur = cur;
		this.cnt = cnt;
		this.list = list;
	}
}

class Main {

	static int N;
	static int[] p;
	static List<Set<Integer>> circularList = new ArrayList<>();
	static List<Integer>[] map;

	public static int findParent(int n) {
		if (p[n] == n)
			return n;

		return p[n] = findParent(p[n]);
	}

	public static void merge(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);

		if (pa != pb) {
			p[Math.max(pa, pb)] = Math.min(pa, pb);
		} else {
			// 스냅샷 찍듯이

			Queue<Route> q = new LinkedList<>();
			boolean[] visit = new boolean[N + 1];
			q.add(new Route(a, 0, Set.of(a)));
			visit[a] = true;

			while (!q.isEmpty()) {
				Route route = q.poll();

				if (route.list.contains(b)) {
					circularList.add(route.list);
					return;
				}

				for (Integer next : map[route.cur]) {
					if (visit[next])
						continue;
					if(route.cur == a && next == b) continue;

					HashSet<Integer> newSet = new HashSet<>(route.list);
					newSet.add(next);
					visit[next] = true;
					q.add(new Route(next, route.cnt + 1, newSet));
				}


			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		N = Integer.parseInt(s);
		p = new int[N + 1];
		map = new ArrayList[N + 1];

		// 순환선을 어떻게 구하냐? -> 유니온 파인드
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			String[] split = s.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			map[a].add(b);
			map[b].add(a);

			merge(a, b);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			int pa = findParent(i);

			if (circularList.contains(pa)) {
				sb.append(0 + " ");
			} else {
				Queue<Route> q = new LinkedList<>();
				boolean[] visit = new boolean[N + 1];
				q.add(new Route(i, 0, null));
				visit[i] = true;

				while(!q.isEmpty()){
					Route route = q.poll();
					boolean check = false;

					for(Set<Integer> set : circularList){
						if (set.contains(route.cur)) {
							sb.append(route.cnt + " ");
							check = true;
							break;
						}
					}

					if(check){
						break;
					}

					for (Integer next : map[route.cur]) {
						if (visit[next])
							continue;

						visit[next] = true;
						q.add(new Route(next, route.cnt + 1, null));
					}
				}
			}
		}

		System.out.println(sb);
	}
}