#include <iostream>
#include <queue>

#define MAX 100001
#define INF 987654321
using namespace::std;

int R, C;
char Map[21][21];
int visited[21][21];
int alpha[100];
int result;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void dfs(int y, int x, int n){
    
    if(n>result)
        result = n;
    
    for(int i=0; i<4; i++){
        int ty = y+ud[i];
        int tx = x+lr[i];
        int next = Map[ty][tx];
        
        if(ty<1 || ty>R || tx<1 || tx>C)
            continue;
        
        if(visited[ty][tx] == 0 && alpha[next] == 0){
            visited[ty][tx] = 1;
            alpha[next] = 1;
            dfs(ty, tx, n+1);
            visited[ty][tx] = 0;
            alpha[next] = 0;
        }
    }
    
}


int main(){
    scanf("%d %d", &R, &C);
   
    for(int i=1; i<=R; i++){
        string tmp;
        cin >> tmp;
        for(int j=1; j<=C; j++){
            Map[i][j] = tmp[j-1];
        }
    }
    visited[1][1] = 1;
    alpha[Map[1][1]] = 1;
    dfs(1, 1, 1);
    
    printf("%d\n", result);
}
