import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Node {
	int d;
	int w;

	public Node(int d, int w) {
		this.d = d;
		this.w = w;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int N = Integer.parseInt(input);

		List<Node> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String nums = br.readLine();
			String[] split = nums.split(" ");
			int d = Integer.parseInt(split[0]);
			int w = Integer.parseInt(split[1]);

			list.add(new Node(d, w));
		}

		list.sort((a, b) -> {
			return b.w - a.w;
		});

		boolean[] alloc = new boolean[1005];
		int ans = 0;

		for (int i = 0; i < list.size(); i++) {
			Node curNode = list.get(i);

			for (int j = curNode.d; j >= 1; j--) {
				if(!alloc[j]){
					alloc[j] = true;
					ans += curNode.w;
					break;
				}
			}
		}

		System.out.println(ans);
	}
}