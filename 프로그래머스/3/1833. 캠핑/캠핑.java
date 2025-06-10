import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        // 다른 쐐기 X, 경계에 있는 건 허용
        // 쐐기: 5000
        // 0 ~ 2^31-1
        // x, y 좌표 모두 최소 1 차이나야함.
        
        // n^2으로 2개의 조합은 일단 선택이 가능함
        // 하지만 선택된 영역에 다른 쐐기가 있는지 없는지 여부 파악이 어려움.
        
        Arrays.sort(data, (a, b) -> a[0] - b[0]);
        
        for(int i=0; i<n; i++){
            int cx = data[i][0];
            int cy = data[i][1];
            
            int up = Integer.MAX_VALUE;
            int down = Integer.MIN_VALUE;
            int tmp_up = Integer.MAX_VALUE;
            int tmp_down = Integer.MIN_VALUE;
            
            for(int j=i+1; j<n; j++){
                int tx = data[j][0];
                int ty = data[j][1];
                
                if(tx == cx) continue;
                
                if(ty > cy){
                    if(ty <= up){
                        answer++;
                        tmp_up = Math.min(tmp_up, ty);
                    }
                    
                    
                }
                else if(ty < cy){
                    if(ty >= down){
                        answer++;
                        tmp_down = Math.max(tmp_down, ty);
                    }
                }
                
                if(j+1 < n && tx != data[j+1][0]){
                    up = tmp_up;
                    down = tmp_down;
                }
                
            }
        }
        
        
        return answer;
    }
}