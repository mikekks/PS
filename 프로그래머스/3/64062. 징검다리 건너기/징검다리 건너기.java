class Solution {
    
    public boolean check(int mid, int k, int[] stones){
        int cnt = 0;
        for(int i=0; i<stones.length; i++){
            if(stones[i] < mid){
                cnt++;
                if(cnt == k) return false;
            }
            else{
                cnt = 0;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        
        int low = 0;
        int high = Integer.MAX_VALUE;
        int answer = 0;
        
        while(low <= high){
            int mid = (low+high)/2;
            boolean suc = check(mid, k, stones);
            
            if(suc){
                answer = Math.max(mid, answer);
                low = mid+1;
            }
            else{
                high = mid-1;
            }
            
        }
        
        return answer;
    }
}