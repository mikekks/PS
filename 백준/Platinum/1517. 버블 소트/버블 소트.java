import java.io.*;
import java.util.StringTokenizer;

class Main {
	static long[] arr;
	static long ans;

	public static void mergeSort(int start, int end) {
		if (start == end) {
			return;
		}

		int mid = (start + end) / 2;

		mergeSort(start, mid);
		mergeSort(mid + 1, end);
		merge(start, end, mid);
	}

	public static void merge(int left, int right, int mid){
		int n1 = mid - left + 1;
		int n2 = right - mid;

		long[] leftArray = new long[n1];
		long[] rightArray = new long[n2];

		// 데이터를 임시 배열에 복사
		for (int i = 0; i < n1; i++) {
			leftArray[i] = arr[left + i];
		}
		for (int j = 0; j < n2; j++) {
			rightArray[j] = arr[mid + 1 + j];
		}

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			} else {
				arr[k] = rightArray[j];
				j++;
				ans += (n1 - i);
			}
			k++;
		}

		// 왼쪽 배열에 남은 요소 추가
		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		// 오른쪽 배열에 남은 요소 추가
		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		arr = new long[N];

		String s = br.readLine();
		String[] split = s.split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(split[i]);
		}

		mergeSort(0, N-1);

		System.out.println(ans);
	}
}