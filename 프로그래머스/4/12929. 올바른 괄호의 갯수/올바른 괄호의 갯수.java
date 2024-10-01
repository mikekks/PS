import java.util.*;

class Solution {
    int N = 0;
    int answer = 0;
    int length = 0;
    boolean[] visit = new boolean[100];
    
    void dfs(int cur, int idx, int total_l, int total_r){
        if(idx == length){
            answer++;
            return;
        }
        
        if(total_l > total_r){
            dfs(2, idx+1, total_l, total_r+1);
        }
            
        if(total_l < N){
            dfs(1, idx+1, total_l+1, total_r);
        }
    }
    
    public int solution(int n) {
        
        N = n;
        length = n*2;
        visit[0] = true;
        dfs(1, 0, 0, 0);
        
        return answer;
    }
}