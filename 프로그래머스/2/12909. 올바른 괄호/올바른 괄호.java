import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<String> st = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                st.push("(");
            }
            else if(s.charAt(i) == ')'){
                if(st.isEmpty()) return false;
                
                String tmp = st.pop();
                if(tmp != "(") {
                    return false;
                }
            }
        }
        
        if(!st.isEmpty()) return false;
        
        return answer;
    }
}