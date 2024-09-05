import java.util.*;

class Solution {
    
    int[] p = new int[105];
    int[] cnt = new int[105];
    int N = 0;
    
    public int findParent(int n){
        if(p[n] == n) return n;
        
        return p[n] = findParent(p[n]);
    }
    
    public void merge(int a, int b){
        a = findParent(a);
        b = findParent(b);
        
        if(a != b){
            p[b] = a;
            cnt[a] += cnt[b];
            cnt[b] = 0;
        }
    }
    
    public int getDiff(int exclude, int[][] wires){
        for(int i=0; i<wires.length; i++){
            if(i == exclude) continue;
            
            merge(wires[i][0], wires[i][1]);
        }
        
        int[] ret = new int[2];
        int idx = 0;
        for(int i=1; i<=N; i++){
            if(cnt[i] != 0) ret[idx++] = cnt[i];
        }
        
        return Math.abs(ret[0] - ret[1]);
    }
    
    public int solution(int n, int[][] wires) {
        int answer = 987654321;
        N = n;
        
        for(int i=0; i<wires.length; i++){
            for(int k=1; k<=102; k++) p[k] = k;
            
            Arrays.fill(cnt, 1);
            
            int tmp = getDiff(i, wires);
            answer = tmp < answer ? tmp : answer;

        }
        
        return answer;
    }
}