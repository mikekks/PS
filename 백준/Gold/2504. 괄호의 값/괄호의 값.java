import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		Stack<String> st = new Stack<>();
		StringBuilder ans = new StringBuilder();

		// 맨 마지막 처리 필요
		for (int i = 0; i < input.length() - 1; i++) {
			String cur = input.substring(i, i + 1);
			String next = input.substring(i+1, i + 2);

			if (isLeft(cur)) {
				if(isLeft(next)){
					ans.append("(");
				}
				st.push(cur);
			}
			else if(cur.equals(")") || cur.equals("]")) {
				if(st.empty()){
					System.out.println(0);
					return;
				}
				String pop = st.pop();

				if(cur.equals(")")){
					if(!pop.equals("(")) {
						System.out.println(0);
						return;
					}
				}

				if(cur.equals("]")){
					if(!pop.equals("[")) {
						System.out.println(0);
						return;
					}
				}

				if(isLeft(next)){
					if(cur.equals(")")){
						ans.append("2+");
					}
					if(cur.equals("]")) {
						ans.append("3+");
					}
				}
				else{
					if(cur.equals(")")){
						ans.append("2)x");
					}
					if(cur.equals("]")){
						ans.append("3)x");
					}
				}
			}
		}

		String last = input.substring(input.length() - 1);

		if(isLeft(last)){
			System.out.println(0);
			return;
		}

		if(last.equals(")")){
			ans.append(2);
		}
		if(last.equals("]")){
			ans.append(3);
		}

		if(st.size() != 1){
			System.out.println(0);
			return;
		}

		if(last.equals(")")){
			if (!st.peek().equals("(")) {
				System.out.println(0);
				return;
			}
		}
		if(last.equals("]")){
			if (!st.peek().equals("[")) {
				System.out.println(0);
				return;
			}
		}
		
		//System.out.println(ans);

		Stack<Integer> numbers = new Stack<>();
		Stack<Character> operators = new Stack<>();

		for(int i=0; i<ans.length(); i++){
			char c = ans.charAt(i);

			if(c == '('){
				operators.add(c);
			}
			else if(c == ')'){
				while (operators.peek() != '(') {
					numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
				}
				operators.pop();
			}
			else if(c == '+' || c == 'x'){
				while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
					numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
				}
				operators.push(c);
			}
			else{
				numbers.add(c - '0');
			}
		}

		while (!operators.isEmpty()) {
			numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
		}

		System.out.println(numbers.pop());
	}

	static boolean isLeft(String str){
		if(str.equals("(") || str.equals("[")){
			return true;
		}

		return false;
	}

	static int applyOperation(char op, int b, int a) {
		switch (op) {
			case '+':
				return a + b;
			case 'x':
				return a * b;
		}
		return 0;
	}

	static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if (op1 == 'x' && op2 == '+')
			return false;
		return true;
	}

}