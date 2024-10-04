import java.util.*;

class Solution {
    int[] ud = {-1,1,0,0};
    int[] lr = {0,0,-1,1};
    
    int[][] board;
    int Y = 0;
    int X = 0;
    
    int dfs(int cy, int cx, int oy, int ox){
        if(board[cy][cx] == 0){
            return 0;
        }
        
        int ret = 0;
        
        for(int i=0; i<4; i++){
            int ny = cy+ud[i];
            int nx = cx+lr[i];
            
            if(ny >= Y || nx >= X || ny < 0 || nx < 0) continue;
            if(board[ny][nx] == 0) continue;
            
            board[cy][cx] = 0;
            int tmp = dfs(oy, ox, ny, nx) + 1;
            board[cy][cx] = 1;
            
            if(ret%2 == 0 && tmp%2 == 1){
                ret = tmp;
            }
            else if(ret%2 == 0 && tmp%2 == 0){
                ret = ret > tmp ? ret : tmp;
            }
            else if(ret%2 == 1 && tmp%2 == 1){
                ret = ret < tmp ? ret : tmp;
            }
        }
        
        return ret;
       
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        this.board = board;
        Y = board.length;
        X = board[0].length;
        
        // ** 최적의 플레이 ** 
        // 보드가 작다. 5*5 = 25
        // dfs -> 완전탐색
        
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }
}