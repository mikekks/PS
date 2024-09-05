class Solution {
    
    int answer = 0;
    boolean[] isVisit = new boolean[10];
    int n = 0;
    
    public void dfs(int depth, int cur, int cnt, int[][] dungeons){
        
        if(depth >= n){
            answer = cnt > answer ? cnt : answer;
            return;
        }
        
        answer = cnt > answer ? cnt : answer;
        
        for(int i=0; i<n; i++){
            if(!isVisit[i] && cur >= dungeons[i][0]){
                isVisit[i] = true;
                dfs(depth+1, cur - dungeons[i][1], cnt+1, dungeons);
                isVisit[i] = false;
            }
        }
        
    }
    
    public int solution(int k, int[][] dungeons) {
        
        n = dungeons.length;
        
        dfs(0, k, 0, dungeons);
        
        return answer;
    }
}