import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Node {
	int n;
	int left;
	int right;

	public Node(int n, int left, int right) {
		this.n = n;
		this.left = left;
		this.right = right;
	}
}

public class Main {
	static int N, M;
	static int idx = 1;
	static int[] arr = new int[10_005];
	static ArrayList<Integer>[] hArr;
	static Node[] nodes = new Node[10_005];

	static void dfs(int cur, int h) {

		if (nodes[cur].left != -1) {
			dfs(nodes[cur].left, h+1);
		}

		hArr[h].add(idx);
		arr[idx++] = cur;

		if (nodes[cur].right != -1) {
			dfs(nodes[cur].right, h+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		N = Integer.parseInt(input);
		hArr = new ArrayList[10_005];
		int[] p = new int[10_005];

		for(int i=0; i<=10000; i++) hArr[i] = new ArrayList();

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			String[] split = input.split(" ");

			int cur = Integer.parseInt(split[0]);
			int left = Integer.parseInt(split[1]);
			int right = Integer.parseInt(split[2]);

			if(left != -1) p[left] = cur;
			if(right != -1) p[right] = cur;

			nodes[cur] = new Node(cur, left, right);
		}

		int root = 0;
		for(int i=1; i<=N; i++){
			if(p[i] == 0) root = i;
		}

		dfs(root, 1);

		int ans = 0;
		int ans_h = 0;

		for(int i=1; i<=10000; i++){
			if(hArr[i].size() == 0) continue;

			hArr[i].sort((a,b) -> a-b);
			int tmp = hArr[i].get(hArr[i].size() - 1) - hArr[i].get(0) + 1;
			if(tmp > ans){
				ans = tmp;
				ans_h = i;
			}
		}

		System.out.println(ans_h + " " + ans);

	}

}