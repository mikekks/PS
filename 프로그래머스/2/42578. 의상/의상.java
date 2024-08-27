import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        // 1. 코디가 중복되면 안된다.
        // 2. 종류별로 최대 1가지만 가능
        // 3. 일부가 겹치더라도 완전히 동일하지 않으면 다른 방법으로 취급
        // 4. 하루에 최소 1개이상의 의상 입음
        // 5. 같은 이름 존재 X
        
        Map<String, List<String>> map = new HashMap<>();
        
        for(String[] s : clothes){
            if(map.containsKey(s[1])){
                List<String> list = map.get(s[1]);
                list.add(s[0]);
                map.put(s[1], list);
            }
            else{
                List<String> list = new ArrayList<String>();
                list.add(s[1]);
                map.put(s[1], list);
            }
        }
    
        
        for(String s : map.keySet()){
            List<String> list = map.get(s);
            answer *= (list.size() + 1);
        }
        
        return answer-1;
    }
}