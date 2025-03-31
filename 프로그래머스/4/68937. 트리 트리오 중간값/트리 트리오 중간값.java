import java.util.*;

class Solution {
    
    List<Integer>[] map;
    
    public int[] dijk(int start, int n){
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : map[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
        return dist;
        
    }
    
    public int solution(int n, int[][] edges) {
        int answer = 0;
        
        // 1. root 찾기
        // 2. root 기준 제일 먼 지점 한 지점(A) 찾기
        // 3. 그 먼 지점 기준으로 제일 먼 지점(B) 하나 찾기
        // 4. 두 거리 for 문 돌면서 가장 큰 합 리턴
        // ~12m
        
        map = new List[n+1];
        for(int i=1; i<=n; i++) map[i] = new ArrayList<>();
        
        for(int[] e : edges){
            map[e[0]].add(e[1]);
            map[e[1]].add(e[0]);
        }
        
        int root = 1;
        
       
        
        int[] init_dist = dijk(root, n);
        
        int maxNum1 = 0;
        int maxValue1 = 0;
        for(int i=1; i<=n; i++){
            if(init_dist[i] > maxValue1){
                maxNum1 = i;
                maxValue1 = init_dist[i];
            }
        }
        int[] dist1 = dijk(maxNum1, n);
        
        
        int maxNum2 = 0;
        int maxValue2 = 0;
        for(int i=1; i<=n; i++){
            if(dist1[i] > maxValue2){
                maxNum2 = i;
                maxValue2 = dist1[i];
            }
        }
        int[] dist2 = dijk(maxNum2, n);
        
        for(int i=1; i<=n; i++){
            if(i == maxNum1 || i == maxNum2) continue;
            
            if(Math.max(dist1[i], dist2[i]) > answer){
                answer = Math.max(dist1[i], dist2[i]);
            }
        }
        
        return answer;
    }
}