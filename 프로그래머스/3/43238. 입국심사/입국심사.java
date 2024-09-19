import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        long answer = Long.MAX_VALUE / 100;
        
        long low = 0;
        long high = Long.MAX_VALUE / 100;
        
        while(low < high){
            long mid = (low+high)/2;
            
            long cnt = 0;
            for(int i=0; i<times.length; i++){
                cnt += mid / times[i];
            }
            
            if(cnt >= n){
                high = mid;
                answer = mid < answer ? mid : answer;
            }
            else{
                low = mid+1;
            }
        }
        
        return low;
    }
}