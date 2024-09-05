import java.util.*;

class Solution {
    
    HashSet<String> set = new HashSet<>();
    String[] arr = {"A", "E", "I", "O", "U"};
    
    public void dfs(String cur){
        
        if(cur.length() == 5){
            return;
        }
        
        for(int i=0; i<arr.length; i++){
            set.add(cur + arr[i]);
            dfs(cur + arr[i]);
        }
        
    }
    
    public int solution(String word) {
        
        // 단어 사전 만들기
        dfs("");
        
        // 단어 사전에서 word 랑 동일한 거 찾기
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        
        for(int i=0; i<list.size(); i++){
            if(list.get(i).equals(word)){
                return i+1;
            }
        }
        
        return -1;
    }
}