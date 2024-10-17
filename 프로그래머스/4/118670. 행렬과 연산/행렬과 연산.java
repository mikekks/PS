import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = new int[rc.length][rc[0].length];
        int n = rc.length;
        int m = rc[0].length;
        
        LinkedList<LinkedList<Integer>> rows = new LinkedList<>();
        for (int i=0; i<rc.length; i++) {
            int[] row = rc[i];
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int j=1; j<m-1; j++)
                tmp.add(row[j]);
            rows.add(tmp);
        }
        
        LinkedList<Integer> leftEdge = new LinkedList<>();
        LinkedList<Integer> rightEdge = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            leftEdge.add(rc[i][0]);
            rightEdge.add(rc[i][m-1]);
        }
        
        for(int i=0; i<operations.length; i++){
            String s = operations[i];
            if(s.equals("ShiftRow")){
                rows.addFirst(rows.removeLast());
                
                leftEdge.addFirst(leftEdge.removeLast());
                rightEdge.addFirst(rightEdge.removeLast());
            }
            else {
                if(m == 2){
                    rightEdge.addFirst(leftEdge.removeFirst());
                    leftEdge.addLast(rightEdge.removeLast());   
                    continue;
                }
                
                int bottomRight = rightEdge.removeLast();
                int topRight = rows.getFirst().removeLast();
                rightEdge.addFirst(topRight);
                
                int topLeft = leftEdge.removeFirst();
                int bottomLeft = rows.getLast().removeFirst();
                leftEdge.addLast(bottomLeft);    
                
                rows.getFirst().addFirst(topLeft);
                rows.getLast().addLast(bottomRight);
            }
        }
        
        for (int i = 0; i < n; i++) {
            LinkedList<Integer> row = rows.removeFirst();
            answer[i][0] = leftEdge.removeFirst();
            answer[i][m - 1] = rightEdge.removeFirst();
            
            for (int j = 1; j < m - 1; j++) {
                answer[i][j] = row.removeFirst();
            }
        }
        
        return answer;
    }
}