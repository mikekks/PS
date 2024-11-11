import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

public class Main {
	static int N, M;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		N = Integer.parseInt(input);

		int[] arr = new int[N];
		Map<Integer, Integer> map = new HashMap<>();

		for(int i=0; i<N; i++){
			input = br.readLine();
			arr[i] = Integer.parseInt(input);
		}

		int[] sortedArr = Arrays.copyOfRange(arr, 0, arr.length);
		Arrays.sort(sortedArr);

		for(int i=0; i<N; i++){
			map.put(sortedArr[i], i);
		}

		int max = 0;
		for(int i=0; i<N; i++){
			max = Math.max(max, i - map.get(arr[i]));
		}

		System.out.println(max+1);
	}

}