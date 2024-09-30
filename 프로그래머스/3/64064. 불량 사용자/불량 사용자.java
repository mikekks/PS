import java.util.*;

class Solution {
    
    boolean[] isVisit = new boolean[10];
    int answer = 0;
    Set<Set<Integer>> list = new HashSet<>();
    
    boolean check(String a, String b){
        if(a.length() != b.length()) return false;
        
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) == '*' || b.charAt(i) == '*') continue;
            
            if(a.charAt(i) != b.charAt(i)) return false;
        }
        
        return true;
    }
    
    public void dfs(int idx, String[] user_id, String[] banned_id, Set<Integer> set){
        
        if(idx == banned_id.length){
            list.add(set);
            return;
        }
        
        for(int i=0; i<user_id.length; i++){
            if(isVisit[i] == false && check(user_id[i], banned_id[idx])){
                Set<Integer> newSet = new HashSet<>(set);
                newSet.add(i);
                isVisit[i] = true;
                dfs(idx+1, user_id, banned_id, newSet);
                isVisit[i] = false;
            }
        }
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        // 1. N^2 -> 겹치는 케이스가 많을 것.
        // 2. dfs -> 
        
        Set<Integer> set = new HashSet<>();
        dfs(0, user_id, banned_id, set);
        
        return list.size();
    }
}