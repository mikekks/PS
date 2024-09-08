import java.util.*;

class Node {
    int a;
    int b;
    int cost;
    
    public Node(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}

class Solution {
    
    int[] p;
    
    public int findP(int n){
        if(p[n] == n) return n;
        
        return p[n] = findP(p[n]);
    }
    
    public boolean merge(int a, int b){
        a = findP(a);
        b = findP(b);
        
        if(a == b) return false;
        
        p[b] = a;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        p = new int[n+1];
        
        // 크루스칼
        List<Node> list = new ArrayList<>();
        
        for(int i=0; i<costs.length; i++){
            list.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        Collections.sort(list, (o1, o2) -> o1.cost-o2.cost);
        
        for(int i=0; i<n+1; i++){
            p[i] = i;
        }
        
        for(Node node : list){
            if(merge(node.a, node.b)){
                answer += node.cost;
            }
        }
        
        return answer;
    }
}