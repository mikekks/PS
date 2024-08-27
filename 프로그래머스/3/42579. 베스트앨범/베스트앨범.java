import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        int[] counts = new int[genres.length + 5];
        
        // 장르별 -> 가장 많이 재생된 노래 2개 뽑기
        // 노래 unique, 많은 재생된 장르 -> 많은 재생된 노래 -> 고유번호 낮은 순으로 출력
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, List<Music>> musicMap = new HashMap<>();
        // Map 만들기 -> List: 노래들 담는 용도
        // Map 정렬
        
        for(int i=0; i<genres.length; i++){
            if(countMap.containsKey(genres[i])){
                Integer cnt = countMap.get(genres[i]);
                countMap.put(genres[i], cnt + plays[i]);
                
                List<Music> mArr = musicMap.get(genres[i]);
                mArr.add(new Music(i, plays[i]));
                musicMap.put(genres[i], mArr);
            } else {
                countMap.put(genres[i], plays[i]);
                
                List<Music> mArr = new ArrayList<>();
                mArr.add(new Music(i, plays[i]));
                musicMap.put(genres[i], mArr);
            }
        }
        
        List<String> list = countMap.keySet().stream()
            .sorted((o1, o2) -> countMap.get(o2).compareTo(countMap.get(o1)))
            .collect(Collectors.toList());
        
        for(String s : list) {
            List<Music> mArr = musicMap.get(s);
            mArr.sort((m1, m2) -> {
                if (m1.cnt != m2.cnt) {
                    return Integer.compare(m2.cnt, m1.cnt);
                } else {
                    return Integer.compare(m1.id, m2.id);
                }
            });
            
            for(int i = 0; i < mArr.size(); i++) {
                if (i == 2) break;
                answer.add(mArr.get(i).id);
            }
        }
        
        int[] intArray = answer.stream().mapToInt(Integer::intValue).toArray();
        
        return intArray;
    }
}

class Music {
    int id;
    int cnt;
    
    public Music(int id, int cnt) {
        this.id = id;
        this.cnt = cnt;
    }
}
