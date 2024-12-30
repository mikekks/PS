import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// class Belt {
// 	int start;
// 	int end;
// 	int[] list;
// 	int N;
// 	int ans = 0;
// 	Queue<Integer> q = new LinkedList<>();
//
// 	public void go() {
// 		ans++;
//
// 		start--;
// 		if (start < 1)
// 			start = list.length - 1;
//
// 		end--;
// 		if (end < 1)
// 			end = list.length - 1;
//
// 		int size = q.size();
// 		for (int i = 0; i < size; i++) {
// 			Integer cur = q.poll();
// 			Integer next = cur + 1;
//
// 			if(next > 2*N) next = 1;
//
// 			if (cur == end)
// 				continue;
//
// 			if (list[next] >= 1) {
// 				list[next]--;
//
// 				if (next != end)
// 					q.add(cur);
// 			}
// 			else{
// 				q.add(cur);
// 			}
// 		}
// 	}
//
// 	public void put() {
// 		if (list[start] >= 1) {
// 			list[start]--;
// 			q.add(start);
// 		}
// 	}
//
// 	public void remove() {
// 		if (!q.isEmpty() && q.peek() == end)
// 			q.poll();
// 	}
//
// 	public int getDeadCnt(){
// 		int cnt = 0;
// 		for(int i=1; i<list.length; i++){
// 			if(list[i] == 0) cnt++;
// 		}
//
// 		return cnt;
// 	}
//
// 	public Belt(int start, int end, int[] list, int N) {
// 		this.start = start;
// 		this.end = end;
// 		this.list = list;
// 		this.N = N;
// 	}
// }

class Main {

	static int[] belt;
	static boolean[] robot;
	static int N, K;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		K = Integer.parseInt(split[1]);

		s = br.readLine();
		split = s.split(" ");

		belt = new int[2 * N];
		robot = new boolean[N];

		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(split[i]);
		}

		while (isEnd()) {
			ans++;

			int last = belt[belt.length - 1];
			for (int i = belt.length - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = last;

			for (int i = robot.length - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[N - 1] = false;

			for (int i = robot.length - 1; i > 0; i--) {
				if (robot[i - 1] && !robot[i] && belt[i] >= 1) {
					belt[i]--;
					robot[i] = true;
					robot[i - 1] = false;
				}
			}

			if (belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
			}

		}

		System.out.println(ans);

	}

	private static boolean isEnd() {
		int cnt = 0;
		for (int i = 0; i < belt.length; i++) {
			if (belt[i] == 0) {
				cnt++;
			}
			if (cnt >= K)
				return false;
		}

		return true;
	}
}