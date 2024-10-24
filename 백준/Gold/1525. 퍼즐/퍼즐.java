import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

	public static int[][] map = new int[3][3];

	public static int[] ud = {-1, 1, 0, 0};
	public static int[] lr = {0, 0, -1, 1};
	static Map<String, Integer> visit = new HashMap<>();

	public static String getStr(){
		StringBuilder ret = new StringBuilder();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				ret.append(map[i][j]);
			}
		}
		return ret.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0; i<3; i++){
			String input = br.readLine();
			String[] split = input.split(" ");
			map[i][0] = Integer.parseInt(split[0]);
			map[i][1] = Integer.parseInt(split[1]);
			map[i][2] = Integer.parseInt(split[2]);
		}

		int answer = getAnswer();

		System.out.println(answer);
	}

	private static int getAnswer() {
		Queue<String> q = new LinkedList<>();
		q.add(getStr());
		visit.put(getStr(), 0);

		while (!q.isEmpty()){
			String cur = q.poll();
			int eIdx = cur.indexOf('0');
			int move = visit.get(cur);
			int ey = eIdx / 3;
			int ex = eIdx % 3;

			if(cur.equals("123456780")){
				return visit.get(cur);
			}

			for(int i=0; i<4; i++){
				int ny = ey + ud[i];
				int nx = ex + lr[i];

				if(ny >= 3 || nx >= 3 || ny < 0 || nx < 0 ) continue;

				int nPos = 3 * ny + nx;
				char tmp = cur.charAt(nPos);
				String next = cur.replace(tmp, 'x');
				next = next.replace('0', tmp);
				next = next.replace('x', '0');

				if(!visit.containsKey(next)){
					visit.put(next, move + 1);
					q.add(next);
				}
			}
		}

		return -1;
	}
}