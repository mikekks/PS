import java.util.*;

class Node{
    int y1;
    int x1;
    int y2;
    int x2;
    int time;
    
    public Node(int y1, int x1, int y2, int x2, int time){
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.time = time;
    }
}

class Solution {
    
    int[] ud = {1,0,-1,0};
    int[] lr = {0,1,0,-1};
        
    public String getPoint(int y1, int x1, int y2, int x2){
		int[][] p = {{y1, x1}, {y2, x2}};
		Arrays.sort(p, (a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
		
		return p[0][0] + "," + p[0][1] + "," + p[1][0] + "," + p[1][1];
	}
    
    public int solution(int[][] board) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        int n = board.length;
        int m = board[0].length;
        int INF = Integer.MAX_VALUE;
        answer = INF;
        
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node a, Node b){
                return a.time - b.time;
            }
        });
        q.add(new Node(0,1,0,0,0));
        map.put(getPoint(0,1,0,0), 0);
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.y1 == n-1 && cur.x1 == m-1){
                return cur.time;
            }
            
            if(cur.y2 == n-1 && cur.x2 == m-1){
                return cur.time;
            }
            
            if(cur.time > map.get(getPoint(cur.y1, cur.x1, cur.y2, cur.x2))) continue;
            
            
            for(int i=0; i<4; i++){
                int n_y1 = cur.y1 + ud[i];
                int n_x1 = cur.x1 + lr[i];
                int n_y2 = cur.y2 + ud[i];
                int n_x2 = cur.x2 + lr[i];
                
                if(n_y1 >= n || n_x1 >= m || n_y1 < 0 || n_x1 < 0) continue;
                if(n_y2 >= n || n_x2 >= m || n_y2 < 0 || n_x2 < 0) continue;
                if(board[n_y1][n_x1] == 1 || board[n_y2][n_x2] == 1) continue;
                
                if(cur.time+1 >= map.getOrDefault(getPoint(n_y1, n_x1, n_y2, n_x2), INF)) continue;
                
                q.add(new Node(n_y1, n_x1, n_y2, n_x2, cur.time+1));
                map.put(getPoint(n_y1, n_x1, n_y2, n_x2), cur.time+1);
                
                if(cur.y1 == cur.y2 && ud[i] != 0){
                    if(cur.time+1 < map.getOrDefault(getPoint(cur.y1, cur.x1, n_y1, n_x1), INF)){
                        q.add(new Node(cur.y1, cur.x1, n_y1, n_x1, cur.time+1));
                        map.put(getPoint(cur.y1, cur.x1, n_y1, n_x1), cur.time+1);
                    }
                    
                    if(cur.time+1 < map.getOrDefault(getPoint(cur.y2, cur.x2, n_y2, n_x2), INF)){
                        q.add(new Node(cur.y2, cur.x2, n_y2, n_x2, cur.time+1));
                        map.put(getPoint(cur.y2, cur.x2, n_y2, n_x2), cur.time+1);
                    }
                }
                if(cur.x1 == cur.x2 && lr[i] != 0){
                    if(cur.time+1 < map.getOrDefault(getPoint(cur.y1, cur.x1, n_y1, n_x1), INF)){
                        q.add(new Node(cur.y1, cur.x1, n_y1, n_x1, cur.time+1));
                        map.put(getPoint(cur.y1, cur.x1, n_y1, n_x1), cur.time+1);
                    }
                    
                    if(cur.time+1 < map.getOrDefault(getPoint(cur.y2, cur.x2, n_y2, n_x2), INF)){
                        q.add(new Node(cur.y2, cur.x2, n_y2, n_x2, cur.time+1));
                        map.put(getPoint(cur.y2, cur.x2, n_y2, n_x2), cur.time+1);
                    }
                }
            }
        }
        
        return answer;
    }
}