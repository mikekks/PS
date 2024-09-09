class Solution {
    
    int N = 0;
    
    public int dfs(int[] numbers, int idx, int cur){
        
        if(idx == numbers.length){
            if(cur == N) return 1;
            
            return 0;
        }
        
        int a = dfs(numbers, idx+1, cur+numbers[idx]);
        int b = dfs(numbers, idx+1, cur-numbers[idx]);
        
        return a+b;
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        N = target;
        
        answer = dfs(numbers, 0, 0);
        
        return answer;
    }
}