import java.util.*;

class Process{
    int p;
    int l;
    
    public Process(int p, int l){
        this.p = p;
        this.l = l;
    }
}

class Solution {    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Process> q = new LinkedList<>();
        
        int idx = 0;
        for(int i : priorities){
            q.add(new Process(i, idx++));
        }
        
        int order = 0;
        
        while(!q.isEmpty()){
            Process p = q.poll();
            boolean check = true;
            
            for(Process tmp : q){
                if(p.p < tmp.p){
                    check = false;
                    break;
                }
            }
            
            if(!check){
                q.add(p);
            }
            else{
                order++;
                if(p.l == location){
                    return order;   
                }
            }
        }
        
        return answer;
    }
}