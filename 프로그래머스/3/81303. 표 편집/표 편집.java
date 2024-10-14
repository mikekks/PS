import java.util.*;

class Node{
        int pre, cur, nxt;
        
        public Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
}

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for(int i=0; i<n; i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;
        
        int cur = k;
        int last = n-1;
        Stack<Node> st = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        
        for(String c : cmd){
            
            if(c.charAt(0) == 'U'){
                int d = Integer.parseInt(c.substring(2));
                while(d > 0){
                    cur = prev[cur];
                    d--;
                }
            }
            else if(c.charAt(0) == 'D'){
                int d = Integer.parseInt(c.substring(2));
                while(d > 0){
                    cur = next[cur];
                    d--;
                }
            }
            else if(c.charAt(0) == 'C'){
                st.add(new Node(prev[cur], cur, next[cur]));
                if(prev[cur] != -1) next[prev[cur]] = next[cur];
                if(next[cur] != -1) prev[next[cur]] = prev[cur];
                sb.setCharAt(cur, 'X');
                
                if(next[cur] != -1){
                    cur = next[cur];
                }
                else{
                    cur = prev[cur];
                }
            }
            else if(c.charAt(0) == 'Z'){
                Node node = st.pop();
                
                if(node.pre != -1) next[node.pre] = node.cur;
                if(node.nxt != -1) prev[node.nxt] = node.cur;
                sb.setCharAt(node.cur, 'O');
            }
        }
        
        return sb.toString();
    }
}