import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L, K;
    static int[][] stars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        L = Integer.parseInt(split[2]);
        K = Integer.parseInt(split[3]);

        stars = new int[K][2];

        for (int i = 0; i < K; i++) {
            split = br.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            stars[i][0] = x;
            stars[i][1] = y;
        }

        int maxCount = 0;

        // 별똥별 중 두 개의 좌표를 이용해 트램펄린 위치 결정
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int startX = stars[i][0];
                int startY = stars[j][1];

                int count = 0;

                for (int[] star : stars) {
                    int x = star[0];
                    int y = star[1];

                    if (startX <= x && x <= startX + L && startY <= y && y <= startY + L) {
                        count++;
                    }
                }

                maxCount = Math.max(maxCount, count);
            }
        }

        System.out.println(K - maxCount);
    }
}
