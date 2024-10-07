import java.util.*;

class Node {
	int cur;
	List<Node> child;
	int idx;

	public Node(int cur, List<Node> child, int idx){
		this.cur = cur;
		this.child = child;
		this.idx = idx;
	}

	public void addIndex(){
		if(idx+1 == child.size()){
			idx = 0;
		}
		else{
			idx++;
		}
	}
}

class Solution {
	public int[] solution(int[][] edges, int[] target) {
		int[] answer = new int[50000];

		// 모든 부모 노드는 자신의 자식 노드 중 가장 번호가 작은 노드를 가리키는 간선을 초기 길로 설정
		// 만약 현재 길로 연결된 노드의 번호가 가장 크면, 번호가 가장 작은 노드를 가리키는 간선을 길로 설정합니다.

		// 모든 경우 중 가장 적은 숫자를 사용 + 사전 순으로 가장 빠른 경우

		// [0, 0, 0, 3, 0, 0, 5, 1, 2, 3]
		int size = target.length;
		Node[] nodes = new Node[size+1];
		List<Integer>[] cnt = new ArrayList[size+1];

		for (int i = 1; i <= size; i++) {
			nodes[i] = new Node(i, new ArrayList<>(), 0);
			cnt[i] = new ArrayList<>();
		}

		for(int[] e : edges){
			int p = e[0];
			int c = e[1];
			nodes[p].child.add(nodes[c]);
		}

		for (int i = 1; i <= size; i++) {
			Collections.sort(nodes[i].child, (a, b) -> {
				return a.cur - b.cur;
			});
		}
		int order = 0;

		while(true){
			Node curNode = nodes[1];

			while(curNode.child.size() != 0){
				Node next = curNode.child.get(curNode.idx);
				curNode.addIndex();
				curNode = next;
			}
			int curIdx = curNode.cur;
			cnt[curIdx].add(order);

			if(cnt[curIdx].size() > target[curIdx-1]){
				return new int[]{-1};
			}

			boolean check = true;
			for(int i=1; i<=size; i++){
				if(!(cnt[i].size() <= target[i-1] && target[i-1] <= cnt[i].size() * 3)){
					check = false;
				}
			}

			if(check){
				break;
			}

			order++;
		}

		for(int i=1; i<=size; i++){
			int last = cnt[i].size();
			int total = 0;

			for (int j = last - 1; j >= 0; j--) {
				Integer curIdx = cnt[i].get(j);
				answer[curIdx]++;
				total++;
			}

			for (int j = last - 1; j >= 0; j--) {
				Integer curIdx = cnt[i].get(j);

				if (total + 2 <= target[i - 1]) {
					answer[curIdx] += 2;
					total += 2;
				}
				else if(total + 1 == target[i - 1]){
					answer[curIdx] += 1;
					total += 1;
				}
			}
		}

		int ans = 0;
		for(int i=0; i<answer.length; i++){
			if(answer[i] == 0){
				ans = i;
				break;
			}
		}

		int[] ret = new int[ans];

		for(int i=0; i<ret.length; i++){
			ret[i] = answer[i];
		}

		return ret;
	}
}