import java.util.*;

public class Solution {
	public String solution(String number, int k) {
		StringBuilder answer = new StringBuilder();

		Stack<Character> st = new Stack<>();
        
		for(int i=0; i<number.length(); i++){
		    while(!st.isEmpty() && k > 0 && number.charAt(i) > st.peek()){
					st.pop();
                    k--;
			}
            
			st.add(number.charAt(i));
		}
        
        while(k > 0){
            k--;
            st.pop();
        }
        
        for (char c : st) {
            answer.append(c);
        }

        return answer.toString();
	}
}