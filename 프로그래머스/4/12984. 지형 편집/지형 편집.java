public class Solution {
    public long solution(int[][] land, int P, int Q) {
        long INF = Long.MAX_VALUE;
        long answer = Long.MAX_VALUE;
        
        // 블록 한 개를 새로 추가하거나 삭제하기 위해서는 게임머니를 사용
        // 추가 P / 제거 Q
        // 300 * 300
        
        // 1. 이분탐색
        long start = 0;
        long end = 1_000_000_000;
        
        while(start <= end){
                
            long m1 = start + (end - start) / 3;
            long m2 = end - (end - start) / 3;
                
            long cost1 = calculateCost(m1, land, P, Q);
            long cost2 = calculateCost(m2, land, P, Q);
                
            if(cost1 > cost2){
                start = m1+1;
            }
            else{
                end = m2-1;
            }
            
            answer = Math.min(answer, Math.min(cost1, cost2));
        }
        
        return answer;
    }
    
    public long calculateCost(long height, int[][] land, int P, int Q){
        long cost = 0;
        
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[i].length; j++){
                if(land[i][j] > height){
                    cost += (land[i][j] - height) * Q; 
                }
                else{
                    cost += (height - land[i][j]) * P;
                }
            }
        }
        
        return cost;
    }
}