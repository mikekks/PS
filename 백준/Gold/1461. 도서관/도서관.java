import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 책 위치 입력받기
        st = new StringTokenizer(br.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos > 0) positive.add(pos);
            else negative.add(-pos); // 음수는 절대값으로 저장
        }

        // 양수와 음수를 내림차순 정렬
        positive.sort(Collections.reverseOrder());
        negative.sort(Collections.reverseOrder());

        int totalDistance = 0;

        // 양수 처리
        for (int i = 0; i < positive.size(); i += M) {
            totalDistance += positive.get(i) * 2;
        }

        // 음수 처리
        for (int i = 0; i < negative.size(); i += M) {
            totalDistance += negative.get(i) * 2;
        }

        // 가장 먼 위치는 한 번만 방문
        int maxDistance = 0;
        if (!positive.isEmpty()) maxDistance = Math.max(maxDistance, positive.get(0));
        if (!negative.isEmpty()) maxDistance = Math.max(maxDistance, negative.get(0));
        totalDistance -= maxDistance;

        System.out.println(totalDistance);
    }
}
