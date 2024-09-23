import java.util.*;

class Solution {
    
    List<String> answers = new ArrayList<>();
    boolean visit[] = new boolean[10005];
    
    public void dfs(String start, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            answers.add(route);
        }
        
        for(int i=0; i<tickets.length; i++){
            if(!visit[i] && tickets[i][0].equals(start)){
                visit[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt+1);
                visit[i] = false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(answers);
        answer = answers.get(0).split(" ");
        
        return answer;
    }
}