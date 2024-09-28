class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int INF = 987654321;
        
        // 이진 탐색?
        // 합승..
        // 플로이드-워셜
        
        // 디폴트는 각각의 최솟값 더한값
        
        int[][] map = new int[n+1][n+1];
        int[][] d = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                map[i][j] = INF;
                if(i == j) map[i][j] = 0;
            }
        }
        
        for(int i=0; i < fares.length; i++){
            map[fares[i][0]][fares[i][1]] = fares[i][2];
            map[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                d[i][j] = map[i][j];
            }
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(d[i][k] == INF || d[k][j] == INF) continue;
                    
                    if(d[i][k] + d[k][j] < d[i][j]){
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        
        answer = d[s][a] + d[s][b];
        
        for(int k=1; k<=n; k++){
            if(d[s][k] == INF || d[k][a] == INF || d[k][b] == INF) continue;
            
            if(d[s][k] + d[k][a] + d[k][b] < answer){
                answer = d[s][k] + d[k][a] + d[k][b];
            }
        }
        
        return answer;
    }
}