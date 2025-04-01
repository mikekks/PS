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
import java.util.Stack;

class Main {
	static int N, M, Q;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();

		N = Integer.parseInt(st);
		arr = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			st = br.readLine();
			arr[i] = Integer.parseInt(st);
		}

		Stack<Integer> s = new Stack<>();
		s.push(0);
		long ans = 0;

		for (int i = 1; i <= N + 1; i++) {

			while (!s.isEmpty() && arr[i] < arr[s.peek()]) {
				Integer top = s.pop();

				long tmp = (long)(i - (s.peek() + 1)) * arr[top];
				ans = Math.max(ans, tmp);
			}

			s.push(i);
		}

		System.out.println(ans);

	}
}
