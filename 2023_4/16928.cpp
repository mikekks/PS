#include <iostream>
#include <queue>

using namespace::std;

int N, M;
int board[101];
int visited[101];
queue<pair<int, int>> q;
int di[6] = {1,2,3,4,5,6};

void bfs(int n){
    visited[n] = 1;
    q.push({n, 0});
    
    while(!q.empty()){
        int t = q.front().first;
        int cnt = q.front().second;
        q.pop();
        
        if(t == 100){
            printf("%d\n", cnt);
            break;
        }
        
        for(int i=0; i<6; i++){
            int nt = t+di[i];
            if(visited[nt] == 0 && nt<=100){
                if(board[nt] != 0){
                    
                    //visited[board[nt]] = 1;
                    q.push({board[nt], cnt+1});
                    
                }else{
                    visited[nt] = cnt+1;
                    q.push({nt, cnt+1});
                }
            }
        }
    }
}


int main(){
    scanf("%d %d", &N, &M);
    
    for(int i=0; i<N; i++){
        int t1, t2;
        scanf("%d %d", &t1, &t2);
        board[t1] = t2;
    }
    for(int i=0; i<M; i++){
        int t1, t2;
        scanf("%d %d", &t1, &t2);
        board[t1] = t2;
    }
    
    bfs(1);
    
    
    
}
