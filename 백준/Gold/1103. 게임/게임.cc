#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>
#include <climits>

using namespace std;

#define MAX 55
int N, M;
int map[MAX][MAX];
bool isVisit[MAX][MAX];
int dp[MAX][MAX];

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

int dfs(int y, int x, int cnt){
    
    int cur_cnt = 1;
    
    for(int i=0; i<4; i++){
        int d = map[y][x];
        int ny = y+ud[i]*d;
        int nx = x+lr[i]*d;
        
        if(ny < 1 || nx < 1 || ny > N || nx > M || map[ny][nx] == -1){
            continue;
        }
        
        if(dp[ny][nx] != 0){
            int tmp = dp[ny][nx] == INT_MAX ? INT_MAX : dp[ny][nx]+1;
            cur_cnt = tmp > cur_cnt ? tmp : cur_cnt;
            continue;
        }
        
        
        if(isVisit[ny][nx]){  // 이미 방문한 경우
            dp[ny][nx] = INT_MAX;
            cur_cnt = INT_MAX;
            continue;
        }
        else{  // 방문 처음인 경우
            isVisit[ny][nx] = true;
            int tmp = dfs(ny, nx, cnt+1);
            if(tmp != INT_MAX) tmp ++;
            cur_cnt = tmp > cur_cnt ? tmp : cur_cnt;
            isVisit[ny][nx] = false;
        }
    }
    
    return dp[y][x] = cur_cnt;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        string tmp;
        cin >> tmp;
        
        for(int j=0; j<M; j++){
            
            if(tmp[j] == 'H'){
                map[i][j+1] = -1;
            }
            else{
                map[i][j+1] = tmp[j] - 48;
            }
            
        }
    }
    
    
    dfs(1, 1, 0);
    
    if(dp[1][1] == INT_MAX){
        dp[1][1] = -1;
    }
    
    cout << dp[1][1] << '\n';
    
    return 0;
}
