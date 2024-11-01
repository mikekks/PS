import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		Stack<Character> st = new Stack<>();

		// 맨 마지막 처리 필요
		int cnt = 1;
		int ans = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			if(ch == '('){
				cnt *= 2;
				st.add(ch);
			}
			else if(ch == '['){
				cnt *= 3;
				st.add(ch);
			}
			else{
				if(ch == ')'){
					if(st.empty() || st.peek() != '('){
						System.out.println(0);
						return;
					}

					if(input.charAt(i-1) == '('){
						ans += cnt;
					}

					st.pop();
					cnt /= 2;
				}
				else if(ch == ']'){
					if(st.empty() || st.peek() != '['){
						System.out.println(0);
						return;
					}

					if(input.charAt(i-1) == '['){
						ans += cnt;
					}

					st.pop();
					cnt /= 3;
				}
				else{
					System.out.println(0);
					return;
				}
			}
		}

		if(!st.empty()){
			System.out.println(0);
			return;
		}

		System.out.println(ans);
	}

}