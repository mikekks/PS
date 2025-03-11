import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String st = br.readLine();
		String[] split = st.split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		int[] arr = new int[N];

		for(int i=0; i<N; i++){
			st = br.readLine();
			int num = Integer.parseInt(st);
			arr[i] = num;
		}

		long left = 1L;
		long right = 1000000000000000005L;

		while(left < right){
			long target = (left + right) / 2;
			long total = 0;

			for(int i=0; i<arr.length; i++){
				total += target / arr[i];
				if(total >= M) break;
			}

			if(total < M){
				left = target + 1;
			}
			else{
				right = target;
			}
		}

		System.out.println(left);
	}
}
