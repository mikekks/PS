import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        int end = citations.length;
        
        // 0 1 3 5 6
        answer = citations[end-1];
        
        while(answer >= 0){
            int tmp = 0;
            for(int i=0; i<citations.length; i++){
                if(citations[i] >= answer){
                    tmp = i;
                    break;
                }
            }
            
            int size = end - tmp;
            if(size >= answer){
                break;
            }
            answer--;
        }
        

        
        return answer;
    }
}