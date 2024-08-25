import java.util.*;


class Solution {
	ArrayList<Integer>[] map;
    boolean[] visited;
    int answer;

	public int solution(int n, int[][] lighthouse) {
		answer = 0;
        
	    map = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        
        
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            int a = edge[0];
            int b = edge[1];
            map[a].add(b);
            map[b].add(a);
        }
        
        visited[1] = true;
        dfs(1);
        
        
		return answer;
	}
    
    public boolean dfs(int cur){
        
        boolean turn = false;
        
        for(int node: map[cur]){
            if(!visited[node]){
                visited[node] = true;
                boolean ret = dfs(node);
                if(!ret){
                    turn = true;
                }
            }
        }
        
        if(turn){
            answer++;
        }
        
        return turn;
        
    }
}