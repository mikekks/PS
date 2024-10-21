import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		List<Integer> arrA = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			arrA.add(sc.nextInt());
		}

		int m = sc.nextInt();
		List<Integer> arrB = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			arrB.add(sc.nextInt());
		}

		// 두 수열의 공통되는 숫자 집합 구하기
		Set<Integer> commonElements = new HashSet<>(arrA);
		commonElements.retainAll(new HashSet<>(arrB));

		// 공통되는 숫자가 없으면 0 출력 후 종료
		if (commonElements.isEmpty()) {
			System.out.println(0);
			return;
		}

		List<Integer> result = new ArrayList<>();

		while (!commonElements.isEmpty()) {
			int maxVal = Collections.max(commonElements);
			result.add(maxVal);

			int idx1 = arrA.indexOf(maxVal);
			int idx2 = arrB.indexOf(maxVal);
			arrA = arrA.subList(idx1 + 1, arrA.size());
			arrB = arrB.subList(idx2 + 1, arrB.size());

			commonElements = new HashSet<>(arrA);
			commonElements.retainAll(new HashSet<>(arrB));
		}

		System.out.println(result.size());
		for (int num : result) {
			System.out.print(num + " ");
		}
	}
}
