class Solution {
    
    long MOD = 1000000007L;
    long[][] dp = new long[105][105];
    boolean[][] isWater = new boolean[105][105];
    int N = 0;
    int M = 0;
    
    public long dfs(int y, int x){
        if(y == N && x == M){
            return 1;
        }
        
        long a = dp[y+1][x];
        long b = dp[y][x+1];
        
        if(x+1 <= M && dp[y][x+1] == 0 && !isWater[y][x+1]){
            b = dfs(y, x+1);
        }
        
        if(y+1 <= N && dp[y+1][x] == 0 && !isWater[y+1][x]){
            a = dfs(y+1, x);
        } 
        
        dp[y][x] = (a+b) % MOD;
        return dp[y][x];
    }
    
    public int solution(int m, int n, int[][] puddles) {
        long answer = 0;
        N = n;
        M = m;
        
        for(int i=0; i<puddles.length; i++){
            isWater[puddles[i][1]][puddles[i][0]] = true;
        }
        
        answer = dfs(1, 1);
        
        return (int) answer;
    }
}