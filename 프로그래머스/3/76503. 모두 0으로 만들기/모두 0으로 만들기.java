import java.util.*;

class Solution {
    public long answer = 0;
    public int[][] edges;
    public long[] a;
    Map<Integer, Boolean>[] map;
    
    public long solution(int[] _a, int[][] _edges) {
        
        edges = _edges;
        a = new long[_a.length];
        for(int i=0; i<a.length; i++){
            a[i] = _a[i];
        }
        map = new HashMap[a.length+5];
        
        for(int i=0; i<a.length+5; i++){
            map[i] = new HashMap<>();
        }
        
        for(int[] e : edges){
            map[e[0]].put(e[1], true);
            map[e[1]].put(e[0], true);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<a.length; i++){
            if(map[i].size() == 1){
                q.add(i);
            }
        }
                
        while(!q.isEmpty()){
            int cur = q.poll();
            
            Set<Integer> set = map[cur].keySet();
            
            if(set.size() == 1){
                Iterator<Integer> it = set.iterator();
                int next = it.next();
                
                a[next] += a[cur];
                answer += Math.abs(a[cur]);
                a[cur] = 0;
                
                map[cur].remove(next);
                map[next].remove(cur);
                q.add(next);
            }
        }

        for(int i=0; i<a.length; i++){
            if(a[i] != 0){
                return -1;
            }
        }
        
        return answer;
    }
}