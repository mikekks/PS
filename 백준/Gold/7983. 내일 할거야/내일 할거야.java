import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Task {
	int d;
	int t;

	public Task(int d, int t) {
		this.d = d;
		this.t = t;
	}
}

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int N = Integer.parseInt(s);
		List<Task> tasks = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			String[] split = s.split(" ");
			int d = Integer.parseInt(split[0]);
			int t = Integer.parseInt(split[1]);

			tasks.add(new Task(d, t));
		}

		tasks.sort((a, b) -> a.t - b.t);

		int last = tasks.get(N - 1).t;

		for (int i = N - 1; i >= 0; i--) {
			Task task = tasks.get(i);

			if (task.t <= last) {
				last = task.t - task.d;
			} else {
				last -= task.d;
			}
		}

		System.out.println(last);

	}
}