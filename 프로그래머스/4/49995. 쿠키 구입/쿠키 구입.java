import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        // l~m, m+1~r -> 순서가 의미있다.
        // 두 아들이 받을 과자 수는 같아야 합니다.
        // 누적합, 이진탐색
        int min = 1;
        int max = cookie[0];
        int[] sumArr = new int[cookie.length];
        sumArr[0] = cookie[0];

        for(int i=0; i<cookie.length-1; i++){
            int aIdx = i;
            int bIdx = i+1;
            int sumA = cookie[aIdx];
            int sumB = cookie[bIdx];
            
            while(aIdx>=0 && bIdx < cookie.length){
                if(sumA == sumB){
                    answer = Math.max(answer, sumA);
                    
                    if(aIdx-1 < 0 || bIdx+1 >= cookie.length) break;
                    
                    sumA += cookie[--aIdx];
                    sumB += cookie[++bIdx];
                }
                else if(sumA > sumB){
                    if(bIdx+1 >= cookie.length) break;
                    sumB += cookie[++bIdx];
                }
                else{
                    if(aIdx-1 < 0) break;
                    sumA += cookie[--aIdx];
                }
            }
        }
        
       
        
        return answer;
    }
}
