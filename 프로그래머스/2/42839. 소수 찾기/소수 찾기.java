class Solution {
    
    boolean[] isVisit = new boolean[10];
    boolean[] num = new boolean[10000000];
    
    public boolean isPos(int n){
        if(n == 0 || n == 1) return false;
        
        for(int i=2; i<n; i++){
            if(n % i == 0) return false;
        }
        
        return true;
    }
    
    public void dfs(String numbers, String cur, int d){
        if(d >numbers.length()){
            return;
        }
        
        
        for(int i=0; i<numbers.length(); i++){
            if(!isVisit[i]){
                isVisit[i] = true;
                num[Integer.parseInt(cur + numbers.charAt(i))] = true;
                dfs(numbers, cur + numbers.charAt(i), d+1);
                isVisit[i] = false;
            }
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        for(int i=0; i<numbers.length(); i++){
            if(numbers.charAt(i) != '0'){
                isVisit[i] = true;
                num[numbers.charAt(i) - '0'] = true;
                dfs(numbers, String.valueOf(numbers.charAt(i)), 1);
                isVisit[i] = false;
            }
        }
        
        for(int i=1; i<num.length; i++){
            if(num[i]){
                if(isPos(i)) answer++;
            }
        }
        
        return answer;
    }
}