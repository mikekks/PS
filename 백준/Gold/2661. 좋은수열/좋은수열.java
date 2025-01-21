import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
	static int N;
	static String answer;
	static void dfs(String cur){
		if(cur.length() == N){
			answer = cur;
			return;
		}

		for(int i=1; i<=3; i++){
			String next = cur + i;

			boolean check = true;

			for(int j=1; j<=next.length()/2 && check; j++){
				for(int k=0; k<=next.length() - j*2; k++){
					String s1 = next.substring(k, k + j);
					String s2 = next.substring(k+j, k + j + j);
					if(s1.equals(s2)){
						check = false;
						break;
					}
				}
			}

			if(!check) continue;

			dfs(next);

			if(answer != null){
				return;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);

		dfs("");

		System.out.println(answer);
	}
}