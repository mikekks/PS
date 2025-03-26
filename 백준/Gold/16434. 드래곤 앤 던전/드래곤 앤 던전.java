import java.io.*;

class Main {
	static int N, Q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] split = s.split(" ");

		N = Integer.parseInt(split[0]);
		int atk = Integer.parseInt(split[1]);

		int[][] room = new int[N + 1][3];

		for(int i=0; i<N; i++){
			s = br.readLine();
			split = s.split(" ");

			room[i][0] = Integer.parseInt(split[0]);
			room[i][1] = Integer.parseInt(split[1]);
			room[i][2] = Integer.parseInt(split[2]);
		}

		long startHP = 1;
		long endHP = Long.MAX_VALUE / 2;
		long answer = endHP;

		while (startHP <= endHP) {
			long maxHP = startHP + (endHP - startHP) / 2;
			long curHP = maxHP;
			long curAtk = atk;
			boolean survive = true;

			for (int i = 0; i < N; i++) {
				if (room[i][0] == 1) {
					int mAtk = room[i][1];
					int mHP = room[i][2];

					long playerTurn = (mHP + curAtk - 1) / curAtk;
					long monsterTurn = playerTurn - 1;

					curHP -= monsterTurn * mAtk;
					if (curHP <= 0) {
						survive = false;
						break;
					}
				} else {
					curAtk += room[i][1];
					curHP = Math.min(maxHP, curHP + room[i][2]);
				}
			}

			if (survive) {
				answer = maxHP;
				endHP = maxHP - 1;
			} else {
				startHP = maxHP + 1;
			}
		}

		System.out.println(answer);


	}
}
