import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return Integer.compare(a, b);
            }
        });
        
        Arrays.sort(routes, (a,b) ->{
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            
            return a[1] - b[1];
        });
        
    
        int cur = - 30005;
        for(int[] r : routes){
            if(r[0] > cur){
                answer++;
                cur = r[1];
            }  
        }
        
        return answer;
    }
}