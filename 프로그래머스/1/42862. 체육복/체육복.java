import java.util.*;

class Solution {
    
    boolean[] isBorrow = new boolean[35];
    boolean[] isNotLost = new boolean[35];
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int count = 0;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i] == reserve[j]){
                    isBorrow[reserve[j]] = true;
                    isNotLost[i] = true;
                    count++;
                    break;
                }
            }
        }
        
        for(int i=0; i<lost.length; i++){
            if(isNotLost[i]) continue;
            
            for(int j=0; j<reserve.length; j++){
                if(Math.abs(lost[i] - reserve[j]) == 1 && !isBorrow[reserve[j]]){
                    isBorrow[reserve[j]] = true;
                    count++;
                    break;
                }
            }
        }
        
        return n - lost.length + count;
    }
}