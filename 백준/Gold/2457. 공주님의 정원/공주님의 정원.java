import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Flower {
	int s;
	int e;

	public Flower(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

class Main {
	static int N, L;
	static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		N = Integer.parseInt(s);

		List<Flower> flowers = new ArrayList<>();

		for(int i=0; i<N; i++){
			s = br.readLine();
			String[] split = s.split(" ");
			int start = Integer.parseInt(split[0]) * 100 + Integer.parseInt(split[1]);
			int end = Integer.parseInt(split[2]) * 100 + Integer.parseInt(split[3]);
			Flower flower = new Flower(start, end);
			flowers.add(flower);
		}

		flowers.sort((a,b) -> {
			if(a.s == b.s){
				return a.e - b.e;
			}
			return a.s - b.s;
		});

		int cur = 301;
		int idx = 0;
		int max = 0;

		while(cur < 1201){
			boolean check = false;
			for(int i = idx; i<N; i++){
				Flower f = flowers.get(i);
				if(f.s > cur) break;

				if(max < f.e){
					max = f.e;
					idx = i+1;
					check = true;
				}
			}

			if(check){
				cur = max;
				ans++;
			}
			else
				break;
		}

		if(max < 1201){
			System.out.println(0);
		}
		else{
			System.out.println(ans);
		}
	}
}