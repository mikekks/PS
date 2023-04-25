#include <iostream>
#include <queue>
#define MAX 1001

using namespace::std;

int n,m;
int board[MAX][MAX];
int result[MAX][MAX];
int visited[MAX][MAX];
int a,b;
int ud[4] = {1,-1 ,0 ,0};
int lr[4] = {0 ,0 ,1 ,-1};

void bfs(int x, int y){
    queue<pair<int, int>> q;
    pair<int, int> p;
    visited[x][y] = 1;
    result[x][y] = 0;
    board[x][y] = 0;
    p = {x,y};
    q.push(p);
    
    while(!q.empty()){
        int t1 = q.front().first;
        int t2 = q.front().second;
        q.pop();
        
        for(int i=0; i<4; i++){
            int nx = t1+ud[i];
            int ny = t2+lr[i];
            
            if(nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                if(visited[nx][ny] == 0 && board[nx][ny] != 0){  // 방문 x
                    visited[nx][ny] = 1;
                    result[nx][ny] = 1 + result[t1][t2];
                    pair<int, int> tmp;
                    tmp = {t1+ud[i], t2+lr[i]};
                    q.push(tmp);
                }
            }
        }
    }
    
}

int main(){
    scanf("%d %d", &n, &m);
    
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            scanf("%d", &board[i][j]);
            if(board[i][j] == 2){
                a = i;
                b = j;
            }
        }
    }
    bfs(a,b);
    
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            if(visited[i][j] == 0 && board[i][j] != 0){
                printf("-1 ");
            }else{
                printf("%d ", result[i][j]);
            }
        }
        printf("\n");
    }
}
