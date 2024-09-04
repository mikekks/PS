import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> min_pq = new PriorityQueue<>();
        PriorityQueue<Integer> max_pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        
        
        for(int i=0; i<operations.length; i++){
            String oper = operations[i];
            char opType = oper.charAt(0); 
            Integer value = Integer.parseInt(oper.substring(2));
            
            if(opType == 'I'){
                min_pq.add(value);
                max_pq.add(value);
            }
            else if(opType == 'D'){
                if(value == 1){
                    if(!max_pq.isEmpty()){
                        Integer v = max_pq.poll();
                        min_pq.remove(v);
                    } 
                }   
                else if(value == -1){
                    if(!min_pq.isEmpty()){
                        Integer v = min_pq.poll();
                        max_pq.remove(v);
                    } 
                }
            }
        }
        
        if(!max_pq.isEmpty()){
            answer[0] = max_pq.peek();
        }
        
        if(!min_pq.isEmpty()){
            answer[1] = min_pq.peek();
        }
        
        return answer;
    }
}