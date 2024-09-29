import java.util.*;

class Node{
    int num;
    int y;
    int x;
    Node l;
    Node r;
    
    public Node(int num, int y, int x, Node l, Node r){
        this.num = num;
        this.y = y;
        this.x = x;
        this.l = l;
        this.r = r;
    }
}

class Solution {
    int[][] answer;
    int idx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        int size = nodeinfo.length;
        answer = new int[2][size];

        Node[] nodes = new Node[size];
        
        for(int i=0; i<size; i++){
            nodes[i] = new Node(i+1, nodeinfo[i][1], nodeinfo[i][0], null, null);
        }
        
        Arrays.sort(nodes, (a, b) -> {
            if(a.y != b.y){
                return b.y - a.y;
            }
            return a.x - b.x;
        });
        
        Node root = nodes[0];
        for(int i=1; i<size; i++){
            insert(root, nodes[i]);
        }
        
        dfs_pre(root);
        idx = 0;
        dfs_post(root);
        
        return answer;
    }
    
    void insert(Node p, Node c){
        if(p.x > c.x){
            if(p.l == null){
                p.l = c;
            }
            else{
                insert(p.l, c);
            }
        }
        else{
            if(p.r == null){
                p.r = c;
            }
            else{
                insert(p.r, c);
            }
        }
    }
    
    void dfs_pre(Node cur){
        Node left = cur.l;
        Node right = cur.r;
        
        answer[0][idx++] = cur.num;
        if(left != null){
            dfs_pre(left);
        }
        
        if(right != null){
            dfs_pre(right);
        }
        
    }
    
     void dfs_post(Node cur){
        Node left = cur.l;
        Node right = cur.r;
        
        if(left != null){
            dfs_post(left);
        }
        
        
        if(right != null){
            dfs_post(right);
        }
         
        answer[1][idx++] = cur.num;
        
    }
}