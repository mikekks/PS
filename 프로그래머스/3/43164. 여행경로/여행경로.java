import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        Map<String, List<String>> map = new HashMap<>();
        
        for(int i=0; i<tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                List<String> tmp = new ArrayList<>();
                tmp.add(tickets[i][1]);
                map.put(tickets[i][0], tmp);
            }
            else{
                List<String> tmp = map.get(tickets[i][0]);
                tmp.add(tickets[i][1]);
                map.put(tickets[i][0], tmp);
            }
        }
        
        return answer;
    }
}