import java.util.*;

class Node{
    Node[] child = new Node[26];
    boolean isEnd = false;
    int[] count = new int[26];
    
    public Node(){}
}

class Solution {
    Node root = new Node();
    
    public int solution(String[] words) {
        int answer = 0;
        
        // 10분 : 이분탐색
        
        for(int i=0; i<words.length; i++){
            insert(words[i]);
        }
        
        for(int i=0; i<words.length; i++){
            answer += search(words[i]);
        }
        
        return answer;
        
    }
    
    void insert(String target){
        Node curNode = root;
        
        for(int i=0; i<target.length(); i++){
            char cur = target.charAt(i);
            int idx = cur - 'a';
            if(curNode.child[idx] == null){
                curNode.child[idx] = new Node();
            }
            curNode.count[idx]++;
            curNode = curNode.child[idx];
        }
        
        curNode.isEnd = true;
    }
    
    int search(String target){
        int cnt = 0;
        Node curNode = root;
        String curStr = "";
        
        for(int i=0; i<target.length(); i++){
            char cur = target.charAt(i);
            int idx = cur - 'a';
            curStr += cur;
            cnt++;
            
            if(curNode.count[idx] == 1){
                return cnt;
            }
            
            if(target.equals(curStr)){
                return cnt;
            }
            
            curNode =  curNode.child[idx];
        }
        
        return 0;
    }
}