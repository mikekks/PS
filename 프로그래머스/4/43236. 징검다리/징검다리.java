import java.util.*;

public class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        int l = 0;
        int r = distance+1;
        
        while(l < r){
            int mid = (l+r)/2;
            int cnt = 0;
            int[] tmp = new int[rocks.length+2];
            tmp[0] = 0;
            tmp[rocks.length+1] = distance;
            
            for(int i=0; i<rocks.length; i++){
                tmp[i+1] = rocks[i];
            }
            
            for(int i=1; i<tmp.length; i++){
                if(tmp[i] - tmp[i-1] < mid){
                    cnt++;
                    tmp[i] = tmp[i-1];
                }
            }
            
            
            if(cnt <= n){
                l = mid+1;
                answer = mid > answer ? mid : answer;
            }
            else{
                r = mid;
            }
        }
        
        return answer;
    }
}