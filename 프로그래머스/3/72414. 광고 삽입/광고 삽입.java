class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = toSeconds(play_time);
        int advSec = toSeconds(adv_time);
        
        long[] total = new long[playSec + 2]; // 누적합용 배열
        
        for(String log : logs) {
            String[] times = log.split("-");
            int start = toSeconds(times[0]);
            int end = toSeconds(times[1]);
            total[start] += 1;
            total[end] -= 1;
        }

        // 시청자 수 누적합 (각 초마다 시청자 수)
        for (int i = 1; i <= playSec; i++) {
            total[i] += total[i - 1];
        }

        // 누적 재생시간
        for (int i = 1; i <= playSec; i++) {
            total[i] += total[i - 1];
        }

        long maxView = total[advSec - 1];
        int startTime = 0;

        for (int i = advSec; i <= playSec; i++) {
            long currentView = total[i] - total[i - advSec];
            if (currentView > maxView) {
                maxView = currentView;
                startTime = i - advSec + 1;
            }
        }

        return toTimeString(startTime);
    }

    private int toSeconds(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    private String toTimeString(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
