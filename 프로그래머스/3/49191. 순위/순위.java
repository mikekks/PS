import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] map = new int[n+1][n+1];
        
        for(int i=0; i<results.length; i++){
            map[results[i][0]][results[i][1]] = 1;
            map[results[i][1]][results[i][0]] = -1;
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(Math.abs(map[i][k] + map[k][j]) == 2){
                        map[i][j] = map[i][k];
                        map[j][i] = -map[i][k];
                    }
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            boolean check = true;
            map[i][i] = 1;
            for(int j=1; j<=n; j++){
                if(map[i][j] == 0){
                    check = false;
                }
            }
            
            if(check) answer++;
        }
        
        return answer;
    }
}