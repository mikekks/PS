import java.util.*;

class Node{
    int s;
    int e;
    
    public Node(int s, int e){
        this.s = s;
        this.e = e;
    }
}

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        
        // 초당 최대 처리량 : 요청의 응답 완료 여부에 관계없이 임의 시간부터 1초(=1,000밀리초)간 처리하는 요청의 최대 개수
        
        // 최대 소수점 셋째 자리
        List<Node> list = new ArrayList<>();
        
        for(int i=0; i<lines.length; i++){
            String[] str = lines[i].split(" ");
            Integer h = Integer.parseInt(str[1].substring(0,2)) * 3600 * 1000;
            Integer m = Integer.parseInt(str[1].substring(3,5)) * 60 * 1000;
			Double tmp = Double.parseDouble(str[1].substring(6,12)) * 1000;
			Integer s = tmp.intValue();
            
            Double t = Double.parseDouble(str[2].substring(0, str[2].length()-1)) * 1000;
            
            int start = (h+m+s) - (t.intValue()) + 1;
            int end = (h+m+s);
            list.add(new Node(start, end));
        }
        
        Collections.sort(list, (a, b) -> {
            return a.e - b.e;
        });
        
        for(int i=0; i<list.size(); i++){
            int cnt = 1;
            int cur_e = list.get(i).e;
            
            for(int j=i+1; j<list.size(); j++){
                if(cur_e+1000 > list.get(j).s){
                    cnt++;
                }
            }
            
            answer = cnt > answer ? cnt : answer;
        }
        
        
        return answer;
    }
}