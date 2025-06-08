import java.util.*;

class Solution {
    public long answer = 0;
    public int[][] edges;
    public long[] a;
    List<Integer>[] map;
    
    public long solution(int[] _a, int[][] _edges) {
        
        edges = _edges;
        a = new long[_a.length];
        for(int i=0; i<a.length; i++){
            a[i] = _a[i];
        }
        map = new List[a.length+5];
        
        for(int i=0; i<a.length+5; i++){
            map[i] = new ArrayList<>();
        }
        
        for(int[] e : edges){
            map[e[0]].add(e[1]);
            map[e[1]].add(e[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] ingree = new int[a.length+5];
        boolean[] visit = new boolean[a.length+5];
        
        for(int i=0; i<a.length; i++){
            if(map[i].size() == 1){
                q.add(i);
                visit[i] = true;
                ingree[i]++;
            }
        }
                
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : map[cur]){
                if(!visit[next]){  // 부모라고 단정할 수 있나?
                    a[next] += a[cur];
                    answer += Math.abs(a[cur]);
                    a[cur] = 0;
                    ingree[next]++;
                    
                    if(ingree[next] == map[next].size() - 1){
                        q.add(next);
                        visit[next] = true;
                    }
                }
            }
        }
        
        long tmp = 0;
        long addtion = 0;
        for(int i=0; i<a.length; i++){
            tmp += a[i];
            addtion = Math.max(addtion, a[i]);
        }
        
        if(tmp != 0){
            return -1;
        }
        
        return answer + addtion;
    }
}