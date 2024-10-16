import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        // 투포인터 2m
        // i=맨왼쪽, 맨오른쪽 고려
        // 짝수 고려
        if(s.length() == 1) return 1;
        
        for(int i=1; i<s.length()-1; i++){
            int mid = i;
            int left = i-1;
            int right = i+1;
            
            int cnt = 0;
            while(left >= 0 && right < s.length()){
                if(s.charAt(left) != s.charAt(right)){
                    break;
                }
                
                left--;
                right++;
                cnt++;
            }
            
            answer = Math.max(answer, cnt*2+1);
        }
        
        for(int i=0; i<s.length()-1; i++){
            int left = i;
            int right = i+1;
            
            int cnt = 0;
            while(left >= 0 && right < s.length()){
                if(s.charAt(left) != s.charAt(right)){
                    break;
                }
                
                left--;
                right++;
                cnt++;
            }
            
            answer = Math.max(answer, cnt*2);
        }

        return answer;
    }
}