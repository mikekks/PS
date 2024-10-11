import java.util.*;

class Node{
	int n;
	boolean isSB;

	public Node(int n, boolean isSB){
		this.n = n;
		this.isSB = isSB;
	}
}

class Solution {
	public int[] solution(int target) {
		int[] answer = new int[2];
		int sb = 0;

		// 만약 두 선수가 같은 라운드에 0점을 만들면 두 선수 중 "싱글" 또는 "불"을 더 많이 던진 선수가 승리하며 그마저도 같다면 선공인 선수가 승리

		// 최소한의 다트로 0점을 만드는 게 가장 중요, 그러한 방법이 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이 던지는 방법

		// 최선의 경우 던질 다트 수와 그 때의 "싱글" 또는 "불"을 맞춘 횟수(합)를 순서대로 배열에 담아 return

		// 1 ~ 20
		List<Node> arr = new ArrayList<>();
		arr.add(new Node(50, true));

		for(int n=1; n<=3; n++){
			for(int i=1; i<=20; i++){
				if(n == 1){
					arr.add(new Node(i*n, true));
				}
				else{
					arr.add(new Node(i*n, false));
				}
			}
		}

		Collections.sort(arr, (a, b) -> {
			return a.n - b.n;
		});


		int[][][] dp = new int[65][target+1][2];
		for(int i=0; i<=arr.size(); i++){
			for(int j=1; j<=target; j++){
				dp[i][j][0] = Integer.MAX_VALUE;
				dp[i][j][1] = 0;
			}
		}

		dp[0][0][0] = 0;
		for(int i=0; i<=arr.size(); i++){
			dp[i][0][0] = 0;
		}

		for(int i=0; i<arr.size(); i++){
			Node curNode = arr.get(i);
			int cur = curNode.n;
			boolean isSB = curNode.isSB;
			int sbCnt = isSB ? 1 : 0;

			for(int j=1; j<=target; j++){
				if(j-cur >= 0 && dp[i+1][j-cur][0] + 1 == dp[i][j][0]){
					dp[i+1][j][0] = dp[i+1][j-cur][0] + 1;
					dp[i+1][j][1] = Math.max(dp[i][j][1], dp[i+1][j-cur][1] + sbCnt);
				}
				else if(j-cur >= 0 && dp[i+1][j-cur][0] + 1 < dp[i][j][0]){
					dp[i+1][j][0] = dp[i+1][j-cur][0] + 1;
					dp[i+1][j][1] = dp[i+1][j-cur][1] + sbCnt;
				}
				else{
					dp[i+1][j][0] = dp[i][j][0];
					dp[i+1][j][1] = dp[i][j][1];
				}
			}
		}

		answer[0] = dp[arr.size()][target][0];
		answer[1] = dp[arr.size()][target][1];

		return answer;
	}
}