import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        
        for(int i=0; i<length; i++){
            int cnt = Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            answer += cnt;
        }
        
        int move = length - 1;
        
        for(int i=0; i< length; i++){
            int next = i+1;
            
            while(next < length && name.charAt(next) == 'A'){
                next++;
            }
            // BBBBAAAAAAAB
            move = Math.min(move, 2*i + length - next);
            move = Math.min(move, (length - next)*2 + i);
        }
        
        
        return answer + move;
    }
}