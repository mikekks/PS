#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>
#include <cstring>
#include <stack>

#define MAX 505

using namespace std;

int N, M;


int map[MAX][MAX];
int dp[MAX][MAX];
bool isVisit[MAX][MAX];

int ud[4]={-1,1,0,0};
int lr[4]={0,0,-1,1};

int dfs(int y, int x){
    
    if(isVisit[y][x]) return dp[y][x];
    
    if(y == M && x == N){
        return 1;
    }
    
    dp[y][x] = 0;
    isVisit[y][x] = true;
    
    for(int i=0; i<4; i++){
        int ty = y+ud[i];
        int tx = x+lr[i];
        
        if(ty < 1 || tx < 1 || ty > M || tx > N) continue;
        
        if(map[ty][tx] < map[y][x]){
            dp[y][x] += dfs(ty, tx);
        }
    }
    
    return dp[y][x];
}

int main(){
    cin >> M >> N;
    
    for(int i=1; i<=M; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
        }
    }
    
    int ans = dfs(1, 1);
    
    cout << ans << '\n';
   
}
