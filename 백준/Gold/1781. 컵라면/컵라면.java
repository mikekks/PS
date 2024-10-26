import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

class Node {
	int time;
	int award;

	public Node(int time, int award) {
		this.time = time;
		this.award = award;
	}
}

public class Main {
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		List<Node> nodes = new ArrayList<>();
		long ans = 0;

		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			String[] split = input.split(" ");
			nodes.add(new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
		}

		nodes.sort((a, b) -> a.time - b.time);

		int cur = N;
		int nodeIdx = nodes.size() - 1;

		while(cur >= 1){

			while(nodeIdx >= 0 && nodes.get(nodeIdx).time >= cur){
				q.add(nodes.get(nodeIdx).award);
				nodeIdx--;
			}

			if(!q.isEmpty()){
				ans += q.poll();
			}
			cur--;
		}

		System.out.println(ans);
	}

}