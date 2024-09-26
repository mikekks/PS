import java.util.*;

class Solution {
    int[] p;
    int root = 0;
    int group = 0;
    
    public int solution(int k, int[] num, int[][] links) {
        int answer = 0;
        p = new int[num.length];
        
        int s = 0;
        int e = (int) 1e9;
        
        for(int i=0; i<num.length; i++){
            s = Math.max(s, num[i]);
        }
        
        for(int i=0; i<num.length; i++){
            p[i] = -1;
        }
        
        for(int i=0; i<links.length; i++){
            if(links[i][0] != -1){
                p[links[i][0]] = i;
            }
            
            if(links[i][1] != -1){
                p[links[i][1]] = i;
            }
        }
        
        for(int i=0; i<p.length; i++){
            if(p[i] == -1){
                root = i;
                break;
            }
        }
        
        while(s <= e){
            int mid = (s+e)/2;
            group = 0;
            check(num, links, root, mid);
            
            if(group+1 > k){
                s = mid+1;
            }
            else{
                e = mid-1;
            }
        }
        
        return e+1;
    }
    
    public int check(int[] num, int[][] links, int cur, int max){
        
        int l = 0;
        int r = 0;
        
        if(links[cur][0] != -1){
            l = check(num, links, links[cur][0], max);
        }
        
        if(links[cur][1] != -1){
            r = check(num, links, links[cur][1], max);
        }
        
        if(num[cur] + l + r <= max) return num[cur] + l + r;
        
        if(num[cur] + Math.min(l,r) <= max){
            group++;
            return num[cur] + Math.min(l,r);
        }

        group += 2;
        return num[cur];
        
    }
}