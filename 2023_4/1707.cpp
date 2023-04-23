#include <iostream>
#include <queue>
#include <vector>
#include <cstring>

#define MAX 20001

using namespace::std;

int K, V, E;
vector<int> board[MAX];
int visited[MAX];
int group[MAX];

int cnt;
queue<int> q;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

bool bfs(){
    
    while (!q.empty()) {
        int t = q.front();
        q.pop();
        
        for(auto i : board[t]){
            if(visited[i] == 0){
                q.push(i);
                visited[i] = 1;
                group[i] = group[t] * -1;
            }else if(group[t] == group[i]){
                return false;
            }
        }
        
    }
    return true;
}

int main(){
    scanf("%d", &K);
    
    for(int i=0; i<K; i++){
        scanf("%d %d", &V, &E);
        for(int j=0; j<E; j++){
            int t1,t2;
            scanf("%d %d", &t1, &t2);
            board[t1].push_back(t2);
            board[t2].push_back(t1);
        }
        
        bool check = true;
        for(int j=1; j<=V; j++){
            if(visited[j] == 0){
                q.push(j);
                visited[j] = 1;
                group[j] = 1;
            }
            if(!bfs()){
                check = false;
                break;
            }
        }
            
        if(check){
            printf("YES\n");
        }else{
            printf("NO\n");
        }
        
        
        memset(board, 0, sizeof(board));
        memset(visited, 0, sizeof(visited));
        memset(group, 0, sizeof(group));
        while(!q.empty()){
            q.pop();
        }
        
    }
}
