import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int aIdx = 0;
        
        for(int i=0; i<commands.length; i++){
            int[] arr = new int[commands[i][1] - commands[i][0]+1];
            int idx = 0;
            for(int k=commands[i][0]-1; k<commands[i][1]; k++){
                arr[idx++] = array[k];
            }
            
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2]-1];
        }
        
        return answer;
    }
}