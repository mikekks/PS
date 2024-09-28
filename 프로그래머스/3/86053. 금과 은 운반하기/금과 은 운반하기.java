import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
       
        // 이진 탐색
        long start = 0;
        long end = (long) (10e9 * 2 * 10e5 * 2);
        long length = g.length;
        
        while(start < end){
            long time = (start + end) / 2;
            long total_g = 0;
            long total_s = 0;
            long total = 0;
            
            for(int i=0; i<length; i++){
                long interval = t[i] * 2;
                long cnt = 0;
                
                if(time-t[i] >= 0){
                    cnt = ((time-t[i]) / interval) + 1;
                }
                
                total_g += Math.min(cnt * w[i], g[i]);
                total_s += Math.min(cnt * w[i], s[i]);
                total += Math.min(cnt * w[i], g[i] + s[i]);
            }
            
            if(total >= a+b && total_g >= a && total_s >= b){
                end = time;
            }
            else{
                start = time+1;
            }
        }
        
        return start;
    }
}