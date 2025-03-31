import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
	static int N, M, Q;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		String[] split = st.split(" ");

		N = Integer.parseInt(split[0]);
		int L = Integer.parseInt(split[1]);

		Deque<int[]> q = new ArrayDeque<>();

		st = br.readLine();
		split = st.split(" ");

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++){
			int num = Integer.parseInt(split[i]);

			while(!q.isEmpty() && q.peekLast()[0] > num)
				q.pollLast();

			q.add(new int[] {num, i});
			if(q.peek()[1] < i - (L-1)){
				q.poll();
			}
			sb.append(q.peek()[0]).append(" ");
		}

		System.out.println(sb);
	}

}
