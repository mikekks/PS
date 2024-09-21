import java.util.*;

class Pos{
    String str;
    int cnt;
    
    public Pos(String str, int cnt){
        this.str = str;
        this.cnt = cnt;
    }
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = Integer.MAX_VALUE;
        boolean[] isVisit = new boolean[words.length];
        
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(begin, 0));
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            if(cur.str.equals(target)){
                answer = cur.cnt;
            }
            
            for(int i=0; i<words.length; i++){
                int cnt = 0;
                for(int k=0; k<words[i].length(); k++){
                    if(cur.str.charAt(k) != words[i].charAt(k)){
                        cnt++;
                    }
                }
                
                if(cnt <= 1 && isVisit[i] == false){  // 하나만 다른 경우
                    q.add(new Pos(words[i], cur.cnt+1));
                    isVisit[i] = true;
                }
            }
        }
        
        if(answer == Integer.MAX_VALUE){
            answer = 0;
        }
        
        return answer;
    }
}