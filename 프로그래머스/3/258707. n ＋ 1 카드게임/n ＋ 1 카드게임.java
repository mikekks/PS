import java.util.*;

class Solution {
    
    int[] picked = new int[1005];
	int[] map = new int[1005];

	public class Condition{
		int a;
		int b;
		int coin;

		public Condition(int a, int b, int coin) {
			this.a = a;
			this.b = b;
			this.coin = coin;
		}
	}
    
    public int solution(int coin, int[] cards) {
        		int round = 1;
		int size = cards.length;
		
		for(int i=0; i<cards.length; i++){
			for(int j=0; j<cards.length; j++){
				if(i == j) continue;
				if(map[i] != 0) continue;
				
				if(cards[i] + cards[j] == size+1){
					map[i] = j;
					map[j] = i;
				}
			}
		}

		for (int i = size / 3; i < size; i += 2) {
			int eIdx = i + 1;
			List<Condition> conditions = new ArrayList<>();

			for (int j = 0; j <= eIdx; j++) {
				if (picked[j] == 0) {
					if (map[j] <= eIdx && picked[map[j]] == 0) { 
						int neededCoin = 0;

						if(j >= size / 3) neededCoin++;
						if(map[j] >= size / 3) neededCoin++;

						conditions.add(new Condition(j, map[j], neededCoin));
					}
				}
			}

			if(conditions.isEmpty()) break;

			// coin 갯수 확인 -> picked 처리 -> coin 갯수 감소
			conditions.sort(Comparator.comparingInt(c -> c.coin));

			Condition condition = conditions.get(0);

			if(coin < condition.coin) break;

			picked[condition.a] = 1;
			picked[condition.b] = 1;
			coin -= condition.coin;

			round++;
		}

		return round;
    }
}