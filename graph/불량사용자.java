import java.util.*;

class Solution {
    int ans = 0;
    boolean[] isVisit = new boolean[10];
    boolean[] isAssign = new boolean[10];
    HashMap<String, Boolean> map = new HashMap<>();
    
    int b_max = 0;
    int u_max = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        b_max = banned_id.length;
        u_max = user_id.length;
        
        // 조합 -> dfs
        // dfs에서 중복조합 피하는 방법 -> 그 다음부터 본다.
        dfs(-1, 0, user_id, banned_id, "");
        
        answer = ans;
        return answer;
    }
    
    public void dfs(int cur, int length, String[] user_id, String[] banned_id, String ans_id){
        if(length == b_max){
            
            if(!map.containsKey(ans_id)){
                System.out.println(ans_id);
                ans++;
                map.put(ans_id, true);
            }
            
            return;
        }
        
        
        for(int i=cur+1; i<user_id.length; i++){

            if(isVisit[i] == false){
                for(int j=0; j<banned_id.length; j++){
                    
                    if(isAssign[j] == false && check(user_id[i], banned_id[j])){
                        isAssign[j] = true;
                        isVisit[i] = true;
                        dfs(i, length+1, user_id, banned_id, ans_id+i);
                        
                        isAssign[j] = false;
                        isVisit[i] = false;
                    }
                }
            }
        }
    }
    
    public boolean check(String user, String banned){
        if(user.length() != banned.length()) return false;
        
        for(int i=0; i<banned.length(); i++){
            if(banned.charAt(i) == '*') continue;
            
            if(banned.charAt(i) != user.charAt(i)) return false;
        }
        
        return true;
    }
}
