class Solution {
    public static int solution(int[] food_times, long k) {
		long total = 0;
        int end = 0;

		for(int t : food_times){
			total += t;
            end = Math.max(end, t);
		}
        
		int start = 0;
        end++;

		while(start+1 < end){
			int mid = (start+end) / 2;
			long sum = 0;

			for (int i = 0; i < food_times.length; i++)
                sum += food_times[i] > mid ? mid : food_times[i];

			if(sum <= k){
                start = mid;
			}
			else {
				end = mid;
			}

		}
        
        long sum = 0;
        for (int i = 0; i < food_times.length; i++)
            sum += food_times[i] > start ? start : food_times[i];
        
        long remain = k - sum;
        for (int i = 0; i < food_times.length; i++) {
            if (food_times[i] <= start) continue;
            
            if (remain-- == 0)
                return i + 1;
        }
        return -1;
        
	}
}