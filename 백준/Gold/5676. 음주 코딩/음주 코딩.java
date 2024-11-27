import java.io.*;

class Main {

	static int[] arr;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true){
			String s = br.readLine();

			if(s == null){
				break;
			}

			String[] split = s.split(" ");
			int N = Integer.parseInt(split[0]);
			int K = Integer.parseInt(split[1]);

			arr = new int[N + 1];
			tree = new int[N * 5];

			s = br.readLine();
			split = s.split(" ");
			for(int i=1; i<=N; i++){
				int parseInt = Integer.parseInt(split[i - 1]);
				arr[i] = (parseInt == 0) ? 0 : (parseInt > 0) ? 1 : -1;
			}


			init(1, 1, N);
			StringBuilder sb = new StringBuilder();

			for(int i=0; i<K; i++){
				s = br.readLine();
				split = s.split(" ");
				int a = Integer.parseInt(split[1]);
				int b = Integer.parseInt(split[2]);

				if(split[0].equals("C")){
					int diff = (b == 0) ? 0 : (b > 0) ? 1 : -1;
					update(1, N, 1, a, diff);
				}
				else {
					int query = query(1, N, a, b, 1);
					if(query > 0)
						sb.append("+");
					else if(query == 0)
						sb.append("0");
					else if(query < 0)
						sb.append("-");
				}
			}

			System.out.println(sb);

		}
	}

	public static int init(int node, int start, int end){
		if(start == end){
			tree[node] = arr[start];
			return arr[start];
		}

		int mid = (start+end) / 2;
		return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end);
	}

	public static int update(int start, int end, int node, int idx, int diff){
		if(idx > end || idx < start) return tree[node];
		if(start == end) return tree[node] = diff;

		int mid = (start+end) / 2;
		return tree[node] = update(start, mid,node * 2, idx, diff) * update(mid+1, end,node * 2+1, idx, diff);
	}

	public static int query(int s, int e, int cs, int ce, int node){
		if(e < cs || s > ce) return 1;
		if(cs <= s && e <= ce){
			return tree[node];
		}

		int mid = (s+e) / 2;
		return query(s, mid, cs, ce, node * 2) * query(mid + 1, e, cs, ce, node * 2 + 1);
	}
}