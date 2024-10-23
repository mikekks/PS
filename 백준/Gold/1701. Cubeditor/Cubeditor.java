import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int answer = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		makeTable(s);

		System.out.println(answer);
	}

	public static void makeTable(String pattern) {
		int pLen = pattern.length();

		for(int k=0; k<pLen; k++){
			String sub = pattern.substring(k);
			int[] table = new int[sub.length()];

			int index = 0;
			for(int i = 1; i < sub.length(); i++) {
			/* index > 0 => 문자열 매칭이 시작됨
			  	연속으로 일치하지 않으면 index 값을 돌려주어 다시 매칭 시작 */
				while(index > 0 && sub.charAt(i) != sub.charAt(index)) {
					index = table[index - 1];
				}

				if(sub.charAt(i) == sub.charAt(index)) {
					index += 1;
					table[i] = index;
				}
			}

			for(int ret : table){
				answer = Math.max(ret, answer);
			}

		}
	}
}