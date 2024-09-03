import java.util.*;

class Job{
    int s;
    int e;
    
    public Job(int s, int e){
        this.s = s;
        this.e = e;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<Job> q = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2){
                return j1.e - j2.e;
            }
        });
        
        int curIdx = 0;
        int count = 0;
        int curTime = 0;
        
        while(count < jobs.length){
            
            while(curIdx < jobs.length && curTime >= jobs[curIdx][0]){
                q.add(new Job(jobs[curIdx][0], jobs[curIdx][1]));
                curIdx++;
            }
            
            if(q.isEmpty()){
                curTime = jobs[curIdx][0];
                continue;
            }
            
            Job job = q.poll();
            answer += (curTime+job.e) - job.s;
            count++;
            
            curTime += job.e;
        }
        
        
        
        return answer / jobs.length;
    }
}