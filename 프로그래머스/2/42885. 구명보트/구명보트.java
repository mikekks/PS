import java.util.*;

public class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        // 최대한 꽉꽉 채워서 태운다.
        // 200 * 50000 == 1천만
        Integer[] list = Arrays.stream(people).boxed().toArray(Integer[]::new);

        Arrays.sort(list, (o1, o2) -> o2-o1);
        
        int[] w = new int[245];
        for(int i : list){
            w[i]++;
        }
        
        for(int i : list){
            if(w[i] == 0) continue;
            
            w[i]--;
            
            for(int j=limit-i; j>= 1; j--){
                if(w[j] >= 1){
                    w[j]--;
                    break;
                }
            }
            answer++;
        }
        
        return answer;
    }
}