import java.util.*;

class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		List<Integer> answer = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();
		int[] day = new int[progresses.length];

		for(int i=0; i<progresses.length; i++){
			int task = 100 - progresses[i];
			int need = (int) Math.ceil((double)task/speeds[i]);
			q.add(need);
		}

		while(!q.isEmpty()){
			int cur = q.poll();
			int cnt = 1;

			while(!q.isEmpty() && cur >= q.peek()){
				q.poll();
				cnt++;
			}
			answer.add(cnt);
		}

		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
}