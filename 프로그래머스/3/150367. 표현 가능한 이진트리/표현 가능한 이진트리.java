import java.util.*;

class Solution {
    
    boolean suc = true;
    
    int check(int l, int r, int[] arr){
        if(l == r){ // 리프인 경우
            return arr[l];
        }
        
        int mid = (l+r)/2;
        
        int left = check(l, mid-1, arr);
        int right = check(mid+1, r, arr);
        
        if(left == 1 || right == 1){
            if(arr[mid] == 0){
                suc = false;
            }
        }
        
        
        return arr[mid];
    }
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        // 규칙성
        for(int i=0; i<numbers.length; i++){
            long cur = numbers[i];
            int[] arr = new int[1030];
            
            if(cur == 0) continue;
            
            long k = 100;
            while(k >= 0){
                long odd = (long) Math.pow(2, k);
                if(cur / odd > 0){
                    break;
                }
                else{
                    k--;
                }
            }
            
            int idx = 0;
            int low = 0;
            int high = (int) k;
            
            int power = 1;
            long cnt = k+1;
            while(cnt > (int) Math.pow(2, power) - 1){
                power++;
            }
            
            while(cnt < (int) Math.pow(2, power) - 1){
                cnt++;
                arr[idx++] = 0;
                high++;
            }
            
            
            while(k >= 0){
                long odd = (long) Math.pow(2, k);
                if(cur / odd > 0){
                    arr[idx++] = 1;
                    cur -= odd;
                }
                else{
                    arr[idx++] = 0;
                }
                k--;
            }
            
            suc = true;
            check(low, high, arr);
            
            if(suc) answer[i]++;
        }
        
        return answer;
    }
}