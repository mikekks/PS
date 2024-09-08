import java.util.*;

class Node{
    int in;
    int out;
    
    public Node(int in, int out){
        this.in = in;
        this.out = out;
    }
}

class Solution {
    
    // 나간시점 배열
    List<Node> list = new ArrayList<>();
    
    public int solution(int[][] routes) {
        int answer = 0;
        
        for(int i = 0; i< routes.length; i++){
            list.add(new Node(routes[i][0], routes[i][1]));
        }
        
        Collections.sort(list, (o1, o2) -> o1.out - o2.out);
        
        int cam = -50000;
        
        for(Node node : list){
            if(node.in > cam){
                answer++;
                cam = node.out;
            }
        }
        
        return answer;
    }
}