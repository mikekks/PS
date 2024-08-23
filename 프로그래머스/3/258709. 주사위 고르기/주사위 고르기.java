import java.util.*;

class Solution {

	int[] visited = new int[15];
	List<Integer> answer_list = new ArrayList<>();
	int[][] gDice;
	List<Integer> sum1 = new ArrayList<>();
	List<Integer> sum2 = new ArrayList<>();
	int total = 0;
	int ans = 0;

	private static int lowerBound(List<Integer> data, int target) {
		int begin = 0;
		int end = data.size();

		while (begin < end) {
			int mid = (begin + end) / 2;

			if (data.get(mid) >= target) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return end;
	}

	private static int upperBound(List<Integer> data, int target) {
		int begin = 0;
		int end = data.size();

		while (begin < end) {
			int mid = (begin + end) / 2;

			if (data.get(mid) > target) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return end;
	}

	public void dfs2(int idx, List<Integer> arr, int sum, int oper) {
		if (idx == arr.size()) {
			if (oper == 1) {
				sum1.add(sum);
			} else {
				sum2.add(sum);
			}
			return;
		}

		for (int i = 0; i < 6; i++) {
			dfs2(idx + 1, arr, sum + gDice[arr.get(idx)][i], oper);
		}
	}

	public void dfs(int idx, int cnt) {
		if (cnt == total / 2) {
			List<Integer> arr1 = new ArrayList<>();
			List<Integer> arr2 = new ArrayList<>();

			for (int i = 0; i < total; i++) {
				if (visited[i] == 0) {
					arr1.add(i);  // 0,3,6,7
				} else {
					arr2.add(i);  // 1,2,4,5
				}
			}

			dfs2(0, arr1, 0, 1);
			dfs2(0, arr2, 0, 2);

			Collections.sort(sum1);
			Collections.sort(sum2);

			int winCount = 0;
			for (int i = 0; i < sum1.size(); i++) {
				int lower = lowerBound(sum2, sum1.get(i));
				winCount += lower;
				//int upper = upperBound(sum2, sum1.get(i));
			}

			if (winCount > ans) {
				ans = winCount;
				answer_list.clear();

				for(int i=0; i<arr1.size(); i++){
					answer_list.add(arr1.get(i)+1);
				}
			}

			sum1.clear();
			sum2.clear();

		}

		for (int i = idx; i < total; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				dfs(i+1, cnt + 1);
				visited[i] = 0;
			}
		}

	}

	public int[] solution(int[][] dice) {

		gDice = dice;
		total = dice.length;
		int[] answer = new int[total/2];

		dfs(0, 0);

		for(int i=0; i<total/2; i++){
			answer[i] = answer_list.get(i);
		}

		return answer;
	}
}