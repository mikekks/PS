import java.io.*;

class Main {

	static boolean suc = false;
	static String target;

	static void dfs(String s){

		if(s.length() == target.length()){
			if(s.equals(target)) suc = true;
			return;
		}

		if(s.charAt(s.length()-1) == 'A'){
			String n1 = s.substring(0, s.length() - 1);
			dfs(n1);
		}

		if(s.charAt(0) == 'B'){
			String tmp = s.substring(1);
			String n2 = new StringBuilder(tmp).reverse().toString();
			dfs(n2);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		String b = br.readLine();
		target = a;

		dfs(b);

		if(suc){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}

	}
}
