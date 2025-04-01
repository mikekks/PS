import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 1~2
        // 6~9
        int len = 2*w+1;
        for(int i=0; i<stations.length; i++){
            int p = stations[i];
            int prev = 0;
            if(i != 0){
                prev = stations[i-1] + w;
            } 
            
            int x = (p-w) - prev - 1;
            if(x > 0) answer += (x/len) + (x%len != 0 ? 1 : 0);
        }
        
        // 맨 뒤 처리
        int x = n - (stations[stations.length-1] + w);
        if(x > 0) answer += (x/len) + (x%len != 0 ? 1 : 0);

        return answer;
    }
}