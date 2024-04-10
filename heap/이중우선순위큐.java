import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
       int[] answer = new int[2];
        
        PriorityQueue<Integer> min_pq = new PriorityQueue<>();
        PriorityQueue<Integer> max_pq = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String judge = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if (min_pq.size() < 1 && judge.equals("D"))
                continue;

            if (judge.equals("I")) {
                min_pq.offer(value);
                max_pq.offer(value);
                continue;
            }

            
            if(value < 0) {
                int min = min_pq.poll();
                max_pq.remove(min);
            }
            else {
                int max = max_pq.poll();
                min_pq.remove(max);
            }
          
        }
        
        
        if(min_pq.size() > 0 ) {
            answer[0] = max_pq.poll();
            answer[1] = min_pq.poll();
        }
        return answer;
    }
}
