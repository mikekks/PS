import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String p : participant){
            map.putIfAbsent(p, 0);
            Integer cnt = map.get(p);
            map.put(p, cnt+1);
        }
        
        for(String p : completion){
            Integer cnt = map.get(p);
            map.put(p, cnt-1);
        }
        
        String s = map.entrySet().stream()
			.filter(m -> m.getValue() != 0)
			.map(m -> m.getKey())
			.findFirst()
			.orElse("");

        
        return s;
    }
}