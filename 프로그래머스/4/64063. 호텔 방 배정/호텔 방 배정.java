import java.util.*;

class Solution {
    
    public HashMap<Long, Long> map = new HashMap<>();
    
    public long getEmpty(long cur){
        if(!map.containsKey(cur)){
            map.put(cur, cur+1);
            return cur;
        }
        
        long empty = getEmpty(map.get(cur));
        map.put(cur, empty);
        return empty;
    }
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for(int i=0; i<room_number.length; i++){
            long cur = room_number[i];
            answer[i] = getEmpty(cur);
        }
        
        return answer;
    }
}