import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
	int p;
	int d;

	public Node(int p, int d) {
		this.p = p;
		this.d = d;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int ans = 0;
		int N = Integer.parseInt(input);

		if(N == 0){
			System.out.println(0);
			return;
		}

		List<Node> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			String[] split = input.split(" ");
			list.add(new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
		}

		list.sort((a, b) -> a.d - b.d);

		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		int curDay = list.get(list.size()-1).d;
		int idx = list.size() - 1;
		while(curDay > 0){

			while(idx >=0 && list.get(idx).d >= curDay){
				q.add(list.get(idx).p);
				idx--;
			}

			if(!q.isEmpty()){
				ans += q.poll();
			}
			curDay--;
		}

		System.out.println(ans);
	}

}