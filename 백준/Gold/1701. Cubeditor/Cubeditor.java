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

	public static void makeTable(String str){
		int sLen = str.length();

		for(int k=0; k<sLen; k++){
			String sub = str.substring(k);
			int[] pi = new int[sub.length()];

			int index = 0;
			for(int i=1; i<sub.length(); i++){

				while(index > 0 && sub.charAt(index) != sub.charAt(i)){
					index = pi[index-1];
				}

				if(sub.charAt(index) == sub.charAt(i)){
					index++;
					pi[i] = index;
					answer = Math.max(answer, index);
				}
			}

		}
	}
}