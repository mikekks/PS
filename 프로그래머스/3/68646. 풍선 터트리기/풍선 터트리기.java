import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[] l_max = new int[a.length];
        int[] r_max = new int[a.length];
        
        // 어떤 풍선은 무슨 수를 쓰더라도 마지막까지 남기는 것이 불가능할 수도 있습니다.
        // 1개만 남을 때까지 터트렸을 때 최후까지 남기는 것이 가능한 풍선들의 개수
        
        // 가장 작은 풍선은 무조건 살아남음
        // 가장 끝에 있는 풍선은 무조건 살아남음
        
        l_max[0] = a[0];
        for(int i=1; i<a.length; i++){
            l_max[i] = Integer.MAX_VALUE;
            l_max[i] = Math.min(l_max[i-1], a[i]);
        }
        
        r_max[a.length-1] = a[a.length-1];
        for(int i=a.length-2; i>0; i--){
            r_max[i] = Integer.MAX_VALUE;
            r_max[i] = Math.min(r_max[i+1], a[i]);
        }
        
        answer++;
        if(a.length >= 2) answer++;
        
        for(int i=1; i<a.length-1; i++){
            int cur = a[i];
            int cnt = 0;
            if(cur > l_max[i-1]){
                cnt++;
            }
            if(cur > r_max[i+1]){
                cnt++;
            }
            
            if(cnt <= 1) answer++;
        }
        
        return answer;
    }
}