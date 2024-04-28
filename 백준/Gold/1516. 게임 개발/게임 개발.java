

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] ars) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        int[] inDegree = new int[N + 1];
        int[] buildTime = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            buildTime[i] = time;

            while (true) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) {
                    break;
                }

                arr.get(num).add(i);

                inDegree[i]++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] result = new int[N + 1];

        for(int i=1; i <= N; i++){
            if(inDegree[i] == 0){
                pq.add(i);
                result[i] = buildTime[i];
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();

            for(Integer next : arr.get(cur)){
                result[next] = Math.max(result[next], result[cur] + buildTime[next]);
                inDegree[next]--;
                if(inDegree[next] == 0){
                    pq.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i <= N; i++){
            sb.append(result[i] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}