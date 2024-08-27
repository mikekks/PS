import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String s : phone_book){
            map.put(s, 1);
        }
        
        for(String s : phone_book){
            for(int i=0; i<s.length(); i++){
                if(map.containsKey(s.substring(0, i))){
                    return false;
                } 
            }
        }
        
        return true;
    }
}