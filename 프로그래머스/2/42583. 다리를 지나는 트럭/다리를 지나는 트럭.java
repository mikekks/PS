import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> q = new LinkedList<Integer>();
        int curWeight = 0;
        int curTime = 0;
        
        for(int i=0; i<truck_weights.length; i++){
            int curTruck = truck_weights[i];
            
            while(true){
                if(q.isEmpty()){
                    q.add(truck_weights[i]);
                    curWeight += truck_weights[i];
                    curTime++;
                    break;
                }
                else if(q.size() == bridge_length){
                    curWeight -= q.poll();
                }
                else{
                    if(curWeight + truck_weights[i] <= weight){
                        q.add(truck_weights[i]);
                        curWeight += truck_weights[i];
                        curTime++;
                        break;
                    }
                    else{
                        q.add(0);
                        curTime++;
                    }
                }
            }
            
        }
        
        return curTime + bridge_length;
    }
}