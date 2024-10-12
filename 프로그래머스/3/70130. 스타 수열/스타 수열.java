import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        // 가장 길이가 긴 스타 수열의 길이
        
        // 1. 부분 수열 뽑기 -> 순서만 유지하면, 삭제를 하더라도 무조건 부분 수열
        // 2. 그 중에서 스타 수열 뽑기
        // 이진 탐색 : 10m
        
        // [0,3,3,0,7,0,2,0]	
        // [5,3,3,5]
        
        int[] cnt = new int[a.length];
        
        for(int i=0; i<a.length; i++){
            cnt[a[i]]++;
        }
        
        for(int i=0; i<cnt.length; i++){
            if(cnt[i] <= answer) continue;
            int ret = 0;
            
            for(int j=0; j<a.length-1; j++){
                if(a[j] != a[j+1] && (a[j] == i || a[j+1] == i)){
                    j++;
                    ret++;
                }
            }
            
            answer = Math.max(ret, answer);
        }
        
        
        
        return answer*2;
    }
}