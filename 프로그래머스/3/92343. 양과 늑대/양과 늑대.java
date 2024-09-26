import java.util.*;

class Solution {    
    int[][] gEdges;
    int[] gInfo;
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        
        gEdges = edges;
        gInfo = info;
        boolean[] visit = new boolean[info.length];
        
        dfs(0, visit, 0, 0);
        
        
        return answer;
    }
    
    public void dfs(int cur, boolean[] isvisit, int sheep, int wolf){
        isvisit[cur] = true;
        
        if(gInfo[cur] == 0){
            sheep++;
            answer = sheep > answer ? sheep : answer;
        }
        else{
            wolf++;
        }
        
        if(wolf >= sheep) return;
        
        for(int i=0; i<gEdges.length; i++){            
            if(isvisit[gEdges[i][0]] && !isvisit[gEdges[i][1]]){
                boolean[] nextVisited = new boolean[isvisit.length];
                for (int j = 0; j < isvisit.length; j++) {
                    nextVisited[j] = isvisit[j];
                }
                dfs(gEdges[i][1], nextVisited, sheep, wolf);
            }
        }
    }
}