import java.util.*;

class node{
    int num;
    int cnt;
    
    public node(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}


class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int size = sources.length;
        
        // 주어진 sources의 원소 순서대로 강철부대로 복귀할 수 있는 최단시간
        // 복귀가 불가능한 경우 해당 부대원의 최단시간은 -1
        // 냅다 bfs 시간초과 안날듯?
        List<Integer>[] map = new ArrayList[100005];
        
        for(int i=0; i<100005; i++){
            map[i] = new ArrayList<>();
        }
        
        for(int i=0; i<roads.length; i++){
            map[roads[i][0]].add(roads[i][1]);
            map[roads[i][1]].add(roads[i][0]);
        }
        
        
        int[] cost = new int[n+1];
        int INF = 987654321;
        
        for(int i=0; i<n+1; i++){
            cost[i] = INF;
        }
        
        int init = destination;
            
        PriorityQueue<node> q = new PriorityQueue<>(new Comparator<node>(){
            @Override
            public int compare(node a, node b){
                return a.cnt - b.cnt;
            }
        });
            
        cost[init] = 0;
        q.add(new node(init, 0));
                        
        while(!q.isEmpty()){
                node cur = q.poll();
                
                if(cur.cnt > cost[cur.num]) continue;
                
                for(int k=0; k<map[cur.num].size(); k++){
                    int next = map[cur.num].get(k);
                    
                    if(cur.cnt+1 < cost[next]){
                        q.add(new node(next, cur.cnt+1));
                        cost[next] = cur.cnt+1;
                    }
                }
        }
            
        
        for(int i=0; i<size; i++){
            if(cost[sources[i]] == INF) answer[i] = -1;
            else answer[i] = cost[sources[i]];
        }
        
        return answer;
    }
}