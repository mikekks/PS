import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int n = scores[0][0];
        int m = scores[0][1];
        
        Arrays.sort(scores, (a1, a2) -> {
            if(a1[0] == a2[0]){
                return a1[1] - a2[1];
            }
            else{
                return a2[0] - a1[0];
            }
        });
        
        int max = scores[0][1];
        
        for(int i=0; i<scores.length; i++){
            if(max > scores[i][1]){
                if(scores[i][1] == m && scores[i][0] == n)
                    return -1;
                
                scores[i][0] = -1;
                scores[i][1] = -1;
            }
            else{
                max = scores[i][1];
            }
        }
        
        int[] arr = new int[scores.length];
        
        for(int i=0; i<scores.length; i++){
            arr[i] = scores[i][0] + scores[i][1];
        }
        
        Arrays.sort(arr);
        
        for(int i = arr.length - 1; i >= 0; i--){
            if(arr[i] <= n + m){
                break;
            }
            answer++;
        }
        
        
        return answer+1;
    }
}