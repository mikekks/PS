import java.util.*;

class Solution {
   	public String[] solution(String[] s) {
		String[] answer = {};
        answer = new String[s.length];
        
        for(int i=0;i<s.length;i++)
            answer[i] = solve(s[i]);
        
        return answer;
	}
    
    public String solve(String s){
        
        int cnt110 = 0;
        
        Stack<Character> st = new Stack<Character>();
        
        for(int i=0;i<s.length();i++){
            
            st.push(s.charAt(i));
            
            if(st.size() >= 3){
                char first = st.pop();
                if(first != '0'){
                    st.push(first);
                    continue;
                }
                char second = st.pop();
                if(second != '1'){
                    st.push(second);
                    st.push(first);
                    continue;
                }
                char third = st.pop();
                if(third != '1'){
                    st.push(third);
                    st.push(second);
                    st.push(first);
                    continue;
                }
                cnt110++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int idx = st.size();
        boolean flag = false;
        
        while(!st.isEmpty()){
            char c = st.pop();
            
            if(!flag && c == '1') idx--;
            if(c == '0') flag = true;
            
            sb.insert(0, c);
        }
        
        for(int i=0; i<cnt110; i++){
            sb.insert(idx, "110");
        }
    
        return sb.toString();
    }
}