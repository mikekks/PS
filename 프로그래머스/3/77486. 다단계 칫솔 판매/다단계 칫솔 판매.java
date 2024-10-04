import java.util.*;

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Integer> map = new HashMap<>();
                
        // 10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
        // seller 에는 같은 이름이 중복해서 들어있을 수 있습니다.
        
        // 구현 문제인듯
        for(int i=0; i<enroll.length; ++i){
            map.put(enroll[i], i);
        }
        
        // 시간초과가되면 그때 고려하자.
        for(int i=0; i<seller.length; i++){
            int curIdx = map.get(seller[i]);
            int curAmount = amount[i] * 100;
            
            while(true){
                int parentAmount = curAmount / 10;
                int childAmount = curAmount - parentAmount;
                
                if(parentAmount < 1) {
                    answer[curIdx] += curAmount;
                    break;
                }
                
                answer[curIdx] += childAmount;
                curAmount -= childAmount;
                
                String p = referral[curIdx];
                
                if(p.equals("-")) break;
                
                int pIdx = map.get(p);
                curIdx = pIdx;
            }
        }
        
        return answer;
    }
}