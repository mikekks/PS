import java.util.*;

class Solution {
    static final int INF = 987654321;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;
        
        int INF = 987654321;
        
        boolean[][] roads = new boolean[n + 1][n + 1];
        
        for(int i=0; i<edge_list.length; i++) {
            roads[edge_list[i][0]][edge_list[i][1]] = true;
            roads[edge_list[i][1]][edge_list[i][0]] = true;
        }
        
        
        int[][] dp = new int[k][n+1];
        
        for(int i=0; i<k; i++) {
            for(int j=0; j<n+1; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][gps_log[0]] = 0;
        
        for(int i=1; i<k; i++){
            for(int j=1; j<=n; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                
                for(int node=1; node<=n; node++){
                    if(roads[j][node]){
                        dp[i][j] = Math.min(dp[i-1][node], dp[i][j]);
                    }
                }
                
                if(j != gps_log[i]) dp[i][j]++;
            }
        }
        
        answer = dp[k-1][gps_log[k-1]];
        if(answer == INF) return -1;
        
        return answer;
    }
}