import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // 연속
        // 이진 탐색 + 윈도우 - 3m
        Set<String> set = new HashSet<>();
        Map<String, Integer> minMap = new HashMap<>();
        Map<String, Integer> maxMap = new HashMap<>();
        
        for(int i=0; i<gems.length; i++){
            set.add(gems[i]);
        }
        
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        int start = 0;
        int tmpStart = 0;
        int w = gems.length;
        
        for(int i=0; i<gems.length; i++){
            q.add(gems[i]);
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1); 
            
            while(true){
                String cur = q.peek();
                if(map.get(cur) > 1){
                    q.poll();
                    map.put(cur, map.get(cur)-1); 
                    tmpStart++;
                }
                else{
                    break;
                }
            }
            
            if(set.size() == map.size()){
                if(q.size() < w){
                    start = tmpStart;
                    w = q.size();
                }
            }
        }
        
        answer[0] = start+1;
        answer[1] = start+w;
        return answer;
    }
}