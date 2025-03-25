import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        
        int[] ud = {-1,1,0,0};
        int[] lr = {0,0,-1,1};
        
        PriorityQueue<int[]> q = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        int[][][] dp = new int[n][m][2];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                dp[i][j][0] = Integer.MAX_VALUE / 2;
                dp[i][j][1] = Integer.MAX_VALUE / 2;
            }
        }
        
        q.add(new int[]{0,0,0,0});
        dp[0][0][0] = 0;
        q.add(new int[]{0,0,0,1});
        dp[0][0][1] = 0;
        
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[1];
            int x = poll[2];
            int cost = poll[0];
            int arrow = poll[3];
            
            if(y == n-1 && x == m-1){
                return cost;
            }
            
            for(int i=0; i<4; i++){
                int ny = y + ud[i];
                int nx = x + lr[i];
                int nextCost = i/2 == arrow ? 100 : 600;
                int nextArrow = i/2;
                
                if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if(board[ny][nx] == 1) continue;
                if(cost + nextCost >= dp[ny][nx][nextArrow]) continue;
                
                dp[ny][nx][nextArrow] = cost + nextCost;
                q.add(new int[]{dp[ny][nx][nextArrow], ny, nx, nextArrow});
            }
        }
        
        return -1;
    }
}