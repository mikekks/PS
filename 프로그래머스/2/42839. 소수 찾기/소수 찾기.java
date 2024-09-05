import java.util.*;

class Solution {
    
    HashSet<Integer> set = new HashSet<>();
    
    public boolean isPos(int n){
        if(n == 0 || n == 1) return false;
        
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        
        return true;
    }
    
    public void dfs(String numbers, String cur){
        int n = numbers.length();
        
        for(int i=0; i<n; i++){
            set.add(Integer.parseInt(cur + numbers.charAt(i)));
            dfs(numbers.substring(0,i) + numbers.substring(i+1, n), cur + numbers.charAt(i));
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        dfs(numbers, "");
        
        for(Integer i : set){
            if(isPos(i)) answer++;
        }
        
        return answer;
    }
}