import java.util.*;

public class Solution {

	List<Integer>[] map = new ArrayList[20005];
	int[] total = new int[50005];

	public int solution(int n, int[][] edge) {
		int answer = 0;
		boolean[] visit = new boolean[n+1];

		for(int i=1; i<=n; i++){
			map[i] = new ArrayList<>();
		}

		for(int i=0; i<edge.length; i++){
			map[edge[i][0]].add(edge[i][1]);
			map[edge[i][1]].add(edge[i][0]);
		}

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{1, 0});
		visit[1] = true;
		total[0]++;

		while(!q.isEmpty()){
			int[] cur = q.poll();

			for(int i=0; i<map[cur[0]].size(); i++){
				Integer next = map[cur[0]].get(i);
				if(!visit[next]){
					visit[next] = true;
					total[cur[1]+1]++;
					q.add(new int[]{next, cur[1]+1});
				}
			}
		}


		for(int i=n; i>=0; i--){
			if(total[i] != 0){
				return total[i];
			}
		}


		return answer;
	}
}