import java.util.*;

class Solution {    
    public int[] solution(String[] gems) {
        int kind = new HashSet<>(Arrays.asList(gems)).size();
        HashMap<String, Integer> map = new HashMap<>();
        int[] answer = new int[2];
        
        int left = 0;
        int ans = 100005;
        int cur_cnt = 0;
        
        for(int right=0; right < gems.length; right++){
            
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            
            while(map.get(gems[left]) > 1){
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
            }
            
            if(right-left < ans && map.size() == kind){
                ans = right-left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
            
        }
        
        
 
        return answer;
    }
    
}
