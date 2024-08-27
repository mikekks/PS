import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        answer = nums.length/2;
        
        // set 사용
        Set<Integer> set = new HashSet<Integer>();
        
        for(int i : nums){
            set.add(i);
        }
        
        if(set.size() < answer){
            answer = set.size();
        }
        
        return answer;
    }
}