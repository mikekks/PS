import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node {
	int n;
	int h;
	List<Integer> childs;

	public Node(int n, int h, List<Integer> childs) {
		this.n = n;
		this.h = h;
		this.childs = childs;
	}
}

public class Main {
	static Node[] nodes;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		int T = Integer.parseInt(input);

		while (T > 0) {
			T--;
			input = br.readLine();
			int N = Integer.parseInt(input);
			p = new int[N + 1];
			nodes = new Node[N + 1];

			for (int i = 1; i <= N; i++) {
				nodes[i] = new Node(i, -1, new ArrayList<>());
			}

			for (int i = 0; i < N - 1; i++) {
				input = br.readLine();
				String[] split = input.split(" ");
				int pNode = Integer.parseInt(split[0]);
				int cNode = Integer.parseInt(split[1]);
				p[cNode] = pNode;
				nodes[pNode].childs.add(cNode);
			}

			input = br.readLine();
			String[] split = input.split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			int root = 0;
			for (int i = 1; i <= N; i++) {
				if (p[i] == 0) {
					root = i;
					break;
				}
			}

			init(root, 1);
			int ans = lcs(a, b);
			System.out.println(ans);
		}
	}

	public static void init(int cur, int h) {
		nodes[cur].h = h;

		for(int i=0; i<nodes[cur].childs.size(); i++){
			Integer child = nodes[cur].childs.get(i);
			init(child, h + 1);
		}
	}

	public static int lcs(int a, int b){
		int ha = nodes[a].h;
		int hb = nodes[b].h;
		while(ha > hb){
			ha--;
			a = p[a];
		}

		while(hb > ha){
			hb--;
			b = p[b];
		}

		while(a != b){
			a = p[a];
			b = p[b];
		}

		return a;
	}

}