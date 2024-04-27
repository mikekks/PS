import java.util.*;


class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<works.length; i++){
            pq.add(works[i]);
        }
        
        while(n > 0){
            Integer cur = pq.poll();
            if(cur == null) break;
            
            if(cur == 0) break;
            
            cur--;
            pq.add(cur);
            n--;
        }
        
        while(!pq.isEmpty()){
            Integer cur = pq.poll();
            answer += cur * cur;
        }
        
        return answer;
    }
}