

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] map;
    static int[][] copyMap;
    static int ans;


    public static void main(String[] ars) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        copyMap = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }

        ArrayList<Integer> archers = new ArrayList<>();
        ans = 0;

        findComb(1, 0, archers);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findComb(int idx, int cnt, ArrayList<Integer> archers){
        if(cnt == 3){
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = copyMap[i][j];
                }
            }

            simulate(archers);
            return;
        }

        for(int i=idx; i<=M; i++){
            archers.add(i);
            findComb(i+1, cnt+1, archers);
            archers.remove(archers.size() - 1);
        }
    }

    private static void simulate(ArrayList<Integer> archers){
        int cnt = 0;

        for(int i=1; i<=N; i++){
            boolean[][] killed = new boolean[N + 1][M + 1];

            for(int j=0; j<archers.size(); j++){
                int archer = archers.get(j);
                int minD = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int minX = Integer.MAX_VALUE;

                for(int y=1; y<=N; y++){
                    for(int x=1; x<=M; x++){
                        if(map[y][x] == 1) {
                            int diff = Math.abs(y - N - 1) + Math.abs(x - archer);
                            if (diff < minD) {
                                minD = diff;
                                minY = y;
                                minX = x;
                            } else if (diff == minD) {
                                if (x < minX) {
                                    minY = y;
                                    minX = x;
                                }
                            }
                        }
                    }
                }

                if(minD <= D){
                    killed[minY][minX] = true;
                }
            }

            for(int k=1; k<=N; k++){
                for(int j=1; j<=M; j++){
                    if(killed[k][j]){
                        map[k][j] = 0;
                        cnt++;
                    }
                }
            }


            for (int k = 1; k <= M; k++) {
                map[N][k] = 0;
            }

            // i번째 줄을 i-1번째 줄로 초기화.
            for (int k = N; k >= 1; k--) {
                for (int j = 1; j <= M; j++) {
                    map[k][j] = map[k - 1][j];
                }
            }

        }

        ans = Math.max(ans, cnt);
    }
}