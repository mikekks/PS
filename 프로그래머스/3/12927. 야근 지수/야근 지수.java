import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<works.length; i++){
            q.add(works[i]);
        }
        
        while(n > 0 && !q.isEmpty()){
            Integer cur = q.poll();
            if(cur == 0) continue;
            
            q.add(cur-1);
            n--;
        }
        
        while(!q.isEmpty()){
            Integer cur = q.poll();
            answer += (cur * cur);
        }
        
        
        return answer;
    }
}