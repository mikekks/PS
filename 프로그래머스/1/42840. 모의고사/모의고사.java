import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
       
        
        // 1, 2, 3, 4, 5
        // 2, 1, 2, 3, 2, 4, 2, 5
        // 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        
        int[] p1_answer = {1, 2, 3, 4, 5};
        int[] p2_answer = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3_answer = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        for(int i=0; i<answers.length; i++){
            if(answers[i] ==p1_answer[i%5]) p1++;
            if(answers[i] ==p2_answer[i%8]) p2++;
            if(answers[i] ==p3_answer[i%10]) p3++;
        }
        
        int max = p1 > p2 ? p1 : p2;
        max = max > p3 ? max : p3;
        
        int[] arr = {p1,p2,p3};
        List<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<3; i++){
            if(max == arr[i]){
                answer.add(i+1);
            }
        }
        
        int[] ret = answer.stream().mapToInt(Integer::intValue).toArray();
        
        return ret;
    }
}