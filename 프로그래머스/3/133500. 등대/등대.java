import java.util.*;

class Solution {

    int answer = 0;
    List<Integer>[] map;
    boolean[] visit;
    boolean[] light;
    
	public int solution(int n, int[][] lighthouse) {
		answer = 0;
        map = new ArrayList[n+1];
        visit = new boolean[n+1];
        light = new boolean[n+1];
        
        for(int i=1; i<=n; i++) map[i] = new ArrayList<>();
        
	    for(int i=0; i<n-1; i++){
            map[lighthouse[i][0]].add(lighthouse[i][1]);
            map[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        visit[1] = true;
        dfs(1);
        
		return answer;
	}
    
    public boolean dfs(int cur){
        
        boolean turn = false;
        
        for(Integer next : map[cur]){
            if(visit[next]) continue;
            
            visit[next] = true;
            boolean ret = dfs(next);
            
            if(!ret){
                turn = true;
            }
        }
        
        if(turn){
            answer++;
        }
        
        return turn;
        
    }
}