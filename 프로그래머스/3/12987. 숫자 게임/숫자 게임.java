import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        // A팀은 빠르게 출전순서를 정했고 자신들의 출전 순서를 B팀에게 공개
        // A의 순서가 의미가 없다.
        // 투포인터 - 12m
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0;
        int bIdx = 0;
        
        for(int i=0; i<A.length; i++){
            if(A[aIdx] >= B[bIdx]){
                bIdx++;
            }
            else{
                aIdx++;
                bIdx++;
                answer++;
            }
        }
        
        return answer;
    }
}