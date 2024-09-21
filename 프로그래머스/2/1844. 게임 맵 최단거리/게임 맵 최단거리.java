import java.util.*;

class Pos{
    int y;
    int x;
    int cnt;
    
    public Pos(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    
    int[] ud = {-1,1,0,0};
    int[] lr = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        int answer = -1;
        
        int n = maps[0].length;
        int m = maps.length;
        
        boolean[][] isVisit = new boolean[m+5][n+5];
        
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(1,1,1));
        isVisit[1][1] = true;
        
        while(!q.isEmpty()){
            Pos pos = q.poll();
            
            if(pos.y == m && pos.x == n){
                return pos.cnt;
                //break;
            }
            
            for(int i=0; i<4; i++){
                int ny = pos.y + ud[i];
                int nx = pos.x + lr[i];
                
                if(ny > m || nx > n || ny < 1 || nx < 1) continue;
                
                if(isVisit[ny][nx]) continue;
                if(maps[ny-1][nx-1] == 0) continue;
                
                isVisit[ny][nx] = true;
                q.add(new Pos(ny, nx, pos.cnt+1));
            }
        }
        
        
        return answer;
    }
}