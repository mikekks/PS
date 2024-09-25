import java.util.*;

public class Solution {

	public int solution(int[] arrows) {
		int answer = 0;
		int[][] move = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};

		Map<Node, List<Node>> map = new HashMap<>();
		Node cur = new Node(0,0);

		for(int i=0; i<arrows.length; i++){
			for(int k=0; k<2; k++){
				Node next = new Node(cur.y+move[arrows[i]][0], cur.x+move[arrows[i]][1]);
				
				if(!map.containsKey(next)){
					map.put(next, makeEdgeList(cur));
                    
                    if(map.get(cur) == null){
                        map.put(cur, makeEdgeList(next));
                    }
                    else{
                        map.get(cur).add(next);
                    }
				}
				else if(!map.get(next).contains(cur)){
                    answer++;
                    map.get(next).add(cur);
                    map.get(cur).add(next);
                    
				}
                
                
				cur = next;
			}
		}

		return answer;
	}
    
    private static List<Node> makeEdgeList(Node node) {
        List<Node> edge = new ArrayList<>();
        edge.add(node);
        return edge;
    }
}

class Node{
	int y;
	int x;

	public Node(int y, int x){
		this.y = y;
		this.x = x;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return y == node.y && x == node.x;
	}

	@Override
	public int hashCode() {
		return Objects.hash(y, x);
	}
}