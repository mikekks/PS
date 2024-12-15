import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int T = Integer.parseInt(s);

		while(T > 0){
			T--;
			String p = s = br.readLine();
			int N = Integer.parseInt(br.readLine());
			String nums = s = br.readLine();
			nums = nums.substring(1, nums.length() - 1);

			String[] split = nums.split(",");

			Deque<Integer> arr = new ArrayDeque<>();

			for(int i=0; i<split.length && nums.length() != 0; i++){
				arr.addLast(Integer.parseInt(split[i]));
			}

			boolean isLeft = true;
			boolean isError = false;

			for(int i=0; i<p.length(); i++){
				char command = p.charAt(i);

				if(command == 'D'){
					if(arr.isEmpty()){
						isError = true;
						break;
					}

					if(isLeft)
						arr.removeFirst();
					else
						arr.removeLast();
				}
				else{
					isLeft = !isLeft;
				}
			}

			if(isError){
				System.out.println("error");
				continue;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");

			while(!arr.isEmpty()){
				int next = 0;

				if(isLeft){
					next = arr.pollFirst();
				}
				else{
					next = arr.pollLast();
				}

				sb.append(next);

				if(!arr.isEmpty()){
					sb.append(",");
				}
			}

			sb.append("]");
			System.out.println(sb);

		}

	}

}