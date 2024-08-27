import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        
        list.add(arr[0]);
        
        for(int i=1; i<arr.length; i++){
            if(arr[i] == arr[i-1]) continue;
            list.add(arr[i]);
        }
        
        int[] answer = new int[list.size()];
        
        int idx = 0;
        for(Integer i : list){
            answer[idx] = list.get(idx);
            idx++;
        }
        
        return answer;
    }
}