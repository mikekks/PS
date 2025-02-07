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

class Main {
	static int A, B, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] split = s.split(" ");

		A = Integer.parseInt(split[0]);
		B = Integer.parseInt(split[1]);
		C = Integer.parseInt(split[2]);

		if((A + B + C) % 3 != 0){
			System.out.println(0);
			return;
		}

		Queue<String> q = new LinkedList<>();

		boolean[][] visit = new boolean[1505][1505];

		q.add(A + " " + B + " " + C);

		while (!q.isEmpty()){
			String cur = q.poll();
			String[] curs = cur.split(" ");

			int a = Integer.parseInt(curs[0]);
			int b = Integer.parseInt(curs[1]);
			int c = Integer.parseInt(curs[2]);

			if(a + b + c > 1505){
				continue;
			}

			if(a == b && b == c) {
				System.out.println(1);
				return;
			}

			if(a != b){
				int na = a > b ? a-b : a+a;
				int nb = b > a ? b-a : b+b;

				if(!visit[na][nb]){
					q.add(na + " " + nb + " " + c);
					visit[na][nb] = true;
				}
			}

			if(a != c){
				int na = a > c ? a-c : a+a;
				int nc = c > a ? c-a : c+c;

				if(!visit[na][nc]){
					q.add(na + " " + b + " " + nc);
					visit[na][nc] = true;
				}
			}

			if(b != c){
				int nb = b > c ? b-c : b+b;
				int nc = c > b ? c-b : c+c;

				if(!visit[nb][nc]){
					q.add(a + " " + nb + " " + nc);
					visit[nb][nc] = true;
				}
			}
		}

		System.out.println(0);
	}
}