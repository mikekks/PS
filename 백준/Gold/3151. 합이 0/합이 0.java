import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {

	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		s = br.readLine();
		String[] split = s.split(" ");

		nums = new int[N];

		long ans = 0;
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(split[i]);
		}

		Arrays.sort(nums);

		for (int i = 0; i < N - 2; i++) {

			for (int j = i + 1; j < N - 1; j++) {
				int target = -(nums[i] + nums[j]);
				int lower_bound = lower_bound(j + 1, N, target);
				int upper_bound = upper_bound(j + 1, N, target);

				ans += (upper_bound - lower_bound);
			}
		}

		System.out.println(ans);

	}

	static int lower_bound(int start, int end, int target){

		while(start < end){
			int mid = (start + end) / 2;

			if(nums[mid] < target){
				start = mid + 1;
			}
			else{
				end = mid;
			}
		}

		return start;
	}

	static int upper_bound(int start, int end, int target){

		while(start < end){
			int mid = (start + end) / 2;

			if(nums[mid] <= target){
				start = mid + 1;
			}
			else{
				end = mid;
			}
		}

		return start;
	}

}

