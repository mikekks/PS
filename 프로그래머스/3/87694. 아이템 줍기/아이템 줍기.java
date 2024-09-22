import java.util.*;

class Solution {
    
    int[] ud = {-1,1,0,0};
    int[] lr = {0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = -1;
        int max = 105;
        
        int[][] map = new int[max][max];
        boolean[][] visit = new boolean[max][max];
        
        for(int i=0; i<rectangle.length; i++){
            int sy = rectangle[i][1] * 2;
            int sx = rectangle[i][0] * 2;
            int ey = rectangle[i][3] * 2;
            int ex = rectangle[i][2] * 2;
            
            for(int j=sy; j<=ey; j++){
				for(int k=sx; k<=ex; k++){
					if(map[j][k] == 1) continue;
					map[j][k] = 1;
					if(j == sy || j == ey || k == sx || k == ex){
						map[j][k] = 2;
					}
				}
			}
        }
        
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{2*characterY,2*characterX ,0});
        visit[2*characterY][2*characterX] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int cnt = cur[2];
            
            if(cy == (itemY*2) && cx == (itemX*2)){
                return cnt/2;
            }
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + ud[i];
                int nx = cur[1] + lr[i];
                
                if(ny < 1 || nx < 1 || ny > 100 || nx > 100) continue;
                if(visit[ny][nx] || map[ny][nx] != 2) continue;
                
                q.add(new int[]{ny, nx, cnt+1});
                visit[ny][nx] = true;
            }
        }
        
        return answer;
    }
}