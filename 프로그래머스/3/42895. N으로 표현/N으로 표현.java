import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        
        if(N == number) return 1;
        
        int size = 8;
        Set<Integer>[] list = new Set[size+1];
        
        for(int i=1; i<=size; i++){
            list[i] = new HashSet<Integer>();
        }
        
        list[1].add(N);
        
        for(int i=2; i<=size; i++){
            
            StringBuilder sb = new StringBuilder();
            sb.append(N);
            for(int j=1; j<i; j++){
                sb.append(N);
            }
            list[i].add(Integer.parseInt(sb.toString()));    
            
            for(int j=1; j<i; j++){
                int k = i - j;
                
                for(int a : list[j]){
                    for(int b : list[k]){
                        list[i].add(a+b);
                        list[i].add(a-b);
                        list[i].add(a*b);
                        
                        if(b != 0){
                            list[i].add(a/b);
                        }
                    }
                }
            }
            
            for(Integer num : list[i]){
                if(num == number) return i;
            }
        }
        
        
        return -1;
    }
}