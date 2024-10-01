import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        // 가능한 가장 마지막 시간을 들고 있는다?
        int posTime = 0;
        Arrays.sort(timetable);
        
        int[] times = Arrays.stream(timetable)
            .mapToInt(time -> Integer.parseInt(time.replace(":", "")))
            .toArray();
        
        int qIdx = 0;
        int curTime = 900;
        
        for(int i=0; i<n; i++){
            int cnt = 0;
            
            for(int j=qIdx; j<times.length; j++){  
                if(cnt == m) break;
                
                if(times[j] <= curTime){
                    cnt++;
                    qIdx++;
                }
            }
            
            // 꽉 차는 경우 -> 마지막 친구의 -1분
            if(cnt == m){
                posTime = times[qIdx-1] - 1;
            }
            
            // 빈 자리가 있는 경우 -> 버스 도착 시간
            if(cnt < m){
                posTime = curTime;
            }
            
            // 분 단위 시간 컨트롤
            if(posTime%100 >= 60){
                posTime -= 40;
            }
            
            curTime += t;
            if(curTime%100 >= 60){
                curTime -= 60;
                curTime += 100;
            }
        }
        
        String ans = String.valueOf(posTime);
        
        if(posTime < 10){
            ans = "000" + ans;
        }
        else if(posTime < 100){
            ans = "00" + ans;
        }
        else if(posTime < 1000){
            ans = "0" + ans;
        }
        
        String answer = ans.substring(0,2) + ":" + ans.substring(2,4);
        
        return answer;
    }
}