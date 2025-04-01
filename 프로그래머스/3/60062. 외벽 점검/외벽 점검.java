import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        
        int[] arr = new int[n*2+5];
        int len = weak.length;
        
        for(int i=0; i<len*2; i++){
            if(i < len){
                arr[i] = weak[i];
            }
            else{
                arr[i] = weak[i-len] + n;
            }
        }
        
        List<int[]> permutations = new ArrayList<>();
        permute(dist, new int[dist.length], permutations, new boolean[dist.length], 0, dist.length, dist.length);
        
        for(int start=0; start<len; start++){
            for(int[] order: permutations){
                int cnt = 1;
                int pos = arr[start] + order[0];
                
                for(int i=start+1; i<start+len; i++){
                    if(arr[i] > pos){
                        cnt++;
                        if(cnt > dist.length) break;
                        
                        pos = arr[i] + order[cnt-1];
                    }
                }
                
                if(cnt <= dist.length){
                    answer = Math.min(answer, cnt);
                }
            }
        }
        
        
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    public void permute(int[] arr, int[] output, List<int[]> result, boolean[] visited, int depth, int n, int r){
        if (depth == r) {
            result.add(output.clone());
            return;
        }
 
        for (int i=0; i<n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                permute(arr, output, result, visited, depth + 1, n, r);       
                visited[i] = false;;
            }
        }
    }
        

    
}
