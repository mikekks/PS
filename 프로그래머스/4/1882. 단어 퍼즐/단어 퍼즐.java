import java.util.*;

class Solution {
    
    public int solution(String[] strs, String t) {
        int answer = 0;

        // 단어 조각 개수의 최솟값
        
        // strs = 100개, 5자이하
        // target = 20,000
        
        // 알파벳은 26자
        // 1. 가장 긴 것을 그리디하게 -> 최적 X
        // 2. 브루트포스 -> 시간초과 예상
        // 3. dfs + dp
        int[] dp = new int[20005];
        
        for(int i=1; i<=20000; i++){
            dp[i] = Integer.MAX_VALUE / 2;
        }
        
        String first = t.substring(0, 1);
        for (String str : strs) {
            if (first.equals(str)) {
                dp[1] = 1;
            }
        }
        
        for(int i=1; i<t.length(); i++){
            String cur = t.substring(0, i+1);
            
            for(String str : strs){
                if(cur.endsWith(str)){
                    dp[i+1] = Math.min(dp[i+1], dp[i+1-str.length()]+1);
                }
            }
            
        }
        
        if(dp[t.length()] > 20000){
            return -1;
        }

        return dp[t.length()];
    }
}