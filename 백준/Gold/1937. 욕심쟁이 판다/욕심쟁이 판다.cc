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

int N;
int ans;

int map[MAX][MAX];
int dp[MAX][MAX];  // 해당칸에서 최대로 갈 수 있는 칸 수

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};


int dfs(int y, int x, int cur){
    
    int next = 0;
    
    for(int i=0; i<4; i++){
        int ty = y+ud[i];
        int tx = x+lr[i];
        int tmp_next = 0;
        
        if(ty > N || tx > N || ty < 1 || tx < 1) continue;
        
        if(map[ty][tx] > map[y][x]){
            if(dp[ty][tx] > 0){
                tmp_next = dp[ty][tx];
            }
            else{
                // 여기 검토 필요
                tmp_next = dfs(ty, tx, 1);
            }
        }
        
        if(tmp_next > next){
            next = tmp_next;
        }
    }
    
    dp[y][x] = next+cur;
    return next+cur;
}

int main(){
    cin >> N;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            cin >> map[i][j];
        }
    }
    
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            // 여기서 더 시간 줄일 수는 있음.
            
            int tmp = dfs(i, j, 1);
            if(tmp > ans){
                ans = tmp;
            }
        }
    }
    
    
    cout << ans << '\n';

   
}
