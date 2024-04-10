class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");
        
        int cur = 1;
        int mod = 2*w+1;
        
        for(int i=0; i<stations.length; i++){
            int left = stations[i] - w;
            int right = stations[i] + w;
            
            if(cur >= left) {
                cur = right + 1;
                continue;
            }
            
            int t_left = left - cur;
            answer += (t_left / mod);
            
            if((t_left%mod) != 0){
                answer++;
            }
            
            cur = right + 1;
        }
        
        if(stations[stations.length-1] +w+1 <= n){
            int tmp = stations[stations.length-1] +w+1;
            int size = n+1 - tmp;
            answer += (size / mod);
            
              if(size%mod != 0){
                answer++;
            }
        }
        
        
        return answer;
    }
}
