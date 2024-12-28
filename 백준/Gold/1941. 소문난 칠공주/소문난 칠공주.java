import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Person {
	int y;
	int x;
	char p;

	public Person(int y, int x, char p) {
		this.y = y;
		this.x = x;
		this.p = p;
	}
}

class Main {

	static Person[] people = new Person[26];
	static int ans = 0;
	static boolean[] visit = new boolean[26];

	static void dfs(int idx, int cnt) {

		if (cnt == 7) {
			List<Integer> list = new ArrayList<>();

			for (int i = 1; i <= 25; i++) {
				if (visit[i]){
					list.add(i);
				}
			}

			int sCnt = 0;
			int connectCnt = 0;
			boolean[] tVisit = new boolean[7];

			Person init = people[list.get(0)];

			Queue<Person> q = new LinkedList<>();
			q.add(init);
			tVisit[0] = true;

			while(!q.isEmpty()){
				Person person = q.poll();

				connectCnt++;
				if (person.p == 'S') {
					sCnt++;
				}

				for (int j=0; j<7; j++) {
					if(tVisit[j]) continue;

					Person next = people[list.get(j)];
					if (Math.abs(person.y - next.y) + Math.abs(person.x - next.x) == 1) {
						q.add(next);
						tVisit[j] = true;
					}
				}
			}

			if (sCnt < 4 || connectCnt != 7)
				return;

			ans++;
			return;
		}

		for (int i = idx + 1; i <= 25; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			dfs(i, cnt + 1);
			visit[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 5; i++) {
			String s = br.readLine();

			for (int j = 1; j <= 5; j++) {
				people[(i - 1) * 5 + j] = new Person(i, j, s.charAt(j - 1));
			}
		}

		dfs(0, 0);

		System.out.println(ans);

	}
}