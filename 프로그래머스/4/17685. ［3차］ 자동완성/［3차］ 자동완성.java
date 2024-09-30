import java.util.*;

class Solution {
    
    public int solution(String[] words) {
        int answer = 0;
        
        // 10분 : 이분탐색
        Arrays.sort(words);
        
        for(int i=0; i<words.length; i++){
            int cnt = 0;
            if(i > 0){
                cnt = getDiff(words[i], words[i-1]);
            }
            
            if(i != words.length-1){
                cnt = Math.max(cnt, getDiff(words[i], words[i+1]));
            }
            
            if(cnt == words[i].length()){
                answer += cnt;
            }
            else{
                answer += cnt+1;
            }
        }
        
        return answer;
        
    }
    
    int getDiff(String a, String b){
        int idx = 0;
        
        for(int i=0; i < Math.min(a.length(), b.length()); i++){
            if(a.charAt(i) == b.charAt(i)){
                idx++;
            }
            else{
                break;
            }
        }
        
        return idx;
    }
    
   
}