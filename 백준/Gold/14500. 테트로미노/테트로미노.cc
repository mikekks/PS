#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <queue>

#define MAX 505
#define INF 987654321

using namespace std;

// 11:00 시작

int N, M;
int sMap[MAX][MAX];
bool isVisit[MAX][MAX];
int ans = 0;

int ud[4] = {-1,1,0,0};
int lr[4] = {0,0,-1,1};

void dfs(int y, int x, int cnt, int score){
    if(cnt == 4){
        ans = score > ans ? score : ans;
        return;
    }
    
    
    for(int i=0; i<4; i++){
        int ny = y+ud[i];
        int nx = x+lr[i];
        
        if(ny < 1 || nx < 1 || ny > N || nx > M) continue;
        if(isVisit[ny][nx]) continue;
        
        isVisit[ny][nx] = true;
        dfs(ny, nx, cnt+1, score+sMap[ny][nx]);
        isVisit[ny][nx] = false;
        
    }
}


int main() {
//    ios::sync_with_stdio(false);
//    cin.tie(0);
//    cout.tie(0);
    
    cin >> N >> M;
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            cin >> sMap[i][j];
        }
    }
    
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            isVisit[i][j] = true;
            dfs(i, j, 1, sMap[i][j]);
            isVisit[i][j] = false;
        }
    }
    
    for(int i=1; i<=N; i++){
        for(int j=1; j<=M; j++){
            int total = sMap[i][j];
            int min = 987654321;
            
            for(int k=0; k<4; k++){
                total += sMap[i+ud[k]][j+lr[k]];
                min = sMap[i+ud[k]][j+lr[k]] < min ? sMap[i+ud[k]][j+lr[k]] : min;
            }
            total -= min;
            
            ans = total > ans ? total : ans;
        }
    }
    
    
    cout << ans << '\n';
    
}
