public class Solution {
    public long solution(int[][] land, int P, int Q) {
        long INF = Long.MAX_VALUE;
        long answer = Long.MAX_VALUE;
        
        // 블록 한 개를 새로 추가하거나 삭제하기 위해서는 게임머니를 사용
        // 추가 P / 제거 Q
        // 300 * 300
        
        // 1. 이분탐색
        long start = 0;
        long end = 1000000000;
        
        while(start <= end){
                
            long mid = (start + end)/ 2;
                
            long leftCost = calculateCost(mid, land, P, Q);
            long rightCost = calculateCost(mid+1, land, P, Q);
                
            if(leftCost > rightCost){
                answer = rightCost;
                start = mid+1;
            }
            else{
                answer = leftCost;
                end = mid-1;
            }
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